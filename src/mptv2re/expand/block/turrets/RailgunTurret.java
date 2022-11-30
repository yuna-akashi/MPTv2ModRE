package mptv2re.expand.block.turrets;

import arc.math.Mathf;
import arc.util.Time;
import arc.graphics.Color;
import mindustry.entities.Mover;
import mindustry.entities.bullet.BulletType;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class RailgunTurret extends ItemTurret {
    public float chargeTimePer1Shot = 30f;
    public float maxShootCharge = 1f;

    public RailgunTurret(String name) {
        super(name);
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("charge",
                (RailgunTurretBuild entity) -> new Bar(
                        () -> "Charge:",
                        () -> entity.requireCompleteCharging ? Pal.redderDust: Color.valueOf("FFFF7E"),
                        () -> entity.shootChargeAmount / maxShootCharge * entity.chargeMultiplier
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
        public float chargeMultiplier = 100f;
        public float chargeCounter = 0f;
        public float shootChargeAmount = 0f;
        public boolean requireCompleteCharging = true;
        private boolean isCharging = false;

        @Override
        public void updateTile(){

            if(shootChargeAmount > 0 && !requireCompleteCharging) {
                super.updateTile();
            } else {
                charge();
                if(linearWarmup){
                    shootWarmup = Mathf.approachDelta(shootWarmup, 0, chargeTimePer1Shot * 60f);
                }else{
                    shootWarmup = Mathf.lerpDelta(shootWarmup, 0, chargeTimePer1Shot * 60f);
                }

                unit.tile(this);
                unit.rotation(rotation);
                unit.team(team);

                if(logicControlTime > 0){
                    logicControlTime -= Time.delta;
                }
                if(shootChargeAmount >= maxShootCharge * chargeMultiplier){
                    shootChargeAmount = maxShootCharge * chargeMultiplier;
                    isCharging = false;
                    requireCompleteCharging = false;
                }
            }
        }

        public void charge() {
            isCharging = true;
            shootChargeAmount = Mathf.approachDelta(shootChargeAmount, maxShootCharge * chargeMultiplier, chargeTimePer1Shot * maxShootCharge * 0.0003f);
        }

        @Override
        protected void updateShooting(){
            BulletType type = peekAmmo();
            shoot(type);
            if(shootChargeAmount < 1)requireCompleteCharging =true;
        }

        @Override
        protected void shoot(BulletType type){
            super.shoot(type);
            if(shootChargeAmount > 0) {
                shootChargeAmount -= 100;
//                if(linearWarmup){
//                    shootWarmup = Mathf.approachDelta(shootWarmup, 0, 200);
//                }else{
//                    shootWarmup = Mathf.lerpDelta(shootWarmup, 0, 200);
//                }
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
