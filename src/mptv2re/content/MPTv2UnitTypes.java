package mptv2re.content;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.entities.abilities.RepairFieldAbility;
import mindustry.entities.abilities.SuppressionFieldAbility;
import mindustry.entities.abilities.UnitSpawnAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.ammo.PowerAmmoType;
import mindustry.type.unit.ErekirUnitType;
import mindustry.type.unit.MissileUnitType;
import mindustry.type.weapons.PointDefenseWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Env;
import mptv2re.MPTv2RE;

import static mindustry.content.Fx.*;
import static mindustry.gen.Sounds.explosion;
import static mindustry.gen.Sounds.lasershoot;

public class MPTv2UnitTypes {

    public static Weapon
        roombaWeapon;

    public static UnitType
        ///*CoreUnits*/
        metre/*, advance, experimental*/, aoe,

        ///*Rommbas*/
        roomba, miningRoomba, builderRoomba, rebuildRoomba, repairRoomba, shieldRoomba, attackRoomba, jibakuRoomba, jibakuNukeRoomba/*,
        jibakuCarrierRoomba, jibakuNukeCarrierRoomba, heavyCarrierRoomba, supportCarrierRoomba,*/

        ///*AirUnits*/
        //AirShips
        /*stingray, bommer, destroyer, cruiser, battleship, carrier*/,
        //AntimatteredShips
        beast, matter, ecru, eter, destAllier,
        //special
        metrenAssemblyDrone
    ;

    static{
        EntityMapping.nameMap.put(MPTv2RE.name("roomba"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(MPTv2RE.name("jibakuRoomba"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(MPTv2RE.name("jibakuNukeRoomba"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(MPTv2RE.name("attackRoomba"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(MPTv2RE.name("minerRoomba"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(MPTv2RE.name("builderRoomba"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(MPTv2RE.name("rebuildRoomba"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(MPTv2RE.name("healerRoomba"), EntityMapping.idMap[3]);
        EntityMapping.nameMap.put(MPTv2RE.name("shieldRoomba"), EntityMapping.idMap[3]);

        EntityMapping.nameMap.put(MPTv2RE.name("antimatterLightCarrier"), EntityMapping.idMap[25]);
        EntityMapping.nameMap.put(MPTv2RE.name("destAllier"), EntityMapping.idMap[30]);
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
        beast = new MPTv2UnitType("beast"){{
            constructor = EntityMapping.map(3);

            speed = 2.7f;
            accel = 0.08f;
            drag = 0.04f;
            flying = true;
            health = 70;
            engineOffset = 5.75f;
            targetFlags = new BlockFlag[]{BlockFlag.generator, null};
            hitSize = 9;
            itemCapacity = 10;

            weapons.add(new Weapon(){{
                y = 0f;
                x = 2f;
                reload = 20f;
                ejectEffect = Fx.casing1;
                bullet = new BasicBulletType(2.5f, 9){{
                    width = 7f;
                    height = 9f;
                    lifetime = 45f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    ammoMultiplier = 2;
                }};
                shootSound = Sounds.pew;
            }});
        }};

        matter = new MPTv2UnitType("matter"){{
            constructor = EntityMapping.map(3);

            health = 340;
            speed = 1.65f;
            accel = 0.08f;
            drag = 0.016f;
            flying = true;
            hitSize = 10f;
        }};

        ecru = new MPTv2UnitType("ecru"){{
            constructor = EntityMapping.map(3);

            speed = 0.8f;
            accel = 0.04f;
            drag = 0.04f;
            rotateSpeed = 1.9f;
            flying = true;
            lowAltitude = true;
            health = 7200;
            armor = 9f;
            engineOffset = 21;
            engineSize = 5.3f;
            hitSize = 46f;
        }};

        eter = new MPTv2UnitType("eter"){{
            constructor = EntityMapping.map(3);
            isEnemy = true;

            flying = true;
            itemCapacity = 75;

            hitSize = 160F;
            armor = 600;
            health = 2700000;
            forceMultiTarget = true;

            speed = 0.55F;
            rotateSpeed = 0.4F;
            accel = 0.3F;
            drag = 0.03F;
            engineOffset = 80.0F;
            engineSize = 14F;

            float orbRad = 4f, partRad = 2f;
            int parts = 10;

            weapons.add(
                    new Weapon(MPTv2RE.name("eter-main-weapon")){{
                        drawCell = true;
                        top = rotate = mirror = false;
                        shootY = 83f;
                        recoil = 0;
                        inaccuracy = 0;
                        shootSound = Sounds.laserblast;
                        chargeSound = Sounds.lasercharge;
                        soundPitchMin = 1f;
                        shake = 14f;
                        x = y = 0;
                        reload = 350f;

                        cooldownTime = 350f;

                        shootStatusDuration = 60f * 2f;
                        shootStatus = StatusEffects.unmoving;
                        shoot.firstShotDelay = MPTv2Fx.purpleLaserCharge.lifetime;
                        parentizeEffects = true;

                        bullet = new LaserBulletType(){{
                            length = 460f;
                            damage = 2000f;
                            width = 45f;

                            lifetime = 70f;

                            lightningSpacing = 35f;
                            lightningLength = 5;
                            lightningDelay = 1.1f;
                            lightningLengthRand = 15;
                            lightningDamage = 50;
                            lightningAngleRand = 40f;
                            largeHit = true;
                            lightColor = lightningColor = Pal.suppress;

                            chargeEffect = MPTv2Fx.purpleLaserCharge;

                            healPercent = 25f;
                            collidesTeam = true;

                            sideAngle = 15f;
                            sideWidth = 0f;
                            sideLength = 0f;
                            colors = new Color[]{Pal.suppress.cpy().a(0.4f), Pal.suppress, Color.white};
                        }};
                    }},
                    new Weapon(MPTv2RE.name("eter-sub-weapon")){{
                        top = rotate = false;
                        mirror = true;
                        shootY = 43f;
                        shootX = 60f;
                        x = y = 0;
                        reload = 140f;
                        recoil = 1.5f;
                        inaccuracy = 0;
                        shootSound = Sounds.missileLarge;
                        minWarmup = 0.95f;
                        shootWarmupSpeed = 0.1f;
                        shoot.shots = 3;
                        shoot.shotDelay = 7f;
                        bullet = new BulletType(){{
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootSmokeTitan;
                            hitColor = Pal.suppress;
                            shake = 1f;
                            speed = 0f;
                            keepVelocity = false;

                            spawnUnit = new MissileUnitType("disrupt-missile"){{
                                speed = 4.6f;
                                maxRange = 5f;
                                outlineColor = Pal.darkOutline;
                                health = 70;
                                homingDelay = 10f;
                                lowAltitude = true;
                                engineSize = 3f;
                                engineColor = trailColor = Pal.sapBulletBack;
                                engineLayer = Layer.effect;
                                deathExplosionEffect = Fx.none;
                                loopSoundVolume = 0.1f;

                                parts.add(new ShapePart(){{
                                    layer = Layer.effect;
                                    circle = true;
                                    y = -0.25f;
                                    radius = 1.5f;
                                    color = Pal.suppress;
                                    colorTo = Color.white;
                                    progress = PartProgress.life.curve(Interp.pow5In);
                                }});

                                weapons.add(new Weapon(){{
                                    shootCone = 360f;
                                    mirror = false;
                                    reload = 1f;
                                    shootOnDeath = true;
                                    bullet = new ExplosionBulletType(140f, 25f){{
                                        suppressionRange = 140f;
                                        shootEffect = new ExplosionEffect(){{
                                            lifetime = 50f;
                                            waveStroke = 5f;
                                            waveLife = 8f;
                                            waveColor = Color.white;
                                            sparkColor = smokeColor = Pal.suppress;
                                            waveRad = 40f;
                                            smokeSize = 4f;
                                            smokes = 7;
                                            smokeSizeBase = 0f;
                                            sparks = 10;
                                            sparkRad = 40f;
                                            sparkLen = 6f;
                                            sparkStroke = 2f;
                                        }};
                                    }};
                                }});
                            }};
                        }};
                    }}
            );

            weapons.addAll(
                    new Weapon(MPTv2RE.name("eter-cannon")){{
                        mirror = rotate = true;
                        rotateSpeed = 20f;
                        y = 70.5f;
                        x = 18.5f;
                        reload = 140f;
                        recoil = 1.5f;
                        inaccuracy = 0;
                        shoot.shots = 3;
                        shoot.shotDelay = 6f;

                        bullet = new BasicBulletType(40, 250){{
                            lifetime = 20;
                            range = 40;
                            reload = 200f;
                            shootSound = Sounds.cannon;
                        }};
                    }},
                    new Weapon(MPTv2RE.name("eter-cannon")){{
                        mirror = rotate = true;
                        rotateSpeed = 20f;
                        y = 41f;
                        x = 21.2f;
                        reload = 70f;
                        recoil = 1.5f;
                        inaccuracy = 0;
                        shoot.shots = 3;
                        shoot.shotDelay = 6f;

                        bullet = new BasicBulletType(40, 250){{
                            lifetime = 20;
                            range = 40;
                            reload = 200f;
                            shootSound = Sounds.cannon;
                        }};
                    }},
                    new Weapon(MPTv2RE.name("eter-cannon")){{
                        mirror = rotate = true;
                        rotateSpeed = 20f;
                        y = 12f;
                        x = 23f;
                        reload = 70f;
                        recoil = 1.5f;
                        inaccuracy = 0;
                        shoot.shots = 3;
                        shoot.shotDelay = 6f;

                        bullet = new BasicBulletType(40, 250){{
                            lifetime = 20;
                            range = 40;
                            reload = 200f;
                            shootSound = Sounds.cannon;
                        }};
                    }}
            );
            weapons.add(
                    new Weapon(MPTv2RE.name("eter-missile")){{
                        rotate = mirror = false;
                        rotateSpeed = 20f;
                        y = -110f;
                        x = -60f;
                        reload = 220f;
                        recoil = 1.5f;
                        inaccuracy = 0;
                        shoot.shots = 4;
                        shoot.shotDelay = 10f;

                        bullet = new MissileBulletType(40, 300){{
                            lifetime = 200;
                            homingPower = 40;
                            range = 10;
                            reload = 200f;
                            shootSound = Sounds.missile;
                        }};
                    }},
                    new Weapon(MPTv2RE.name("eter-missile")){{
                        rotate = mirror = false;
                        rotateSpeed = 20f;
                        y = -110f;
                        x = 60f;
                        reload = 220f;
                        recoil = 1.5f;
                        inaccuracy = 0;
                        shoot.shots = 4;
                        shoot.shotDelay = 10f;

                        bullet = new MissileBulletType(40, 300){{
                            lifetime = 200;
                            homingPower = 10;
                            range = 40;
                            reload = 200f;
                            shootSound = Sounds.missile;
                        }};
                    }}
            );

            weapons.addAll(
                    new PointDefenseWeapon(MPTv2RE.name("eter-pointDefense-weapon")){{
                        rotate = true;
                        rotateSpeed = 250f;
                        x = 37.5f;
                        y = 38.5f;
                        range = 160;
                        maxRange = 160;
                        reload = 1f;
                        targetAir = targetGround = false;
                        bullet = new BasicBulletType(250, 250);
                    }},
                    new PointDefenseWeapon(MPTv2RE.name("eter-pointDefense-weapon")){{
                        rotate = true;
                        rotateSpeed = 250f;
                        x = 37.5f;
                        y = 31f;
                        range = 160;
                        maxRange = 160;
                        reload = 1f;
                        targetAir = targetGround = false;
                        bullet = new BasicBulletType(250, 250);
                    }},
                    new PointDefenseWeapon(MPTv2RE.name("eter-pointDefense-weapon")){{
                        rotate = true;
                        rotateSpeed = 250f;
                        x = 37.5f;
                        y = 23f;
                        range = 160;
                        maxRange = 160;
                        reload = 1f;
                        targetAir = targetGround = false;
                        bullet = new BasicBulletType(250, 250);
                    }},
                    new PointDefenseWeapon(MPTv2RE.name("eter-pointDefense-weapon")){{
                        rotate = true;
                        rotateSpeed = 250f;
                        x = 37.5f;
                        y = 16f;
                        range = 160;
                        maxRange = 160;
                        reload = 1f;
                        targetAir = targetGround = false;
                        bullet = new BasicBulletType(250, 250);
                    }},
                    new PointDefenseWeapon(MPTv2RE.name("eter-pointDefense-weapon")){{
                        rotate = true;
                        rotateSpeed = 250f;
                        x = 37.5f;
                        y = 7f;
                        range = 160;
                        maxRange = 160;
                        reload = 1f;
                        targetAir = targetGround = false;
                        bullet = new BasicBulletType(250, 250);
                    }},

                    new PointDefenseWeapon(MPTv2RE.name("eter-pointDefense-weapon")){{
                        rotate = true;
                        rotateSpeed = 250f;
                        x = 42f;
                        y = -36.2f;
                        range = 160;
                        maxRange = 160;
                        reload = 1f;
                        targetAir = targetGround = false;
                        bullet = new BasicBulletType(250, 250);
                    }},
                    new PointDefenseWeapon(MPTv2RE.name("eter-pointDefense-weapon")){{
                        rotate = true;
                        rotateSpeed = 250f;
                        x = 42f;
                        y = -44f;
                        range = 160;
                        maxRange = 160;
                        reload = 1f;
                        targetAir = targetGround = false;
                        bullet = new BasicBulletType(250, 250);
                    }},
                    new PointDefenseWeapon(MPTv2RE.name("eter-pointDefense-weapon")){{
                        rotate = true;
                        rotateSpeed = 250f;
                        x = 42f;
                        y = -51f;
                        range = 160;
                        maxRange = 160;
                        reload = 1f;
                        targetAir = targetGround = false;
                        bullet = new BasicBulletType(250, 250);
                    }}
            );

            abilities.add(
                    new SuppressionFieldAbility() {{
                        y = -23.8f;
                        x = 30f;
                        orbRadius = orbRad;
                        particleSize = partRad;
                        particles = parts;
                    }},
                    new SuppressionFieldAbility() {{
                        y = -23.8f;
                        x = -30f;
                        orbRadius = orbRad;
                        particleSize = partRad;
                        particles = parts;
                    }}
            );

            setEnginesMirror(
                    new UnitEngine(30f, -65f, 10f, 120f),
                    new UnitEngine(60f, -80f, 20f, 140f)
            );
        }};

        destAllier = new MPTv2UnitType("destAllier"){{
            constructor = EntityMapping.map(3);
            aiController = DefenderAI::new;
            isEnemy = true;

            flying = true;
            itemCapacity = 275;

            hitSize = 260F;
            armor = 1000;
            health = 5400000;
            range = 800f;

            speed = 0.35F;
            rotateSpeed = 0.35F;
            accel = 0.1F;
            drag = 0.01F;
            engineOffset = 140.0F;
            engineSize = 32.5F;

            float orbRad = 9f, partRad = 4f;
            int parts = 10;

            abilities.add(
                    new UnitSpawnAbility(UnitTypes.toxopid , 62f * 60f, 95f, -12f),
                    new UnitSpawnAbility(UnitTypes.toxopid , 62f * 60f, -95f, -12f),
                    new UnitSpawnAbility(UnitTypes.collaris , 65f * 60f, 55f, 40f),
                    new UnitSpawnAbility(UnitTypes.collaris , 65f * 60f, -55f, 40f)
            );

            abilities.add(
                    new UnitSpawnAbility(UnitTypes.corvus, 65f * 60f, 88f, -63f),
                    new UnitSpawnAbility(UnitTypes.corvus, 65f * 60f, -88f, -63f)
            );

            abilities.add(
                    new UnitSpawnAbility(UnitTypes.eclipse, 65f * 60f, 0f, -95f)
            );

            abilities.add(
                    new UnitSpawnAbility(UnitTypes.flare , 25f * 60f, 65f, 115f),
                    new UnitSpawnAbility(UnitTypes.flare , 25f * 60f, -65f, 115f),
                    new UnitSpawnAbility(UnitTypes.horizon , 30f * 60f, 70f, 80f),
                    new UnitSpawnAbility(UnitTypes.horizon , 30f * 60f, -70f, 80f)
            );

            abilities.add(
                    new RepairFieldAbility(1000, 10f * 60f, 1600),
                    new SuppressionFieldAbility() {{
                        y = 20f;
                        orbRadius = orbRad;
                        particleSize = partRad;
                        particles = parts;
                    }},
                    new SuppressionFieldAbility(){{
                        x = 44;
                        y = -36f;
                        orbRadius = orbRad;
                        particleSize = partRad;
                        particles = parts;
                        display = active = false;
                    }},
                    new SuppressionFieldAbility(){{
                        x = -44;
                        y = -36f;
                        orbRadius = orbRad;
                        particleSize = partRad;
                        particles = parts;
                        display = active = false;
                    }}
            );

            setEnginesMirror(
                    new UnitEngine(55f, -150f, 22f, 330f),
                    new UnitEngine(100f, -130f, 22f, 315f)
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
            health = 50000;
            armor = 500;

            speed = 5F;
            range = 250;
            rotateSpeed = 20F;
            faceTarget = false;

            legCount = 100;
            legLength = 1;
            legMoveSpace = 1.4f;
            legSplashDamage = 500000000;
            legSplashRange = 10.75f;
            hovering = true;

            targetFlags = new BlockFlag[]{BlockFlag.repair, BlockFlag.turret, BlockFlag.reactor, BlockFlag.generator, BlockFlag.core, null};
        }};

        //Special
        miningRoomba = new MPTv2UnitType("miningRoomba"){{
            constructor = EntityMapping.map(3);
            controller = u -> new MinerAI();
            defaultCommand = UnitCommand.mineCommand;

            health = 500;

            flying = true;

            speed = 1.5f;
            engineSize = 0f;

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
            buildSpeed = 5f;

            speed = 5F;
            rotateSpeed = 18F;
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
                ejectEffect = none;

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
            constructor = EntityMapping.map(3);
            aiController = RepairAI::new;
            defaultCommand = UnitCommand.rebuildCommand;

            flying = true;

            speed = 2.6f;
            rotateSpeed = 15f;
            range = 130f;
            health = 400;
            buildSpeed = 0.5f;
            engineSize = 0f;
            lowAltitude = true;

            ammoType = new PowerAmmoType(900);

            mineTier = 2;
            mineSpeed = 3.5f;

            abilities.add(new RepairFieldAbility(500f, 60f * 8, 100f));

            weapons.add(new Weapon("poly-weapon"){{
                constructor = EntityMapping.map(3);
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

            repairRoomba = new MPTv2UnitType("healerRoomba"){{
                constructor = EntityMapping.map(3);
                aiController = DefenderAI::new;

                flying = true;

                health = 1200;
                armor = 10;

                faceTarget = true;
                itemCapacity = 20;
                range = 240;

                speed = 5F;
                rotateSpeed = 18F;
                engineSize = 0f;

                abilities.add(new RepairFieldAbility(500f, 60f * 8, 100f));

                weapons.add(
                    new RepairBeamWeapon("none"){{
                        x = 0;
                        y = 0;
                        shootY = 0;
                        shootSound = lasershoot;
                        repairSpeed = 50000;
                        bullet = new BulletType(){{
                            maxRange = 240;
                        }};
                    }}
                );
            }};

            shieldRoomba = new MPTv2UnitType("shieldRoomba"){{
                constructor = EntityMapping.map(3);
                aiController = DefenderAI::new;

                flying= true;

                health = 1200;
                armor = 120;

                speed = 5F;
                rotateSpeed = 18F;
                engineSize = 0F;

                abilities.add(new ForceFieldAbility(140f, 5f, 10000f, 60f * 12));
            }};

            //attacker
            jibakuRoomba = new MPTv2UnitType("jibakuRoomba"){{
                constructor = EntityMapping.map(3);
                aiController = SuicideAI::new;

                flying= false;

                health = 1200;
                armor = 120;

                speed = 5F;
                rotateSpeed = 18F;
                engineSize = 0F;

                weapons.add(
                        new Weapon("none"){{
                            x = 0;
                            y = 0;
                            shootOnDeath = true;
                            reload = 24;
                            shootCone = 180;
                            ejectEffect = none;
                            shootSound = explosion;
                            shootY = 0;
                            mirror = false;
                            bullet = new BombBulletType(){{
                                hitEffect = pulverize;
                                lifetime = 10;
                                speed = 1;
                                instantDisappear = killShooter = collidesAir = true;
                                hittable = false;
                                splashDamage = 250;
                                splashDamageRadius = 100;
                            }};
                        }}
                );
            }};

            jibakuNukeRoomba = new MPTv2UnitType("jibakuNukeRoomba"){{
                constructor = EntityMapping.map(3);
                aiController = SuicideAI::new;

                flying= false;

                health = 1200;
                armor = 120;

                speed = 2F;
                rotateSpeed = 18F;
                engineSize = 0F;

                weapons.add(
                        new Weapon("none"){{
                            x = 0;
                            y = 0;
                            shootOnDeath = true;
                            reload = 24;
                            shootCone = 180;
                            ejectEffect = none;
                            shootSound = explosion;
                            shootY = 0;
                            mirror = false;
                            bullet = new BombBulletType(){{
                                hitEffect = pulverize;
                                lifetime = 10;
                                speed = 1;
                                instantDisappear = killShooter = collidesAir = true;
                                hittable = false;
                                splashDamage = 1000;
                                splashDamageRadius = 250;
                            }};
                        }}
                );
            }};

            attackRoomba = new MPTv2UnitType("attackRoomba"){{
                constructor = EntityMapping.map(3);

                flying = false;
            }};
        }};
    }

    public static void loadCoreUnits() {
        metre = new UnitType("metre"){{
            constructor = EntityMapping.map(3);
            aiController = BuilderAI::new;
            isEnemy = false;

            hitSize = 16.0F;
            armor = 250;
            health = 50000;

            flying = true;
            mineTier = 9;
            itemCapacity = 75;

            range = 240;

            speed = 3.5F;
            faceTarget = true;
            rotateSpeed = 18F;
            buildSpeed = 5f;
            engineOffset = 6.0F;
            engineSize = 5.0F;

            weapons.add(
                new Weapon(MPTv2RE.name("roombaWeapon")){{
                    top = alternate = autoTarget = autoFindTarget = true;
                    mirror = rotate = false;
                    x = 14;
                    y = 13;
                    reload = 22f;
                    recoil = 1.5f;
                    inaccuracy = 0;

                    shootSound = lasershoot;

                    bullet = new LaserBulletType(200){{
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                    }};
                }},
                new Weapon(MPTv2RE.name("roombaWeapon")){{
                    top = alternate = autoTarget = autoFindTarget = true;
                    mirror  = rotate = false;
                    x = -14;
                    y = 13;
                    reload = 22f;
                    recoil = 1.5f;
                    inaccuracy = 0;

                    shootSound = lasershoot;

                    bullet = new LaserBulletType(200){{
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                    }};
                }},
                new Weapon(MPTv2RE.name("roombaWeapon")){{
                    top = alternate = autoTarget = autoFindTarget = true;
                    mirror = rotate = false;
                    x = 20;
                    y = 0;
                    reload = 22f;
                    recoil = 1.5f;
                    inaccuracy = 0;

                    shootSound = lasershoot;

                    bullet = new LaserBulletType(200){{
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                    }};
                }},
                new Weapon(MPTv2RE.name("roombaWeapon")){{
                    top = alternate = autoTarget = autoFindTarget = true;
                    mirror  = rotate = false;
                    x = -20;
                    y = 0;
                    reload = 22f;
                    recoil = 1.5f;
                    inaccuracy = 0;

                    shootSound = lasershoot;

                    bullet = new LaserBulletType(200){{
                        status = StatusEffects.shocked;
                        statusDuration = 60f;
                    }};
                }}
            );
        }};

        aoe = new ErekirUnitType("aoeUnit"){{
            constructor = EntityMapping.map(3);
            aiController = BuilderAI::new;
            isEnemy = false;

            hitSize = 360.0F;
            armor = 7951;
            health = 900000000;

            flying = true;
            mineTier = 9;
            buildSpeed = 50f;
            itemCapacity = 250000;

            range = 8000;

            rotateMoveFirst = true;
            speed = 0.12F;
            forceMultiTarget = true;
            faceTarget = false;
            rotateSpeed = 18F;
            engineOffset = 0F;
            engineSize = 25F;

            weapons.addAll(
                    new Weapon(MPTv2RE.name("aoeUnit-shockwaveCannon")){{
                        rotate = mirror = false;
                        top = true;
                        shootY = 115f;
                        recoil = 0;
                        inaccuracy = 0;
                        shootSound = Sounds.laserblast;
                        chargeSound = Sounds.lasercharge;
                        soundPitchMin = 1f;
                        shake = 24f;
                        x = y = 0;
                        reload = 1200f;

                        cooldownTime = 500f;

                        shootStatusDuration = 60f * 2f;
                        shootStatus = StatusEffects.unmoving;
                        shoot.firstShotDelay = Fx.greenLaserCharge.lifetime;
                        parentizeEffects = true;

                        bullet = new LaserBulletType(){{
                            length = 8000f;
                            damage = 20000000f;
                            width = 225f;

                            lifetime = 150f;

                            lightningSpacing = 35f;
                            lightningLength = 50;
                            lightningDelay = 1.1f;
                            lightningLengthRand = 15;
                            lightningDamage = 5000;
                            lightningAngleRand = 40f;
                            largeHit = true;
                            lightColor = lightningColor = Pal.heal;

                            chargeEffect = Fx.greenLaserCharge;

                            healPercent = 1000f;
                            collidesTeam = true;

                            sideAngle = 15f;
                            sideWidth = 0f;
                            sideLength = 0f;
                            colors = new Color[]{Pal.heal.cpy().a(0.4f), Pal.heal, Color.white};
                        }};
                    }},

                    new Weapon(MPTv2RE.name("aoeUnit-cannons")){{
                        drawCell = true;
                        top = mirror = false;
                        rotate = true;
                        rotateSpeed = 250f;
                        shootY = 0f;
                        recoil = 0;
                        inaccuracy = 0;
                        shootSound = Sounds.laserblast;
                        chargeSound = Sounds.lasercharge;
                        soundPitchMin = 1f;
                        shake = 14f;
                        x = y = 177;
                        reload = 550f;

                        cooldownTime = 450f;

                        shootStatusDuration = 60f * 2f;
                        shootStatus = StatusEffects.unmoving;
                        shoot.firstShotDelay = MPTv2Fx.purpleLaserCharge.lifetime;
                        parentizeEffects = true;

                        bullet = new LaserBulletType(){{
                            length = 800f;
                            damage = 20000f;
                            width = 100f;

                            lifetime = 90f;

                            lightningSpacing = 35f;
                            lightningLength = 5;
                            lightningDelay = 1.1f;
                            lightningLengthRand = 15;
                            lightningDamage = 500;
                            lightningAngleRand = 40f;
                            largeHit = true;
                            lightColor = lightningColor = Pal.suppress;

                            chargeEffect = MPTv2Fx.purpleLaserCharge;

                            healPercent = 100f;
                            collidesTeam = true;

                            sideAngle = 15f;
                            sideWidth = 0f;
                            sideLength = 0f;
                            colors = new Color[]{Pal.suppress.cpy().a(0.4f), Pal.suppress, Color.white};
                        }};
                    }},
                    new Weapon(MPTv2RE.name("aoeUnit-cannons")){{
                        drawCell = true;
                        top = mirror = false;
                        rotate = true;
                        rotateSpeed = 60f;
                        shootY = 0f;
                        recoil = 0;
                        inaccuracy = 0;
                        shootSound = Sounds.laserblast;
                        chargeSound = Sounds.lasercharge;
                        soundPitchMin = 1f;
                        shake = 14f;
                        x = y = -177;
                        reload = 550f;

                        cooldownTime = 450f;

                        shootStatusDuration = 60f * 2f;
                        shootStatus = StatusEffects.unmoving;
                        shoot.firstShotDelay = MPTv2Fx.purpleLaserCharge.lifetime;
                        parentizeEffects = true;

                        bullet = new LaserBulletType(){{
                            length = 800f;
                            damage = 20000f;
                            width = 100f;

                            lifetime = 90f;

                            lightningSpacing = 35f;
                            lightningLength = 5;
                            lightningDelay = 1.1f;
                            lightningLengthRand = 15;
                            lightningDamage = 500;
                            lightningAngleRand = 40f;
                            largeHit = true;
                            lightColor = lightningColor = Pal.suppress;

                            chargeEffect = MPTv2Fx.purpleLaserCharge;

                            healPercent = 100f;
                            collidesTeam = true;

                            sideAngle = 15f;
                            sideWidth = 0f;
                            sideLength = 0f;
                            colors = new Color[]{Pal.suppress.cpy().a(0.4f), Pal.suppress, Color.white};
                        }};
                    }},
                    new Weapon(MPTv2RE.name("aoeUnit-cannons")){{
                        drawCell = true;
                        top = mirror = false;
                        rotate = true;
                        rotateSpeed = 250f;
                        shootY = 0f;
                        recoil = 0;
                        inaccuracy = 0;
                        shootSound = Sounds.laserblast;
                        chargeSound = Sounds.lasercharge;
                        soundPitchMin = 1f;
                        shake = 14f;
                        x = -177;
                        y = 177;
                        reload = 550f;

                        cooldownTime = 450f;

                        shootStatusDuration = 60f * 2f;
                        shootStatus = StatusEffects.unmoving;
                        shoot.firstShotDelay = MPTv2Fx.purpleLaserCharge.lifetime;
                        parentizeEffects = true;

                        bullet = new LaserBulletType(){{
                            length = 800f;
                            damage = 20000f;
                            width = 100f;

                            lifetime = 90f;

                            lightningSpacing = 35f;
                            lightningLength = 5;
                            lightningDelay = 1.1f;
                            lightningLengthRand = 15;
                            lightningDamage = 500;
                            lightningAngleRand = 40f;
                            largeHit = true;
                            lightColor = lightningColor = Pal.suppress;

                            chargeEffect = MPTv2Fx.purpleLaserCharge;

                            healPercent = 100f;
                            collidesTeam = true;

                            sideAngle = 15f;
                            sideWidth = 0f;
                            sideLength = 0f;
                            colors = new Color[]{Pal.suppress.cpy().a(0.4f), Pal.suppress, Color.white};
                        }};
                    }},
                    new Weapon(MPTv2RE.name("aoeUnit-cannons")){{
                        drawCell = true;
                        top = mirror = false;
                        rotate = true;
                        rotateSpeed = 250f;
                        shootY = 0f;
                        recoil = 0;
                        inaccuracy = 0;
                        shootSound = Sounds.laserblast;
                        chargeSound = Sounds.lasercharge;
                        soundPitchMin = 1f;
                        shake = 14f;
                        x = 177;
                        y = -177;
                        reload = 550f;

                        cooldownTime = 450f;

                        shootStatusDuration = 60f * 2f;
                        shootStatus = StatusEffects.unmoving;
                        shoot.firstShotDelay = MPTv2Fx.purpleLaserCharge.lifetime;
                        parentizeEffects = true;

                        bullet = new LaserBulletType(){{
                            length = 800f;
                            damage = 20000f;
                            width = 100f;

                            lifetime = 90f;

                            lightningSpacing = 35f;
                            lightningLength = 5;
                            lightningDelay = 1.1f;
                            lightningLengthRand = 15;
                            lightningDamage = 500;
                            lightningAngleRand = 40f;
                            largeHit = true;
                            lightColor = lightningColor = Pal.suppress;

                            chargeEffect = MPTv2Fx.purpleLaserCharge;

                            healPercent = 100f;
                            collidesTeam = true;

                            sideAngle = 15f;
                            sideWidth = 0f;
                            sideLength = 0f;
                            colors = new Color[]{Pal.suppress.cpy().a(0.4f), Pal.suppress, Color.white};
                        }};
                    }}
            );

            abilities.add(
                    new RepairFieldAbility(12000, 30f* 60f, 3200)
            );

            setEnginesMirror(
                    new UnitEngine(60f, -90f, 30f, 0f)
            );
        }};
    }

    public static void load() {
        loadPreviousWeapon();
        loadAirUnits();
        loadRoombas();
        loadCoreUnits();
        metrenAssemblyDrone = new ErekirUnitType("metren-assembly-drone"){{
            constructor = EntityMapping.map(3);
            controller = u -> new AssemblerAI();

            flying = true;
            drag = 0.06f;
            accel = 0.11f;
            speed = 2.5f;
            health = 9000;
            engineSize = 2f;
            engineOffset = 6.5f;
            payloadCapacity = 0f;
            targetable = false;
            bounded = false;

            outlineColor = Pal.darkOutline;
            isEnemy = false;
            hidden = true;
            useUnitCap = false;
            logicControllable = false;
            playerControllable = false;
            allowedInPayloads = false;
            createWreck = false;
            envEnabled = Env.any;
            envDisabled = Env.none;
        }};
    }
}
