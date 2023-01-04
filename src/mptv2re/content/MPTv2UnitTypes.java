package mptv2re.content;

import mindustry.ai.types.BuilderAI;
import mindustry.ai.types.FlyingAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.Effect;
import mindustry.entities.abilities.UnitSpawnAbility;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.entities.bullet.ShrapnelBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.logic.LExecutor;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mptv2re.MPTv2RE;

public class MPTv2UnitTypes {
    public static Weapon
        alcChargeLaser;
    public static UnitType
        metre, advance, experimental, emperor,
        builderRoomba,
        antimatterLightCarrier, antimatterHeavyCarrier
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

    public static void loadPreviousWeapon(){
        alcChargeLaser = new Weapon(MPTv2RE.name("alc-chargeLaser")){{
            top = alternate = autoTarget  = true;
            mirror = predictTarget = controllable = rotate = false;
            x = 0;
            y = 10;
            reload = 12f;
            recoil = 3f;
            inaccuracy = 0;
            shoot = new ShootPattern();
            bullet = new ShrapnelBulletType(){{
                lifetime = 45f;
                length = 200f;
                damage = 180.0F;
                status = StatusEffects.shocked;
                statusDuration = 60f;
                serrationSpaceOffset = 40f;
                width = 6f;
            }};
        }};
    }

    public static void loadAirUnits() {
        antimatterLightCarrier= new MPTv2UnitType("antimatterLightCarrier"){{
            constructor = EntityMapping.map(5);
            aiController = FlyingAI::new;
            isEnemy = true;

            flying = true;

            itemCapacity = 75;
            hitSize = 80.0F;
            armor = 486;
            health = 246000;
            speed = 3.5F;
            rotateSpeed = 0.5F;
            accel = 0.02F;
            drag = 0.02F;
            engineOffset = 55.0F;
            engineSize = 10.0F;

            weapons.add(new Weapon(MPTv2RE.name("alc-chargeLaser")){{
                top = true;
                reload = 100f;
                rotate = false;
                mirror = false;
                x = 0f;
                y = 0f;
                shootY = 60;
                recoil = 0;

                inaccuracy = 3f;

                bullet = new LaserBulletType(5000){{
                    width = 20f;
                    range = 500;
                }};
            }});

            abilities.add(
                new UnitSpawnAbility(){{
                    unit = UnitTypes.horizon;
                    spawnTime = 650;
                    spawnX = 14;
                    spawnY = 14;
                }},
                new UnitSpawnAbility(){{
                    unit = UnitTypes.horizon;
                    spawnTime = 650;
                    spawnX = -14;
                    spawnY = 14;

                }}
            );
        }};
    }

    public static void loadRoombas() {
        builderRoomba = new MPTv2UnitType("builderRoomba"){{
            constructor = EntityMapping.map(3);
            aiController = BuilderAI::new;
            isEnemy = false;

            flying = false;

            mineTier = 6;
            itemCapacity = 75;
            hitSize = 16.0F;
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

    public static void loadCoreUnits() {
        metre = new MPTv2UnitType("metre"){{
            aiController = BuilderAI::new;
            isEnemy = false;

            flying = true;
            mineTier = 6;
            itemCapacity = 75;
            hitSize = 16.0F;
            armor = 250;
            health = 50000;
            speed = 5F;
            rotateSpeed = 18F;
            buildSpeed = 5f;
            accel = 0.1F;
            drag = 0.035F;
            engineOffset = 6.0F;
            engineSize = 3.0F;

            alwaysUnlocked = true;
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

    public static void load() {
        loadPreviousWeapon();
        loadCoreUnits();
        loadAirUnits();
        loadRoombas();
    }
}
