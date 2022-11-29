package mptv2re.expand.block.turrets;

import arc.math.Mathf;
import arc.struct.ObjectMap;
import arc.util.Time;
import arc.graphics.Color;
import mindustry.entities.Mover;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.pattern.ShootPattern;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.type.LiquidStack;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumeLiquids;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class RailgunTurret extends ItemTurret {
    public float chargeTimePer1Shot = 30f;
    public float maxShootCharge = 1f;

    public RailgunTurret(String name) {
        super(name);
        hasLiquids = true;
        liquidCapacity = 200f;
    }

    @Override
    public void setBars() {
        super.setBars();
        for(var consl : consumers){
            if(consl instanceof ConsumeLiquid liq){
                addLiquidBar(liq.liquid);
            }else if(consl instanceof ConsumeLiquids multi){
                for(var stack : multi.liquids){
                    addLiquidBar(stack.liquid);
                }
            }
        }
        addBar("charge",
                (RailgunTurretBuild entity) -> new Bar(
                        () -> "Charge:",
                        () -> entity.requireCompleteCharging ? Pal.redderDust: Color.valueOf("FFFF7E"),
                        () -> entity.shootChargeAmount / maxShootCharge
                )
        );
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.maxConsecutive, chargeTimePer1Shot, StatUnit.seconds);
    }

    @Override
    public void init(){
        super.init();
    }

//    public void shooter(Object... objects){
//        ObjectMap<Item, ShootPattern> mapper = ObjectMap.of(objects);
//
//        for(ObjectMap.Entry<Item, BulletType> entry : ammoTypes.entries()){
//            shooterMap.put(entry.value, mapper.get(entry.key, shoot));
//        }
//    }

    public class RailgunTurretBuild extends ItemTurretBuild {
        public float addCharge = 0f;
        public float shootChargeAmount = 0f;
        public boolean requireCompleteCharging = true;
        private boolean isCharging = false;

        @Override
        public void updateTile(){
            if(requireCompleteCharging){
                isCharging = true;
                charge();
                if(shootChargeAmount >= maxShootCharge){
                    shootChargeAmount = maxShootCharge;
                    isCharging = false;
                    requireCompleteCharging = false;
                }
            }

            if(shootChargeAmount > 0f && !requireCompleteCharging) {
                super.updateTile();
            } else {
                isCharging = true;
                charge();

                unit.tile(this);
                unit.rotation(rotation);
                unit.team(team);

                if(logicControlTime > 0){
                    logicControlTime -= Time.delta;
                }

                if(shootChargeAmount >= maxShootCharge){
                    shootChargeAmount = maxShootCharge;
                    isCharging = false;
                    requireCompleteCharging = false;
                }
            }
        }

        public void charge() {
            shootChargeAmount = Mathf.approachDelta(shootChargeAmount, 0, 0);
        }

        @Override
        protected void updateShooting(){
            if(shootChargeAmount >= maxShootCharge){
                BulletType type = peekAmmo();

                shoot(type);

                reloadCounter = 0f;
            } else {
                reloadCounter += 1;
                if(shootChargeAmount <= 0)requireCompleteCharging =true;
            }
        }

        @Override
        protected void shoot(BulletType type){
            super.shoot(type);
            if(shootChargeAmount > 0) {
                shootChargeAmount -= 1;
            } else requireCompleteCharging = true;

            if(consumeAmmoOnce){
                useAmmo();
            }
        }

        @Override
        protected void bullet(BulletType type, float xOffset, float yOffset, float angleOffset, Mover mover){
            super.bullet(type, xOffset, yOffset,angleOffset, mover);
        }

        public boolean isCharge() {
            return isCharging;
        }
    }
}
