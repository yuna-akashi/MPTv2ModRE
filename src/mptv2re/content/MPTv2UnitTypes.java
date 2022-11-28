package mptv2re.content;

import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mptv2re.MPTv2RE;

public class MPTv2UnitTypes {
    public static UnitType
        metre, advance, experimental, emperor
    ;

    static{
        EntityMapping.nameMap.put(MPTv2RE.name("metre"), EntityMapping.idMap[20]);
    }

    public static class MPTv2UnitType extends UnitType{
        public MPTv2UnitType(String name){
            super(name);
        }

        public void setRequirements(ItemStack[] stacks){
            firstRequirements = cachedRequirements = stacks;
        }
    }

    public static void load() {
        metre = new MPTv2UnitType("metre"){{
            constructor = EntityMapping.map(3);
            aiController = BuilderAI::new;
            isEnemy = false;

            mineTier = 6;
            itemCapacity = 75;
            hitSize = 8.0F;
            armor = 250;
            health = 50000;
            speed = 5F;
            rotateSpeed = 18F;
            buildSpeed = 5f;
            accel = 0.1F;
            drag = 0.035F;
            engineOffset = 6.0F;
            engineSize = 3.0F;
            weapons.add(new Weapon("small-mount-weapon"){{
                top = false;
                reload = 15f;
                x = 1f;
                y = 2f;
                shoot = new ShootSpread(){{
                    shots = 2;
                    shotDelay = 3f;
                    spread = 2f;
                }};

                inaccuracy = 3f;
                ejectEffect = Fx.casing1;

                bullet = new BasicBulletType(3.5f, 11){{
                    width = 6.5f;
                    height = 11f;
                    lifetime = 70f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    buildingDamageMultiplier = 0.01f;
                    homingPower = 0.04f;
                }};
            }});
        }};
    }
}
