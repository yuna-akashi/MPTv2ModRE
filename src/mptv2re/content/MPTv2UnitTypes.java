package mptv2re.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.content.UnitTypes;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.part.DrawPart;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.part.ShapePart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.ammo.ItemAmmoType;
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
            ///*Rommbas*///
            roomba,  attackRoomba, jibakuRoomba, jibakuNukeRoomba,
            miningRoomba, repairRoomba, rebuildRoomba, builderRoomba, shieldRoomba,

            ///*Grounds*///
            //spider
            ayu, mino, ami, meru, nimu,

            ///*AirUnits*///
            //Pemu
            pemu, pemuRecon, pemuBomber, pemuLauncher, pemuCannon, pemuCarrier,
            //antimatter
            beast, matter, ecru, eter, destAllier,

            ///*CoreUnits*///
            metre, aoe,

            ///*special*/
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

        EntityMapping.nameMap.put(MPTv2RE.name("ayu"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(MPTv2RE.name("mino"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(MPTv2RE.name("ami"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(MPTv2RE.name("meru"), EntityMapping.idMap[24]);
        EntityMapping.nameMap.put(MPTv2RE.name("nimu"), EntityMapping.idMap[24]);

        EntityMapping.nameMap.put(MPTv2RE.name("pemu"), EntityMapping.idMap[16]);
        EntityMapping.nameMap.put(MPTv2RE.name("pemu-bomber"), EntityMapping.idMap[23]);
        EntityMapping.nameMap.put(MPTv2RE.name("pemu-launcher"), EntityMapping.idMap[23]);
        EntityMapping.nameMap.put(MPTv2RE.name("pemu-cannon"), EntityMapping.idMap[23]);
        EntityMapping.nameMap.put(MPTv2RE.name("pemu-recon"), EntityMapping.idMap[23]);
        EntityMapping.nameMap.put(MPTv2RE.name("pemu-carrier"), EntityMapping.idMap[5]);

        EntityMapping.nameMap.put(MPTv2RE.name("destAllier"), EntityMapping.idMap[5]);

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

    public static void loadRoombas() {
        //Special
        miningRoomba = new MPTv2UnitType("miningRoomba"){{
            constructor = EntityMapping.map(3);
            controller = u -> new MinerAI();
            defaultCommand = UnitCommand.mineCommand;

            health = 840;

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
        }};
    }

    public static void loadAttackRoomba() {
        //attacker
        roomba = new MPTv2UnitType("roomba"){{
            localizedName = "Roomba";
            description = "Clean.";
            constructor = EntityMapping.map(24);
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
        jibakuRoomba = new MPTv2UnitType("jibakuRoomba"){{
            constructor = EntityMapping.map(4);
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
            constructor = EntityMapping.map(4);
            aiController = SuicideAI::new;

            flying= false;

            health = 12000;
            armor = 120;
            hitSize = 15f;

            speed = 2F;
            rotateSpeed = 4F;
            engineSize = 0F;

            deathExplosionEffect = reactorExplosion;

            weapons.add(
                    new Weapon("jibakuNukeRoomba"){{
                        x = 0;
                        y = 0;
                        reload = 60;
                        shootCone = 180;
                        
                        crushDamage = 10000;
                        shootOnDeath = true;
                        
                        ejectEffect = none;
                        shootSound = explosion;
                        chargeSound = Sounds.lasercharge;
                        range = 48f;
                        shootY = 0;
                        mirror = false;
                        shootWarmupSpeed = 0.125f;
                        shootStatus = StatusEffects.unmoving;
                        shootStatusDuration = 60f * 2f;
                        shoot.firstShotDelay = MPTv2Fx.jibakuNukeCharge.lifetime;
                        bullet = new BombBulletType(){{
                            hitEffect = pulverize;
                            lifetime = 10;
                            speed = 1;
                            instantDisappear = killShooter = collidesAir = true;
                            hittable = false;
                            splashDamage = 54827;
                            splashDamageRadius = 1600f;

                            lightColor = lightningColor = Pal.suppress;
                            chargeEffect = MPTv2Fx.jibakuNukeCharge;
                        }};
                    }}
            );
        }};

        attackRoomba = new MPTv2UnitType("attackRoomba"){{
            constructor = EntityMapping.map(3);

            flying = false;
        }};
    }

    public static void loadSpider() {
        ayu = new ErekirUnitType("ayu"){{
            constructor = EntityMapping.map(24);

            speed = 0.72f;
            drag = 0.11f;
            hitSize = 9f;
            rotateSpeed = 3f;
            health = 1360;
            armor = 8f;
            legStraightness = 0.3f;
            stepShake = 0f;

            legCount = 6;
            legLength = 8f;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -2f;
            legBaseOffset = 3f;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legLengthScl = 0.96f;
            legForwardScl = 1.1f;
            legGroupSize = 3;
            rippleScale = 0.2f;

            legMoveSpace = 1f;
            allowLegStep = true;
            hovering = true;
            legPhysicsLayer = false;

            shadowElevation = 0.1f;
            groundLayer = Layer.legUnit - 1f;
            targetAir = false;

            weapons.add(
                    new Weapon(MPTv2RE.name("mainWeapon")){{
                        shootSound = Sounds.missileLaunch;
                        mirror = false;
                        x = 0f;
                        y = 1f;
                        shootY = 4f;
                        reload = 90f;
                        range = 80f;
                        lifetime = 20f;
                        cooldownTime = 60f;
                        heatColor = Pal.turretHeat;

                        bullet = new MissileBulletType(){{
                            damage = 40;
                            homingPower = 20;
                        }};
                    }},
                    new Weapon(MPTv2RE.name("ayu-laser")){{
                        mirror = true;
                        x = 5;
                        y = 1f;
                        shootY = 2f;
                        reload = 40;
                        cooldownTime = 22;
                        bullet = new LaserBulletType(14){{
                            width = 2f;
                            length = 80f;
                        }};
                    }}
            );
        }};

        mino = new ErekirUnitType("mino"){{
            constructor = EntityMapping.map(24);

            speed = 0.7f;
            drag = 0.1f;
            hitSize = 14f;
            rotateSpeed = 3f;
            health = 1100;
            armor = 5f;
            stepShake = 0f;

            legCount = 4;
            legLength = 14f;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -3f;
            legBaseOffset = 5f;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legLengthScl = 0.95f;
            legForwardScl = 0.7f;

            legMoveSpace = 1f;
            hovering = true;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1f;
        }};

        ami = new ErekirUnitType("ami"){{
            constructor = EntityMapping.map(24);

            speed = 0.65f;
            drag = 0.1f;
            hitSize = 21f;
            rotateSpeed = 3f;
            health = 2900;
            armor = 7f;
            fogRadius = 40f;
            stepShake = 0f;

            legCount = 6;
            legLength = 18f;
            legGroupSize = 3;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -3f;
            legBaseOffset = 7f;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legLengthScl = 0.95f;
            legForwardScl = 0.9f;

            legMoveSpace = 1f;
            hovering = true;

            shadowElevation = 0.2f;
            groundLayer = Layer.legUnit - 1f;
        }};

        meru = new ErekirUnitType("meru"){{
            constructor = EntityMapping.map(24);

            drag = 0.1f;
            speed = 0.6f;
            hitSize = 23f;
            health = 6700;
            armor = 5f;

            lockLegBase = true;
            legContinuousMove = true;
            legGroupSize = 3;
            legStraightness = 0.4f;
            baseLegStraightness = 0.5f;
            legMaxLength = 1.3f;
            researchCostMultiplier = 0f;

            abilities.add(new ShieldArcAbility(){{
                region = "meru-shield";
                radius = 34f;
                angle = 82f;
                regen = 0.6f;
                cooldown = 60f * 8f;
                max = 1500f;
                y = -20f;
                width = 6f;
            }});

            rotateSpeed = 2.1f;

            legCount = 6;
            legLength = 15f;
            legForwardScl = 0.45f;
            legMoveSpace = 1.4f;
            rippleScale = 2f;
            stepShake = 0.5f;
            legExtension = -5f;
            legBaseOffset = 5f;

            ammoType = new PowerAmmoType(2000);

            legSplashDamage = 32;
            legSplashRange = 30;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;
        }};

        nimu = new ErekirUnitType("nimu"){{
            constructor = EntityMapping.map(24);

            drag = 0.1f;
            speed = 1.1f;
            hitSize = 60f;
            health = 500000;
            armor = 200;
            rotateSpeed = 1.6f;
            lockLegBase = true;
            legContinuousMove = true;
            legStraightness = 0.6f;
            baseLegStraightness = 0.5f;

            legCount = 8;
            legLength = 30f;
            legForwardScl = 2.1f;
            legMoveSpace = 1.05f;
            rippleScale = 1.2f;
            stepShake = 0.8f;
            legGroupSize = 2;
            legExtension = -6f;
            legBaseOffset = 30f;
            legStraightLength = 0.9f;
            legMaxLength = 1.2f;

            ammoType = new PowerAmmoType(2000);

            legSplashDamage = 48;
            legSplashRange = 48;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;

            range = 160f;

            targetAir = false;
            alwaysShootWhenMoving = true;
            Color nimuColor = Color.valueOf("6e7080"), heatCol = Color.purple;

            var circleProgress = DrawPart.PartProgress.warmup.delay(0.9f);
            var circleColor = nimuColor;
            float circleY = -7f, circleRad = 11f, circleRotSpeed = 3.5f, circleStroke = 1.6f;


            weapons.addAll(
                    new Weapon(){{
                        x = y = 0;
                        mirror = rotate = false;

                        shootY = 25f;
                        reload = 240f;
                        cooldownTime = 110;
                        range = 160f;

                        bullet = new BulletType(){{
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootSmokeTitan;
                            hitColor = Pal.suppress;
                            shake = 1f;
                            speed = 0f;
                            keepVelocity = false;

                            spawnUnit = new MissileUnitType("nimu-missile"){{
                                speed = 8f;
                                maxRange = 6f;
                                lifetime = 60f * 4.5f;
                                outlineColor = Pal.darkOutline;
                                engineColor = trailColor = Pal.redLight;
                                engineLayer = Layer.effect;
                                engineSize = 3.1f;
                                engineOffset = 11f;
                                rotateSpeed = 2.5f;
                                trailLength = 18;
                                missileAccelTime = 45f;
                                lowAltitude = true;
                                loopSound = Sounds.missileTrail;
                                loopSoundVolume = 0.6f;
                                deathSound = Sounds.largeExplosion;
                                targetAir = false;

                                fogRadius = 6f;

                                health = 210;

                                parts.add(
                                        //circle
                                        new ShapePart(){{
                                            layer = Layer.effect;
                                            circle = true;
                                            y = -0.25f;
                                            radius = 1.5f;
                                            color = nimuColor;
                                            colorTo = Color.white;
                                            progress = PartProgress.life.curve(Interp.pow5In);
                                        }}

                                        //shape
                                );

                                weapons.add(new Weapon(){{
                                    shootCone = 360f;
                                    mirror = false;
                                    reload = 1f;
                                    deathExplosionEffect = Fx.massiveExplosion;
                                    shootOnDeath = true;
                                    shake = 10f;
                                    bullet = new ExplosionBulletType(640f, 65f){{
                                        hitColor = Pal.redLight;
                                        shootEffect = new MultiEffect(Fx.massiveExplosion, Fx.scatheExplosion, Fx.scatheLight, new WaveEffect(){{
                                            lifetime = 10f;
                                            strokeFrom = 4f;
                                            sizeTo = 130f;
                                        }});

                                        collidesAir = false;
                                        buildingDamageMultiplier = 0.3f;

                                        ammoMultiplier = 1f;
                                        fragLifeMin = 0.1f;
                                        fragBullets = 7;
                                        fragBullet = new ArtilleryBulletType(3.4f, 32){{
                                            buildingDamageMultiplier = 0.3f;
                                            drag = 0.02f;
                                            hitEffect = Fx.massiveExplosion;
                                            despawnEffect = Fx.scatheSlash;
                                            knockback = 0.8f;
                                            lifetime = 23f;
                                            width = height = 18f;
                                            collidesTiles = false;
                                            splashDamageRadius = 40f;
                                            splashDamage = 80f;
                                            backColor = trailColor = hitColor = Pal.redLight;
                                            frontColor = Color.white;
                                            smokeEffect = Fx.shootBigSmoke2;
                                            despawnShake = 7f;
                                            lightRadius = 30f;
                                            lightColor = Pal.redLight;
                                            lightOpacity = 0.5f;

                                            trailLength = 20;
                                            trailWidth = 3.5f;
                                            trailEffect = Fx.none;
                                        }};
                                    }};
                                }});
                                abilities.add(new MoveEffectAbility(){{
                                    effect = Fx.missileTrailSmoke;
                                    rotation = 180f;
                                    y = -9f;
                                    color = Color.grays(0.6f).lerp(Pal.redLight, 0.5f).a(0.4f);
                                    interval = 7f;
                                }});
                            }};
                        }};

                        parts.addAll(
                                new RegionPart(MPTv2RE.name("nimu-missile-set")){{
                                    progress = PartProgress.warmup;
                                    x = y = 8;
                                    drawCell = true;
                                }},

                                //summoning circle
                                new ShapePart(){{
                                    progress = circleProgress;
                                    color = circleColor;
                                    circle = true;
                                    hollow = true;
                                    stroke = 0f;
                                    strokeTo = circleStroke;
                                    radius = circleRad;
                                    layer = Layer.effect;
                                    y = circleY;
                                }},

                                new ShapePart(){{
                                    progress = circleProgress;
                                    rotateSpeed = -circleRotSpeed;
                                    color = circleColor;
                                    sides = 4;
                                    hollow = true;
                                    stroke = 0f;
                                    strokeTo = circleStroke;
                                    radius = circleRad - 1f;
                                    layer = Layer.effect;
                                    y = circleY;
                                }},

                                //outer squares

                                new ShapePart(){{
                                    progress = circleProgress;
                                    rotateSpeed = -circleRotSpeed;
                                    color = circleColor;
                                    sides = 4;
                                    hollow = true;
                                    stroke = 0f;
                                    strokeTo = circleStroke;
                                    radius = circleRad - 1f;
                                    layer = Layer.effect;
                                    y = circleY;
                                }},

                                //inner square
                                new ShapePart(){{
                                    progress = circleProgress;
                                    rotateSpeed = -circleRotSpeed/2f;
                                    color = circleColor;
                                    sides = 4;
                                    hollow = true;
                                    stroke = 0f;
                                    strokeTo = 2f;
                                    radius = 3f;
                                    layer = Layer.effect;
                                    y = circleY;
                                }},

                                //spikes on circle
                                new HaloPart(){{
                                    progress = circleProgress;
                                    color = circleColor;
                                    tri = true;
                                    shapes = 3;
                                    triLength = 0f;
                                    triLengthTo = 5f;
                                    radius = 6f;
                                    haloRadius = circleRad;
                                    haloRotateSpeed = 10 / 2f;
                                    shapeRotation = 180f;
                                    haloRotation = 180f;
                                    layer = Layer.effect;
                                    y = circleY;
                                }}
                                //shape
                        );
                        Color heatCol2 = heatCol.cpy().add(0.1f, 0.1f, 0.1f).mul(1.2f);
                        for(int i = 1; i < 4; i++){
                            int fi = i;
                            parts.add(new RegionPart("-spine"){{
                                outline = false;
                                progress = PartProgress.warmup.delay(fi / 5f);
                                heatProgress = PartProgress.warmup.add(p -> (Mathf.absin(3f, 0.2f) - 0.2f) * p.warmup);
                                mirror = true;
                                under = true;
                                layerOffset = -0.3f;
                                turretHeatLayer = Layer.turret - 0.2f;
                                moveY = 9f;
                                moveX = 1f + fi * 4f;
                                moveRot = fi * 60f - 130f;

                                color = Color.valueOf("bb68c3");
                                heatColor = heatCol2;
                                moves.add(new PartMove(PartProgress.recoil.delay(fi / 5f), 1f, 0f, 3f));
                            }});
                        }
                    }},
                    new Weapon(){{
                        x = 30f;
                        y = 10f;
                        shootY = 5f;

                        reload = 200f;
                        shootWarmupSpeed = 90f;
                        cooldownTime = 60f;

                        bullet = new EmpBulletType(){{
                            damage = 420;
                            width = 15f;
                            height = 40f;
                            speed = 25f;
                            lifetime = 60f;
                            shootSound = Sounds.pulseBlast;
                        }};
                    }}
            );

            //parts.addAll();
        }};
    }

    public static void loadAir(){
        pemu = new ErekirUnitType("pemu"){{
            constructor = EntityMapping.map(16);
            defaultCommand = UnitCommand.assistCommand;

            buildSpeed = 3.5f;
            mineTier = 9;
            mineSpeed = 23f;

            isEnemy = false;

            health = 20000;
            armor = 120;

            speed = 15f;
            rotateSpeed = 8f;
            accel = 0.05f;
            drag = 0.08f;
            flying = true;
            engineSize = 0f;
            hitSize = 25f;
            itemCapacity = 65;
        }};

        pemuBomber = new ErekirUnitType("pemu-bomber"){{
            constructor = EntityMapping.map(23);

            isEnemy = false;

            health = 20000;
            armor = 120;

            speed = 15f;
            rotateSpeed = 8f;
            accel = 0.05f;
            drag = 0.08f;
            flying = true;
            engineSize = 0f;
            hitSize = 25f;

            itemCapacity = 65;

            targetFlags = new BlockFlag[]{BlockFlag.factory, BlockFlag.reactor,null};
            circleTarget = true;
            ammoType = new ItemAmmoType(Items.graphite);
            weapons.add(new Weapon(){{
                mirror = false;

                minShootVelocity = 0.75f;

                range = 160f;

                x = 0f;
                shootY = 0f;
                reload = 12f;
                shootCone = 180f;
                ejectEffect = Fx.none;
                inaccuracy = 15f;
                ignoreRotation = true;
                shootSound = Sounds.missileLaunch;
                bullet = new BombBulletType(1200f, 25f){{
                    width = 20f;
                    height = 20f;
                    hitEffect = Fx.flakExplosion;
                    shootEffect = Fx.none;
                    smokeEffect = Fx.none;

                    status = StatusEffects.blasted;
                    statusDuration = 60f;
                }};
            }});
        }};

        pemuLauncher = new ErekirUnitType("pemu-launcher"){{
            constructor = EntityMapping.map(23);

            isEnemy = false;

            health = 20000;
            armor = 120;

            speed = 15f;
            rotateSpeed = 8f;
            accel = 0.05f;
            drag = 0.08f;
            flying = true;
            engineSize = 0f;
            hitSize = 25f;
            itemCapacity = 65;
            circleTarget = true;

            targetFlags = new BlockFlag[]{BlockFlag.launchPad, BlockFlag.storage, BlockFlag.battery, BlockFlag.generator, BlockFlag.core, null};

            Color pemuColor = Color.valueOf("feb380");

            weapons.add(
                    new Weapon(){{
                        x = y = 0;
                        rotate = false;
                        mirror = true;

                        shootY = 0f;
                        shootX = 18f;
                        reload = 180f;
                        cooldownTime = 50;
                        range = 160f;

                        bullet = new BulletType(){{
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootSmokeTitan;
                            hitColor = Pal.suppress;
                            shake = 1f;
                            speed = 0f;
                            keepVelocity = false;

                            spawnUnit = new MissileUnitType("pemu-missile"){{
                                speed = 8f;
                                maxRange = 6f;
                                lifetime = 60f * 4.5f;
                                outlineColor = Pal.darkOutline;
                                engineColor = trailColor = Pal.redLight;
                                engineLayer = Layer.effect;
                                engineSize = 3.1f;
                                engineOffset = 11f;
                                rotateSpeed = 2.5f;
                                trailLength = 18;
                                missileAccelTime = 45f;
                                lowAltitude = true;
                                loopSound = Sounds.missileTrail;
                                loopSoundVolume = 0.6f;
                                deathSound = Sounds.largeExplosion;
                                targetAir = true;

                                fogRadius = 6f;

                                health = 210;

                                parts.add(
                                        //circle
                                        new ShapePart(){{
                                            layer = Layer.effect;
                                            circle = true;
                                            y = -0.25f;
                                            radius = 1.5f;
                                            color = pemuColor;
                                            colorTo = Color.white;
                                            progress = PartProgress.life.curve(Interp.pow5In);
                                        }}

                                        //shape
                                );

                                weapons.add(new Weapon(){{
                                    shootCone = 360f;
                                    mirror = false;
                                    reload = 1f;
                                    deathExplosionEffect = Fx.massiveExplosion;
                                    shootOnDeath = true;
                                    shake = 10f;
                                    bullet = new ExplosionBulletType(640f, 65f){{
                                        hitColor = Pal.redLight;
                                        shootEffect = new MultiEffect(Fx.massiveExplosion, Fx.scatheExplosion, Fx.scatheLight, new WaveEffect(){{
                                            lifetime = 10f;
                                            strokeFrom = 4f;
                                            sizeTo = 130f;
                                        }});

                                        collidesAir = true;
                                        buildingDamageMultiplier = 0.85f;

                                        ammoMultiplier = 1f;
                                        fragLifeMin = 0.1f;
                                        fragBullets = 7;
                                        fragBullet = new ArtilleryBulletType(3.4f, 32){{
                                            buildingDamageMultiplier = 0.85f;
                                            drag = 0.02f;
                                            hitEffect = Fx.massiveExplosion;
                                            despawnEffect = Fx.scatheSlash;
                                            knockback = 0.8f;
                                            lifetime = 23f;
                                            width = height = 18f;
                                            collidesTiles = true;
                                            splashDamageRadius = 40f;
                                            splashDamage = 80f;
                                            backColor = trailColor = hitColor = Pal.redLight;
                                            frontColor = Color.white;
                                            smokeEffect = Fx.shootBigSmoke2;
                                            despawnShake = 7f;
                                            lightRadius = 30f;
                                            lightColor = Pal.redLight;
                                            lightOpacity = 0.5f;

                                            trailLength = 20;
                                            trailWidth = 3.5f;
                                            trailEffect = Fx.none;
                                        }};
                                    }};
                                }});
                                abilities.add(new MoveEffectAbility(){{
                                    effect = Fx.missileTrailSmoke;
                                    rotation = 180f;
                                    y = -9f;
                                    color = Color.grays(0.6f).lerp(Pal.redLight, 0.5f).a(0.4f);
                                    interval = 7f;
                                }});
                            }};
                        }};

                        parts.add(
                                new RegionPart(MPTv2RE.name("pemu-launcher-set")){{
                                    layerOffset = -1f;
                                    top = false;
                                    mirror = true;
                                    drawCell = true;
                                    progress = PartProgress.smoothReload;
                                    x = 0f;
                                    y = 0;
                                    moveX = -20;
                                }}
                        );
                    }}
            );

            pemuCannon = new ErekirUnitType("pemu-cannon"){{
                constructor = EntityMapping.map(23);

                isEnemy = false;

                health = 20000;
                armor = 120;

                speed = 15f;
                rotateSpeed = 8f;
                accel = 0.05f;
                drag = 0.08f;
                flying = true;
                engineSize = 0f;
                hitSize = 25f;
                itemCapacity = 65;

                circleTarget = true;

                weapons.add(
                        new Weapon(MPTv2RE.name("pemu-weapon-cannon")){{
                            mirror = true;
                            x = 18;
                            y = 2;
                            shootX = 0f;
                            shootY = 10f;
                            reload = 80f;
                            cooldownTime = 25f;
                            shootSound = Sounds.cannon;

                            bullet = new BasicBulletType(){{
                                damage = 500;
                                width = 6f;
                                height = 4f;
                                lifetime = 50f;
                                pierceArmor = true;
                            }};
                        }}
                );
            }};

            pemuRecon = new ErekirUnitType("pemu-recon"){{
                constructor = EntityMapping.map(16);
                defaultCommand = UnitCommand.assistCommand;

                buildSpeed = 3.5f;
                mineTier = 9;
                mineSpeed = 23f;

                isEnemy = false;

                health = 20000;
                armor = 120;
                targetable = false;

                speed = 15f;
                rotateSpeed = 8f;
                accel = 0.05f;
                drag = 0.08f;
                flying = true;
                engineSize = 0f;
                hitSize = 25f;
                itemCapacity = 65;

                abilities.add(
                        new RepairFieldAbility(1200f, 30f * 60f, 320f)
                );
            }};

            pemuCarrier = new ErekirUnitType("pemu-carrier"){{
                constructor = EntityMapping.map(26);

                buildSpeed = 4f;
                mineTier = 9;
                mineSpeed = 23f;

                isEnemy = false;

                health = 200000;
                armor = 200;

                speed = 10f;
                rotateSpeed = 6f;
                accel = 0.05f;
                drag = 0.08f;
                flying = true;
                engineSize = 20f;
                engineOffset = 20f;
                hitSize = 200f;
                itemCapacity = 650;
                range = 400;
                forceMultiTarget = true;

                abilities.addAll(
                        new RepairFieldAbility(4000, 90f * 60f, 800)//,
//                        new UnitSpawnAbility(MPTv2UnitTypes.pemuBomber, 100f * 60f, 60, -40),
//                        new UnitSpawnAbility(MPTv2UnitTypes.pemuCannon, 100f * 60f, -60, -40),
//
//                        new UnitSpawnAbility(MPTv2UnitTypes.pemuLauncher, 100f * 60f, 100, -80),
//                        new UnitSpawnAbility(MPTv2UnitTypes.pemuRecon, 100f * 60f, 100, -80)
                );

                weapons.addAll(
                        new Weapon(){{
                            mirror = true;
                            x = 90;
                            y = 80;
                            shootY = 60f;
                            recoil = 0;
                            inaccuracy = 3f;
                            shootSound = Sounds.laserblast;
                            chargeSound = Sounds.lasercharge;
                            soundPitchMin = 1f;
                            shake = 10f;
                            reload = 350f;

                            cooldownTime = 350f;

                            shootStatusDuration = 60f * 2f;
                            shootStatus = StatusEffects.unmoving;
                            shoot.firstShotDelay = MPTv2Fx.purpleLaserCharge.lifetime;
                            parentizeEffects = true;

                            bullet = new LaserBulletType(){{
                                length = 460f;
                                damage = 2500f;
                                width = 45f;

                                lifetime = 70f;

                                lightningSpacing = 35f;
                                lightningLength = 7;
                                lightningDelay = 1.1f;
                                lightningLengthRand = 15;
                                lightningDamage = 80;
                                lightningAngleRand = 40f;
                                largeHit = true;
                                lightColor = lightningColor = Pal.suppress;

                                chargeEffect = MPTv2Fx.purpleLaserCharge;

                                sideAngle = 15f;
                                sideWidth = 0f;
                                sideLength = 0f;
                                colors = new Color[]{Pal.suppress.cpy().a(0.4f), Pal.suppress, Color.white};
                            }};
                        }},

                        new Weapon(MPTv2RE.name("pemu-missile-launchers")){{
                            x = 160f;
                            y = 80f;
                            rotate = false;
                            mirror = true;

                            shootY = 0f;
                            shootX = 18f;
                            reload = 180f;
                            cooldownTime = 50;
                            range = 320f;
                            shoot.shots = 5;
                            shoot.shotDelay = 6.5f;

                            bullet = new BulletType(){{
                                shootEffect = Fx.sparkShoot;
                                smokeEffect = Fx.shootSmokeTitan;
                                hitColor = Pal.suppress;
                                shake = 1f;
                                speed = 0f;
                                keepVelocity = false;

                                spawnUnit = new MissileUnitType("pemu-missiles"){{
                                    speed = 8f;
                                    maxRange = 6f;
                                    lifetime = 60f * 4.5f;
                                    outlineColor = Pal.darkOutline;
                                    engineColor = trailColor = Pal.redLight;
                                    engineLayer = Layer.effect;
                                    engineSize = 3.1f;
                                    engineOffset = 11f;
                                    rotateSpeed = 2.5f;
                                    trailLength = 18;
                                    missileAccelTime = 45f;
                                    lowAltitude = true;
                                    loopSound = Sounds.missileTrail;
                                    loopSoundVolume = 0.6f;
                                    deathSound = Sounds.largeExplosion;
                                    targetAir = true;

                                    fogRadius = 6f;

                                    health = 210;

                                    parts.add(
                                            //circle
                                            new ShapePart(){{
                                                layer = Layer.effect;
                                                circle = true;
                                                y = -0.25f;
                                                radius = 1.5f;
                                                color = pemuColor;
                                                colorTo = Color.white;
                                                progress = PartProgress.life.curve(Interp.pow5In);
                                            }}

                                            //shape
                                    );

                                    weapons.add(new Weapon(){{
                                        shootCone = 360f;
                                        mirror = false;
                                        reload = 1f;
                                        deathExplosionEffect = Fx.massiveExplosion;
                                        shootOnDeath = true;
                                        shake = 10f;
                                        bullet = new ExplosionBulletType(2000f, 65f){{
                                            hitColor = Pal.redLight;
                                            shootEffect = new MultiEffect(Fx.massiveExplosion, Fx.scatheExplosion, Fx.scatheLight, new WaveEffect(){{
                                                lifetime = 10f;
                                                strokeFrom = 4f;
                                                sizeTo = 130f;
                                            }});

                                            collidesAir = true;
                                            buildingDamageMultiplier = 0.4f;

                                            ammoMultiplier = 1f;
                                            fragLifeMin = 0.1f;
                                            fragBullets = 7;
                                            fragBullet = new ArtilleryBulletType(3.4f, 32){{
                                                buildingDamageMultiplier = 0.85f;
                                                drag = 0.02f;
                                                hitEffect = Fx.massiveExplosion;
                                                despawnEffect = Fx.scatheSlash;
                                                knockback = 0.8f;
                                                lifetime = 23f;
                                                width = height = 18f;
                                                collidesTiles = true;
                                                splashDamageRadius = 40f;
                                                splashDamage = 80f;
                                                backColor = trailColor = hitColor = Pal.redLight;
                                                frontColor = Color.white;
                                                smokeEffect = Fx.shootBigSmoke2;
                                                despawnShake = 7f;
                                                lightRadius = 30f;
                                                lightColor = Pal.redLight;
                                                lightOpacity = 0.5f;

                                                trailLength = 20;
                                                trailWidth = 3.5f;
                                                trailEffect = Fx.none;
                                            }};
                                        }};
                                    }});
                                    abilities.add(new MoveEffectAbility(){{
                                        effect = Fx.missileTrailSmoke;
                                        rotation = 180f;
                                        y = -9f;
                                        color = Color.grays(0.6f).lerp(Pal.redLight, 0.5f).a(0.4f);
                                        interval = 7f;
                                    }});
                                }};
                            }};
                        }},

                        new Weapon(){{
                            mirror = true;
                            x=70;
                            y=50;
                            shootY = 10f;
                            recoil = 0;
                            reload = 150f;
                            cooldownTime = 20f;
                            bullet = new EmpBulletType(){{
                                damage = 750;
                                lifetime = 50f;
                            }};
                        }}
                );
            }};
        }};
    }

    public static void loadAntimatter(){
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
                    new UnitType.UnitEngine(30f, -65f, 10f, 120f),
                    new UnitType.UnitEngine(60f, -80f, 20f, 140f)
            );
        }};

        destAllier = new ErekirUnitType("destAllier"){{
            constructor = EntityMapping.map(26);
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

            abilities.addAll(
                    new UnitSpawnAbility(MPTv2UnitTypes.ayu, 30f * 60f, 65f, 115f),
                    new UnitSpawnAbility(MPTv2UnitTypes.ayu, 30f * 60f, -65f, 115f),
                    //new UnitSpawnAbility(UnitTypes.horizon , 30f * 60f, 70f, 80f),
                    //new UnitSpawnAbility(UnitTypes.horizon , 30f * 60f, -70f, 80f),

                    new UnitSpawnAbility(MPTv2UnitTypes.nimu, 90f * 60f, 95f, -100f),
                    new UnitSpawnAbility(MPTv2UnitTypes.nimu, 90f * 60f, -95f, -100f),

                    new UnitSpawnAbility(UnitTypes.collaris, 65f * 60f, 55f, 40f),
                    new UnitSpawnAbility(UnitTypes.collaris, 65f * 60f, -55f, 40f),

                    new UnitSpawnAbility(UnitTypes.disrupt, 65f * 60f, 88f, -63f),
                    new UnitSpawnAbility(UnitTypes.disrupt, 65f * 60f, -88f, -63f),

                    new UnitSpawnAbility(UnitTypes.conquer, 65f * 60f, 0f, -95f)
            );

            abilities.add(
                    new RepairFieldAbility(2000, 60f * 60f, 1600),
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
                    new UnitType.UnitEngine(55f, -150f, 22f, 330f),
                    new UnitType.UnitEngine(100f, -130f, 22f, 315f)
            );
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
            speed = 0.5F;
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
        loadAttackRoomba();
        loadRoombas();
        loadSpider();
        loadAir();
        loadAntimatter();
        loadCoreUnits();
        metrenAssemblyDrone = new ErekirUnitType("metren-assembly-drone"){{
            localizedName = "Metren Assembly Drone";
            constructor = EntityMapping.map(36);
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
