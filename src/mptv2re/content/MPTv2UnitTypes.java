package mptv2re.content;

import arc.graphics.Color;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.*;
import mindustry.entities.abilities.RepairFieldAbility;
import mindustry.entities.abilities.UnitSpawnAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Pal;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.PowerAmmoType;
import mptv2re.MPTv2RE;

import static mindustry.content.Fx.none;
import static mindustry.content.Fx.pulverize;
import static mindustry.gen.Sounds.explosion;
import static mindustry.gen.Sounds.lasershoot;

public class MPTv2UnitTypes {
    public static Weapon
        roombaWeapon,
        alcChargeLaser;
    public static UnitType
        //CoreUnits
        metre, advance, experimental, emperor,
        //Rommbas
        roomba, miningRoomba, builderRoomba, rebuildRoomba, repairRoomba, shieldRoomba, attackRoomba, jibakuRoomba, jibakuNukeRoomba,
        jibakuCarrierRoomba, jibakuNukeCarrierRoomba, heavyCarrierRoomba,
        //AirShip
        stingray,
        antimatterBattleCarrier, antimatterLightCarrier, antimatterHeavyCarrier
    ;

    static{
        EntityMapping.nameMap.put(MPTv2RE.name("metre"), EntityMapping.idMap[3]);
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

        roombaWeapon = new Weapon(MPTv2RE.name("roombaWeapon")){{
            top = alternate = autoTarget  = true;
            mirror = false;
            x = 12;
            y = 11;
            reload = 22f;
            recoil = 1.5f;
            inaccuracy = 0;
            shoot = new ShootPattern();
            bullet = new LaserBulletType(240){{
                lifetime = 45f;
                status = StatusEffects.shocked;
                statusDuration = 60f;
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

                inaccuracy = 0f;

                bullet = new LaserBulletType(5000){{
                    width = 20f;
                    range = 500;
                }};
            }});

            abilities.add(
                new UnitSpawnAbility(){{
                    unit = UnitTypes.horizon;
                    spawnTime = 650;
                    spawnX = 14.5f;
                    spawnY = 14;
                }},
                new UnitSpawnAbility(){{
                    unit = UnitTypes.horizon;
                    spawnTime = 650;
                    spawnX = -14.5f;
                    spawnY = 14;
                }}
            );
        }};
    }

    public static void loadRoombas() {
        roomba = new MPTv2UnitType("roomba"){{
            localizedName = "Roomba";
            description = "Clean.";
            constructor = EntityMapping.map(3);
            aiController = SuicideAI::new;

            flying = false;

            itemCapacity = 10;
            hitSize = 1;
            health = 50000;
            armor = 500;

            speed = 10F;
            range = 120;
            rotateSpeed = 20F;
            accel = 1;
            drag = 1;
            faceTarget = false;

            legCount = 100;
            legLength = 1;
            legMoveSpace = 1.4f;
            legSplashDamage = 500000000;
            legSplashRange = 10.75f;
            hovering = false;
        }};

        miningRoomba = new MPTv2UnitType("miningRoomba"){{
            constructor = EntityMapping.map(3);
            controller = u -> new MinerAI();
            defaultCommand = UnitCommand.mineCommand;

            health = 500;

            flying = true;
            drag = 0.06f;
            accel = 0.12f;
            speed = 1.5f;

            engineSize = 0f;
            engineOffset = 0f;
            range = 50f;
            isEnemy = false;

            ammoType = new PowerAmmoType(500);

            mineTier = 9;
            mineSpeed = 2.5f;
        }};

        builderRoomba = new MPTv2UnitType("builderRoomba"){{
            constructor = EntityMapping.map(3);
            aiController = BuilderAI::new;
            defaultCommand = UnitCommand.assistCommand;
            isEnemy = false;

            flying = true;

            mineTier = 6;
            itemCapacity = 75;

            armor = 250;
            health = 50000;

            speed = 5F;
            rotateSpeed = 18F;
            buildSpeed = 5f;
            accel = 0.1F;
            drag = 0.035F;
            engineSize = 0F;

            weapons.add(new Weapon("none"){{
                top = mirror = false;
                reload = 15f;
                recoil = 0;
                x = 0f;
                y = 0f;
                shootY = 1.5f;
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

        rebuildRoomba = new MPTv2UnitType("rebuildRoomba"){{
            constructor= EntityMapping.map(3);
            defaultCommand = UnitCommand.rebuildCommand;

            flying = true;
            drag = 0.05f;
            speed = 2.6f;
            rotateSpeed = 15f;
            accel = 0.1f;
            range = 130f;
            health = 400;
            buildSpeed = 0.5f;
            engineSize = 0f;
            hitSize = 9f;
            lowAltitude = true;

            ammoType = new PowerAmmoType(900);

            mineTier = 2;
            mineSpeed = 3.5f;

            abilities.add(new RepairFieldAbility(5f, 60f * 8, 100f));

            weapons.add(new Weapon("poly-weapon"){{
                top = false;
                y = -2.5f;
                x = 3.75f;
                reload = 30f;
                ejectEffect = Fx.none;
                recoil = 2f;
                shootSound = Sounds.missile;
                velocityRnd = 0.5f;
                inaccuracy = 15f;
                alternate = true;

                bullet = new MissileBulletType(4f, 12){{
                    homingPower = 0.08f;
                    weaveMag = 4;
                    weaveScale = 4;
                    lifetime = 50f;
                    keepVelocity = false;
                    shootEffect = Fx.shootHeal;
                    smokeEffect = Fx.hitLaser;
                    hitEffect = despawnEffect = Fx.hitLaser;
                    frontColor = Color.white;
                    hitSound = Sounds.none;

                    healPercent = 5.5f;
                    collidesTeam = true;
                    backColor = Pal.heal;
                    trailColor = Pal.heal;
                }};
            }});

            repairRoomba = new MPTv2UnitType("repairRoomba"){{
                constructor = EntityMapping.map(3);
                aiController = RepairAI::new;

                flying = true;

                health = 1200;
                armor = 10;
                hitSize = 1;

                faceTarget = true;
                itemCapacity = 20;
                range = 240;

                engineSize = 0f;

                weapons.add(
                    new Weapon("none"){{
                        x = 0;
                        y = 0;
                        reload = 0;
                        alternate = false;
                        shootY = 0;
                        shootSound = lasershoot;
                        bullet = new LaserBoltBulletType(){{
                            damage = 13;
                            speed = 5.2f;
                            healPercent = 100;
                            backColor = Color.valueOf("98ffa9");
                            frontColor = Color.valueOf("ffffff");
                            lifetime = 50;
                            pierce = pierceBuilding = collidesTeam = true;
                        }};
                    }}
                );
            }};
        }};
    }

    public static void loadCoreUnits() {
        metre = new UnitType("metre"){{
            aiController = BuilderAI::new;
            isEnemy = false;

            hitSize = 16.0F;
            armor = 250;
            health = 50000;

            flying = true;
            mineTier = 9;
            itemCapacity = 75;

            range = 160;

            speed = 3.5F;
            faceTarget = true;
            rotateSpeed = 18F;
            buildSpeed = 5f;
            accel = 0.1F;
            drag = 0.035F;
            engineOffset = 6.0F;
            engineSize = 5.0F;

            weapons.add(
                new Weapon(MPTv2RE.name("roombaWeapon")){{
                    top = alternate = autoTarget  = true;
                    mirror = rotate = false;
                    x = 14;
                    y = 13;
                    reload = 22f;
                    recoil = 1.5f;
                    inaccuracy = 0;
                    shoot = new ShootPattern();

                    shootSound = lasershoot;

                    bullet = new LaserBulletType(200){{
                        lifetime = 45f;
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                    }};
                }},
                new Weapon(MPTv2RE.name("roombaWeapon")){{
                    top = alternate = autoTarget  = true;
                    mirror  = rotate = false;
                    x = -14;
                    y = 13;
                    reload = 22f;
                    recoil = 1.5f;
                    inaccuracy = 0;
                    shoot = new ShootPattern();

                    shootSound = lasershoot;

                    bullet = new LaserBulletType(200){{
                        lifetime = 45f;
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                    }};
                }},
                new Weapon(MPTv2RE.name("roombaWeapon")){{
                    top = alternate = autoTarget  = true;
                    mirror = rotate = false;
                    x = 20;
                    y = 0;
                    reload = 22f;
                    recoil = 1.5f;
                    inaccuracy = 0;
                    shoot = new ShootPattern();

                    shootSound = lasershoot;

                    bullet = new LaserBulletType(200){{
                        lifetime = 45f;
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                    }};
                }},
                new Weapon(MPTv2RE.name("roombaWeapon")){{
                    top = alternate = autoTarget  = true;
                    mirror  = rotate = false;
                    x = -20;
                    y = 0;
                    reload = 22f;
                    recoil = 1.5f;
                    inaccuracy = 0;
                    shoot = new ShootPattern();

                    shootSound = lasershoot;

                    bullet = new LaserBulletType(200){{
                        lifetime = 45f;
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                    }};
                }}
            );
        }};
    }

    public static void load() {
        loadPreviousWeapon();
        loadCoreUnits();
        loadAirUnits();
        loadRoombas();
    }
}
