package mptv2re.content;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ContinuousLaserBulletType;
import mindustry.entities.bullet.MissileBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;

import static mindustry.type.ItemStack.with;

import mindustry.type.LiquidStack;
import mindustry.type.PayloadStack;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LaserTurret;
import mindustry.world.blocks.defense.turrets.PointDefenseTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.ItemBridge;
import mindustry.world.blocks.distribution.Junction;
import mindustry.world.blocks.distribution.Router;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidJunction;
import mindustry.world.blocks.liquid.LiquidRouter;
import mindustry.world.blocks.payloads.Constructor;
import mindustry.world.blocks.payloads.PayloadDeconstructor;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.sandbox.ItemSource;
import mindustry.world.blocks.sandbox.LiquidSource;
import mindustry.world.blocks.sandbox.PowerSource;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitAssembler;
import mindustry.world.blocks.units.UnitAssemblerModule;
import mindustry.world.blocks.units.UnitFactory;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.BuildVisibility;
import mptv2re.expand.block.turrets.RailgunTurret;

public class MPTv2Blocks {
    public static Block
        //Research centers
            basicResearchCenter, normalResearchCenter, advancedResearchCenter, superResearchCenter, specialResearchCenter,
        turretResearchCenter, unitResearchCenter, efficiencyTechnologyResearchCenter,
        //wall
        titaniumAlloyWall, titaniumAlloyWallLarge, metrenWall, metrenWallLarge,

        //turrets
        assaultCannon, missileSilo, defendTurret, railgun, multiRailgun, guardian, antimatterRailgun, antimatterBlaster, antimatterShockwaveCannon,

        //drill
        titaniumAlloyDrill, metrenDrill,
        //distribution
        superItemSource, metrenConveyor, metrenBridgeConveyor, metrenRouter, metrenJunction,

        //liquids
        superLiquidSource, metrenConduit, metrenBridgeConduit, metrenLiquidRouter, metrenLiquidJunction, metrenLiquidTank,

        //base factory
        metrenSmelter, titaniumAlloySmelter,
        metrenFrameCrafter, largeMetrenFrameCrafter, specialMetrenFrameCrafter,
        turretFrameCrafter, largeTurretFrameCrafter, specialTurretFrameCrafter,
        armorPlateCrafter, heavyArmorPlateCrafter, specialArmorPlateCrafter,

        //metren's factory
        metrenGlassSmelter, metrenDiamondCompressor, metrenSiliconSmelter, metrenAmmoCrafter, metrenExplosiveAmmoCrafter/*,
        multiMetrenSmelter, multiFrameCrafter*/,
        cellFactory, thoriumCompressor, deuteriumChamber, antimatterGenerator,

        //power
        metrenReactor, deuteriumReactor, antimatterReactor, superPowerSource,
        metrenNode, metrenLargeNode, metrenTowerNode,
        metrenBattery, largeMetrenBattery, powerCondenser,

        //units
        roombaFactory/*, metrenedAirFactory*/,
        metrenedAdditiveReconstructor/*, metrenedMultiplicativeReconstructor, metrenedExponentialReconstructor, metrenedTetrativeReconstructor*/,

        antimatteredUnitFactory,
        antimatteredAdditiveReconstructor, antimatteredMultiplicativeReconstructor, antimatteredAssembler, antimatteredAssemblerModule,

        metrenConstructor, metrenLargeConstructor, smallMetrenDeconstructor, metrenDeconstructor,

        //effect
        metrender, boostDriveProjector, metrenShieldDome, buildBlock, metrenUnloader,
        //storage
        metrenContainer,
        ///core
        coreMetren, coreAdvance, coreExperimental, coreEmperorOfAntimatter, coreSDU
    ;

    private static void loadResearchCenter(){
        basicResearchCenter = new GenericCrafter("basicResearchCenter"){{
            size = 3;
            health = 170;
            hasItems = hasPower = true;
            itemCapacity = 60;
            craftTime = 5f * 60f;

            consumePower(2.5f);
            consumeItems(with(Items.copper, 30, Items.metaglass, 15, Items.lead, 25, Items.silicon, 15));
            outputItems = with(MPTv2Items.basicResearchPack, 1);

            requirements(Category.crafting, with(Items.copper, 140, Items.lead, 80, Items.metaglass, 40));
        }};

        normalResearchCenter = new GenericCrafter("normalResearchCenter"){{
            size = 3;
            health = 340;
            hasItems = hasPower = true;
            itemCapacity = 45;
            craftTime = 7.5f * 60f;

            consumePower(5f);
            consumeItems(with(Items.metaglass, 15, MPTv2Items.basicResearchPack, 2, Items.silicon, 15, MPTv2Items.titaniumAlloy, 10));
            outputItems = with(MPTv2Items.normalResearchPack, 1);

            requirements(Category.crafting, with(Items.copper, 280, MPTv2Items.titaniumAlloy, 40));
        }};

        advancedResearchCenter = new GenericCrafter("advancedResearchCenter"){{
            size = 3;
            health = 680;
            hasItems = hasPower = true;
            itemCapacity = 45;
            craftTime = 10f * 60f;

            consumePower(7.5f);
            consumeItems(with(Items.metaglass, 15, MPTv2Items.normalResearchPack, 2, MPTv2Items.metrenGlass, 5, MPTv2Items.metrenDiamond, 5, Items.silicon, 15, MPTv2Items.metrenSilicon, 10, MPTv2Items.metren, 10));
            outputItems = with(MPTv2Items.advancedResearchPack, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 80, MPTv2Items.metren, 128));
        }};

        superResearchCenter = new GenericCrafter("superResearchCenter"){{
            size = 3;
            health = 1360;
            hasItems = hasPower = true;
            itemCapacity = 45;
            craftTime = 12.5f * 60f;

            consumePower(10f);
            consumeItems(with(Items.metaglass, 15, MPTv2Items.advancedResearchPack, 2, MPTv2Items.metrenFrame, 5, MPTv2Items.turretFrame, 5, MPTv2Items.armorPlate, 5, MPTv2Items.metrenSilicon, 15, MPTv2Items.metren, 10));
            outputItems = with(MPTv2Items.superResearchPack, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 160, MPTv2Items.metren, 200));
        }};

        specialResearchCenter = new GenericCrafter("specialResearchCenter"){{
            size = 3;
            health = 2720;
            hasItems = hasPower = true;
            itemCapacity = 60;
            craftTime = 15f * 60f;

            consumePower(12.5f);
            consumeItems(with(Items.metaglass, 15, MPTv2Items.superResearchPack, 2, MPTv2Items.largeMetrenFrame, 5, MPTv2Items.largeTurretFrame, 5, MPTv2Items.heavyArmorPlate, 5, MPTv2Items.antimatterCell, 5, MPTv2Items.metrenSilicon, 20, MPTv2Items.metren, 10));
            outputItems = with(MPTv2Items.specialResearchPack, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 320, MPTv2Items.metren, 288));
        }};
        turretResearchCenter = new GenericCrafter("turretResearchCenter"){{
            size = 3;
            health = 340;
            hasItems = hasPower = true;
            itemCapacity = 45;
            craftTime = 8f * 60f;

            consumePower(12.5f);
            consumeItems(with(Items.metaglass, 15, MPTv2Items.metrenFrame, 10, MPTv2Items.armorPlate, 10, MPTv2Items.metrenSilicon, 20, MPTv2Items.metren, 10));
            outputItems = with(MPTv2Items.turretResearchPack, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 50, MPTv2Items.metren, 72));
        }};
        unitResearchCenter = new GenericCrafter("unitResearchCenter"){{
            size = 3;
            health = 340;
            hasItems = hasPower = true;
            itemCapacity = 45;
            craftTime = 8f * 60f;

            consumePower(12.5f);
            consumeItems(with(Items.metaglass, 15, Items.silicon, 50, MPTv2Items.armorPlate, 5, MPTv2Items.metrenSilicon, 20, MPTv2Items.metren, 10));
            outputItems = with(MPTv2Items.unitResearchPack, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 50, MPTv2Items.metren, 72));
        }};
        efficiencyTechnologyResearchCenter = new GenericCrafter("efficiencyTechnologyResearchCenter"){{
            size = 3;
            health = 340;
            hasItems = hasPower = true;
            itemCapacity = 45;
            craftTime = 8f * 60f;

            consumePower(12.5f);
            consumeItems(with(Items.metaglass, 15, Items.phaseFabric, 5, MPTv2Items.coolingCell, 3, Items.silicon, 15, MPTv2Items.metrenSilicon, 20, MPTv2Items.metren, 10));
            outputItems = with(MPTv2Items.specialResearchPack, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 50, MPTv2Items.metren, 72));
        }};
    }

    private static void loadTurrets() {
        assaultCannon = new ItemTurret("assaultCannon"){{
            size = 3;
            health = 4000000;
            range = 320;
            reload = 110;
            coolant = consumeCoolant(0.05F);
            ammo(MPTv2Items.metrenAmmo, new BasicBulletType(10, 50){{
                width = 2;
                height = 5;
                hitColor = backColor = lightColor = trailColor = MPTv2Items.metrenAmmo.color.cpy().lerp(Color.white, 0.2f);
                frontColor = backColor.cpy().lerp(Color.white, 0.55f);
                ammoMultiplier = 20;
            }});

            requirements(Category.turret, with( MPTv2Items.turretFrame, 8, MPTv2Items.armorPlate, 8));
        }};

        missileSilo = new ItemTurret("missileSilo"){{
            size = 3;
            health = 4000000;
            range = 800;
            reload = 200;
            coolant = consumeCoolant(0.05f);

            ammo(
                    MPTv2Items.metrenMissile, new MissileBulletType(){{
                        damage = 250;
                        homingPower = 20;
                        homingRange = 80;
                        ammoMultiplier = 1;
                    }}
            );

            requirements(Category.turret, with( MPTv2Items.turretFrame, 8, MPTv2Items.armorPlate, 8));
        }};

        defendTurret = new PointDefenseTurret("defendTurret"){{
            size = 2;
            health = 4000000;
            range = 400;
            coolant = consumeCoolant(0.4f);
            hasPower = true;
            consumePowerCond(8f, (PointDefenseTurret.PointDefenseBuild b) -> b.target != null);

            shootLength = 10f;
            bulletDamage = 100f;
            reload = 7.5f;
            requirements(Category.turret, BuildVisibility.shown, with( MPTv2Items.turretFrame, 4, MPTv2Items.armorPlate, 4, MPTv2Items.metren, 8));
        }};

        railgun = new RailgunTurret("railgun"){{
            size = 5;
            health = 25000000;
            range = 4000;
            hasPower = true;
            reload = 360;
            recoil = 0.001f;
            chargeTimePer1Shot = 25;
            maxShootCharge = 1;
            ammo(
                    MPTv2Items.metrenAmmo, new BasicBulletType(50, 500){{
                        lifetime = 80;
                        width = 10;
                        height = 15;
                        hitColor = backColor = lightColor = trailColor = MPTv2Items.metrenAmmo.color.cpy().lerp(Color.white, 0.2f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.55f);
                        ammoMultiplier = 20;
                        pierceArmor = true;
                    }}
            );
            consumePowerCond(1000, RailgunTurretBuild::isCharge);
            requirements(Category.turret, with( MPTv2Items.largeTurretFrame, 25, MPTv2Items.heavyArmorPlate, 25, MPTv2Items.metrenSilicon, 50, MPTv2Items.metren, 50));
        }};
        multiRailgun = new RailgunTurret("multiRailgun"){{
            size = 5;
            health = 25000000;
            range = 4000;
            hasPower = true;
            reload = 360;
            recoil = 0.001f;
            coolant = consumeCoolant(0.05F);
            chargeTimePer1Shot = 25;
            maxShootCharge = 5;
            ammo(
                    MPTv2Items.metrenAmmo, new BasicBulletType(50, 500){{
                        lifetime = 80;
                        width = 10;
                        height = 15;
                        hitColor = backColor = lightColor = trailColor = MPTv2Items.metrenAmmo.color.cpy().lerp(Color.white, 0.2f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.55f);
                        ammoMultiplier = 20;
                        pierceArmor = true;
                    }}
            );
            consumePowerCond(1000, RailgunTurretBuild::isCharge);

            requirements(Category.turret, with( MPTv2Items.largeTurretFrame, 25, MPTv2Items.heavyArmorPlate, 25,MPTv2Items.multiCore, 5, MPTv2Items.metrenSilicon, 50, MPTv2Items.metren, 50));
        }};
        guardian = new ItemTurret("guardian"){{
            size = 5;
            health = 25000000;
            range = 8000;
            reload = 240;
            coolant = consumeCoolant(0.05F);
            maxAmmo = 240;
            ammo(
                    MPTv2Items.metrenAmmo, new BasicBulletType(30, 50000){{
                        width = 10;
                        height = 15;
                        hitColor = backColor = lightColor = trailColor = MPTv2Items.metrenAmmo.color.cpy().lerp(Color.white, 0.2f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.55f);
                        ammoMultiplier = 40;
                        pierceArmor = true;
                    }}
            );

            requirements(Category.turret,with( MPTv2Items.specialTurretFrame, 25, MPTv2Items.specialArmorPlate, 25));
        }};

        antimatterRailgun = new ItemTurret("antimatterRailgun"){{
            size = 16;
            health = 256000000;
            range = 160000;
            hasPower = true;
            reload = 240;
            recoil = 0;
            shootY = -11f;
            rotateSpeed = 0.25f;
            coolant = consumeCoolant(0.05F);
            canOverdrive = false;
//            maxShootCharge = 10;
//            chargeTimePer1Shot = 30f * 60f;
            ammo(
                    MPTv2Items.metrenAmmo, new BasicBulletType(99, 999999999){{
                        width = 32;
                        height = 32;
                        hitColor = backColor = lightColor = trailColor = MPTv2Items.metrenAmmo.color.cpy().lerp(Color.white, 0.2f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.55f);
                        ammoMultiplier = 20;
                        pierceArmor = true;
                    }},
                    MPTv2Items.antimatterCell, new BasicBulletType(99, 999999999){{
                        width = 32;
                        height = 32;
                        hitColor = backColor = lightColor = trailColor = MPTv2Items.antimatterCell.color.cpy().lerp(Color.white, 0.2f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.55f);
                        ammoMultiplier = 4;
                        pierceArmor = true;
                    }}
            );

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-side"){{
                              under = turretShading = mirror = true;
                              moveX = -3.125f;
                              moveY = 2f;
                              progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                          }},
                        new RegionPart("-barrel-side"){{
                            under = true;
                            progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                        }},
                        new RegionPart("-barrel-center"){{
                            under = mirror = true;
                            layerOffset = -0.0025f;
                            moveX = -3.15f;
                            moveY = 25f;
                            y = 2;
                            x = 3f;
                            progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                        }});
//                parts.add(new MPTv2AntiRailCharge){{
//                    progress = aa;
//                 }}
            }};

            consumePower(500000000);
//            consumePowerCond(500000000, RailgunTurretBuild::isCharge);

            requirements(Category.turret, with( MPTv2Items.specialTurretFrame, 256,MPTv2Items.specialArmorPlate, 256, MPTv2Items.metren, 512));
        }};

        antimatterBlaster = new PowerTurret("antimatterBlaster"){{
            size = 16;
            health = 256000000;
            range = 160000;
            reload = 250f;
            recoil = 20;
            rotateSpeed = 0.25f;
            coolant = consumeCoolant(0.5F);
            canOverdrive = false;
            shootY = -15;

            shootType = new BasicBulletType(6.5f, 99999999){{
                hitEffect = new Effect(12.0F, (e) -> {
                    Draw.color(Pal.lancerLaser, Color.white, e.fout() * 0.75f);
                    Lines.stroke(e.fout() * 1.5F);
                    Angles.randLenVectors(e.id, 3, e.finpow() * 17.0F, e.rotation, 360.0F, (x, y) -> {
                        float ang = Mathf.angle(x, y);
                        Lines.lineAngle(e.x + x, e.y + y, ang, e.fout() * 4.0F + 1.0F);
                    });
                });
                trailWidth = 8.55f;
                trailLength = 60;

                knockback = 0.5f;
                trailColor = backColor = hitColor = Pal.lancerLaser;
                frontColor = Color.white;
                lifetime = 500f;
                homingDelay = 1f;
                homingPower = 0.2f;
                homingRange = 120f;
                status = StatusEffects.shocked;
                statusDuration = 30f;
                width = 85f;
                drawSize = 100f;
                height = 85f;
            }};
            consumePower(500000);

            shoot = new ShootPattern(){{
                shots = 1;
            }};

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-side"){{
                    under = turretShading = mirror = true;
                    moveX = -4;
                    moveY = 2.5f;
                    y = -2;
                    x = 4;
                    progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                }});
            }};

            shootSound = Sounds.laserblast;

            requirements(Category.turret, with( MPTv2Items.specialTurretFrame, 256,MPTv2Items.specialArmorPlate, 256, MPTv2Items.metren, 512));
        }};

        antimatterShockwaveCannon = new LaserTurret("antimatterShockwaveCannon"){{
            size = 16;
            health = 256000000;
            range = 160000;
            reload = 250f;
            recoil = 20f;
            shake = 7.5f;
            rotateSpeed = 0.25f;
            shootY = 20f;
            shootDuration = 230f;
            shootCone = 40f;

            unitSort = UnitSorts.strongest;

            coolant = consumeCoolant(0.5F);
            canOverdrive = false;

            consumePower(5000000);

            shootType = new ContinuousLaserBulletType(999){{
                length = 20000f;
                width = 20f;
                hitSize = 10f;
                hitEffect = Fx.hitMeltdown;
                hitColor = MPTv2Items.antimatterCell.color;
                status = StatusEffects.melting;
                drawSize = 840f;

                incendChance = 0.4f;
                incendSpread = 5f;
                incendAmount = 1;
                ammoMultiplier = 1f;
            }};

            drawer = new DrawTurret(){{
                parts.add(new RegionPart("-side"){{
                    under = turretShading = mirror = true;
                    moveX = -4;
                    moveY = 2.5f;
                    y = -2;
                    x = 4;
                    progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                }});
            }};

            shootEffect = Fx.shootBigSmoke2;
            shootSound = Sounds.laserbig;
            loopSound = Sounds.beam;
            loopSoundVolume = 20f;

            requirements(Category.turret, with( MPTv2Items.specialTurretFrame, 256,MPTv2Items.specialArmorPlate, 256, MPTv2Items.metren, 512));
        }};
    }

    private static void loadDrill() {
        titaniumAlloyDrill = new Drill("titaniumAlloyDrill"){{
            size = 2;
            health = 500;
            tier = 4;
            drillTime = 175f;
            liquidBoostIntensity = 1.5f;

            consumeLiquid(Liquids.water, 0.25f).optional(true, true);

            requirements(Category.production, with(MPTv2Items.titaniumAlloy, 32));
        }};

        metrenDrill = new Drill("metrenDrill"){{
            size = 2;
            health = 4000000;
            tier = 9;
            drillTime = 150f;
            liquidBoostIntensity = 1.65f;

            consumeLiquid(Liquids.water, 0.25f).optional(true, true);

            requirements(Category.production, with(MPTv2Items.metren, 28));
        }};
    }

    private static void loadDistribution() {
        metrenConveyor = new Conveyor("metrenConveyor"){{
            size = 1;
            health = 250000;
            speed = 0.16f;
            displayedSpeed = 18f;
            requirements(Category.distribution, with(MPTv2Items.metren, 1));
        }};

        metrenJunction = new Junction("metrenJunction"){{
            size = 1;
            health = 1000000;
            itemCapacity = 10;
            requirements(Category.distribution, with(MPTv2Items.metren, 4));
        }};

        metrenBridgeConveyor = new ItemBridge("metrenBridgeConveyor"){{
            size = 1;
            health = 1000000;
            hasPower = false;
            itemCapacity = 20;
            range = 15;
            requirements(Category.distribution, with(MPTv2Items.metren, 4));
        }};

        metrenRouter = new Router("metrenRouter"){{
            size = 1;
            health = 1000000;
            itemCapacity = 10;
            requirements(Category.distribution, with(MPTv2Items.metren, 4));
        }};
    }

    private static void loadLiquid() {
        metrenConduit = new Conduit("metrenConduit"){{
            size = 1;
            health = 500000;
            liquidCapacity = 20f;
            requirements(Category.liquid, with(MPTv2Items.metren, 2, MPTv2Items.metrenGlass, 1));
        }};

        metrenLiquidRouter = new LiquidRouter("metrenLiquidRouter"){{
            size = 1;
            health = 1000000;
            liquidCapacity = 40f;
            requirements(Category.liquid, with(MPTv2Items.metren, 4, MPTv2Items.metrenGlass, 2));
        }};

        metrenLiquidTank = new LiquidRouter("metrenLiquidTank"){{
            size = 4;
            health = 16000000;
            liquidCapacity = 70000f;
            solid = true;
            requirements(Category.liquid, with(MPTv2Items.metren, 30, MPTv2Items.metrenGlass, 40));
        }};

        metrenLiquidJunction = new LiquidJunction("metrenLiquidJunction"){{
            size = 1;
            health = 1000000;
            requirements(Category.liquid, with(MPTv2Items.metren, 4, MPTv2Items.metrenGlass, 8));
        }};

        metrenBridgeConduit = new LiquidBridge("metrenBridgeConduit"){{
            size = 1;
            health = 1000000;
            liquidCapacity = 20f;
            range = 15;
            hasPower = false;
            requirements(Category.liquid, with(MPTv2Items.metren, 4, MPTv2Items.metrenGlass, 8));
        }};
    }

    private static void loadFactory() {
        titaniumAlloySmelter = new GenericCrafter("titaniumAlloySmelter"){{
            size = 3;
            health = 320;
            hasItems = hasPower = hasLiquids = true;
            itemCapacity = 40;
            liquidCapacity = 80;
            craftTime = 1.33f * 60f;

            consumePower(2.5f);
            consumeItems(with(Items.copper, 3, Items.lead, 2, Items.titanium, 5));
            consumeLiquids(LiquidStack.with(Liquids.slag, 0.25f));
            outputItems = with(MPTv2Items.titaniumAlloy, 1);

            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
            ambientSound = Sounds.smelter;
            craftEffect = Fx.smeltsmoke;

            requirements(Category.crafting, with(Items.copper, 156, Items.lead, 200, Items.titanium, 250));
        }};

        metrenSmelter = new GenericCrafter("metrenSmelter"){{
            size = 3;
            health = 320;
            hasItems = hasPower = true;
            itemCapacity = 120;
            craftTime = 2f * 60f;

            consumePower(6);
            consumeItems(with(Items.graphite, 1, Items.surgeAlloy, 2, MPTv2Items.titaniumAlloy, 3));
            outputItems = with(MPTv2Items.metren, 1);

            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
            ambientSound = Sounds.smelter;
            craftEffect = Fx.smeltsmoke;

            requirements(Category.crafting, with(Items.copper, 156, Items.lead, 200, MPTv2Items.titaniumAlloy, 120));
        }};//done

        metrenGlassSmelter = new GenericCrafter("metrenGlassSmelter"){{
            size = 2;
            health = 4000000;
            hasItems = hasPower = true;
            itemCapacity = 40;
            craftTime = 1.5f * 60f;

            consumePower(2);
            consumeItems(with(MPTv2Items.metren, 1, Items.sand, 2));
            outputItems = with(MPTv2Items.metrenGlass, 1);

            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
            ambientSound = Sounds.smelter;
            craftEffect = Fx.smeltsmoke;

            requirements(Category.crafting, with(MPTv2Items.metren, 32));
        }};//done

        metrenDiamondCompressor = new GenericCrafter("metrenDiamondCompressor"){{
            size = 2;
            health = 4000000;
            hasItems = hasPower = true;
            itemCapacity = 80;
            craftTime = 3f * 60f;

            consumePower(2.5f);
            consumeItems(with(Items.graphite, 2));
            outputItems = with(MPTv2Items.metrenDiamond, 1);

            craftEffect = Fx.generate;

            requirements(Category.crafting, with(MPTv2Items.metren, 32));
        }};//done

        metrenSiliconSmelter = new GenericCrafter("metrenSiliconSmelter"){{
            size = 2;
            health = 4000000;
            hasItems = hasPower = true;
            itemCapacity = 40;
            craftTime = 2f * 60f;

            consumePower(2);
            consumeItems(with(MPTv2Items.metren, 2, Items.graphite, 1, Items.sand, 4));
            outputItems = with(MPTv2Items.metrenSilicon, 2);

            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));
            ambientSound = Sounds.smelter;
            craftEffect = Fx.smeltsmoke;

            requirements(Category.crafting, with(MPTv2Items.metren, 32));
        }};//done

        metrenFrameCrafter = new GenericCrafter("metrenFrameCrafter"){{
            size = 2;
            health = 320;
            hasItems = hasPower = true;

            consumePower(2.5f);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.metrenFrame, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 40, Items.copper, 120, Items.lead, 80));
        }};

        largeMetrenFrameCrafter = new GenericCrafter("largeMetrenFrameCrafter"){{
            size = 3;
            health = 960;
            hasItems = hasPower = true;
            craftTime = 5.5f * 60f;

            consumePower(6);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, Items.silicon, 4, MPTv2Items.metrenFrame, 4));
            outputItems = with(MPTv2Items.largeMetrenFrame, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 80, Items.copper, 240, Items.lead, 160));
        }};

        specialMetrenFrameCrafter = new GenericCrafter("specialMetrenFrameCrafter"){{
            size = 4;
            health = 1280;
            hasItems = hasPower = true;
            craftTime = 7.5f * 60f;

            consumePower(16);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 4, MPTv2Items.largeMetrenFrame, 4));
            outputItems = with(MPTv2Items.specialMetrenFrame, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 120, Items.copper, 360, Items.lead, 240));
        }};

        turretFrameCrafter = new GenericCrafter("turretFrameCrafter"){{
            size = 2;
            health = 320;
            hasItems = hasPower = true;

            consumePower(2.5f);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 2));
            outputItems = with(MPTv2Items.turretFrame, 2);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 40, Items.copper, 120, Items.lead, 80));
        }};

        largeTurretFrameCrafter = new GenericCrafter("largeTurretFrameCrafter"){{
            size = 3;
            health = 960;
            hasItems = hasPower = true;
            craftTime = 5.5f * 60f;

            consumePower(6);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, Items.silicon, 4, MPTv2Items.metrenSilicon, 2, MPTv2Items.turretFrame, 4));
            outputItems = with(MPTv2Items.largeTurretFrame, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 80, Items.copper, 240, Items.lead, 160));
        }};

        specialTurretFrameCrafter = new GenericCrafter("specialTurretFrameCrafter"){{
            size = 4;
            health = 1280;
            hasItems = hasPower = true;
            craftTime = 7.5f * 60f;

            consumePower(16);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 6, MPTv2Items.largeTurretFrame, 4));
            outputItems = with(MPTv2Items.specialTurretFrame, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 120, Items.copper, 360, Items.lead, 240));
        }};

        armorPlateCrafter = new GenericCrafter("armorPlateCrafter"){{
            size = 2;
            health = 320;
            hasItems = hasPower = true;

            consumePower(2.5f);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.armorPlate, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 40, Items.copper, 120, Items.lead, 80));
        }};

        heavyArmorPlateCrafter = new GenericCrafter("heavyArmorPlateCrafter"){{
            size = 3;
            health = 960;
            hasItems = hasPower = true;
            craftTime = 5.5f * 60f;

            consumePower(6);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, Items.silicon, 4, MPTv2Items.armorPlate, 4));
            outputItems = with(MPTv2Items.heavyArmorPlate, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 80, Items.copper, 240, Items.lead, 160));
        }};

        specialArmorPlateCrafter = new GenericCrafter("specialArmorPlateCrafter"){{
            size = 4;
            health = 1280;
            hasItems = hasPower = true;
            craftTime = 7.5f * 60f;

            consumePower(16);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 4, MPTv2Items.heavyArmorPlate, 4));
            outputItems = with(MPTv2Items.specialArmorPlate, 1);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 120, Items.copper, 360, Items.lead, 240));
        }};

        cellFactory = new GenericCrafter("cellFactory"){{
            size = 3;
            health = 9000000;
            hasItems = hasPower = true;
            itemCapacity = 40;
            craftTime = 4f * 60f;

            consumePower(2.75f);
            consumeItems(with(MPTv2Items.metren, 2));
            outputItems = with(MPTv2Items.cell, 1);

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 9, MPTv2Items.armorPlate, 9, MPTv2Items.metren, 18));
        }};

        thoriumCompressor = new GenericCrafter("thoriumCompressor"){{
            size = 3;
            health = 900;
            craftTime = 5f * 60f;

            consumePower(3);
            consumeItems(with(Items.thorium, 3));
            outputItems = with(MPTv2Items.compressedThorium, 1);

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 9, MPTv2Items.armorPlate, 9, MPTv2Items.metrenDiamond,  18));
        }};

        metrenAmmoCrafter = new GenericCrafter("metrenAmmoCrafter"){{
            size = 2;
            health = 320;
            craftTime = 3.75f * 60f;

            consumePower(2.5f);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.metrenAmmo, 2);

            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 40, Items.copper, 120, Items.lead, 80));
        }};

        metrenExplosiveAmmoCrafter = new GenericCrafter("metrenExplosiveAmmoCrafter"){{
            size = 3;
            health = 900;
            craftTime = 6f * 60f;

            consumePower(3);
            consumeItems(with(MPTv2Items.compressedThorium, 3, MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.metrenExplosiveAmmo, 2);

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 9, MPTv2Items.armorPlate, 9, MPTv2Items.metrenDiamond,  18));
        }};

        deuteriumChamber = new GenericCrafter("deuteriumChamber"){{
            size = 4;
            health = 16000000;
            hasItems = hasLiquids = hasPower = true;
            itemCapacity = 40;
            craftTime = 6.5f * 60f;

            consumePower(4.5f);
            consumeLiquids(LiquidStack.with(Liquids.water, 0.5f));
            outputItems = with(MPTv2Items.deuterium, 1, MPTv2Items.solidOxygen, 2);

            requirements(Category.crafting, with(MPTv2Items.largeMetrenFrame, 16, MPTv2Items.heavyArmorPlate, 16, MPTv2Items.metren, 32,MPTv2Items.metrenGlass, 30, MPTv2Items.metrenSilicon, 20));
        }};

        antimatterGenerator = new GenericCrafter("antimatterGenerator"){{
            size = 9;
            health = 81000000;
            hasItems = hasPower = true;
            itemCapacity = 80;
            craftTime = 20f * 60f;

            consumePower(25000000);
            consumeItems(with(MPTv2Items.cell, 1));
            outputItems = with(MPTv2Items.antimatterCell, 1);

            requirements(Category.crafting, with(MPTv2Items.specialArmorPlate, 81, MPTv2Items.specialArmorPlate, 81, MPTv2Items.metrenSilicon, 2000, MPTv2Items.metren, 648));
        }};
    }

    private static void loadPower() {
        //power node
        metrenNode = new PowerNode("metrenNode"){{
            size = 1;
            health = 1000000;
            maxNodes = 25;
            laserRange = 12;
            hasPower = true;
            requirements(Category.power, with(MPTv2Items.metrenFrame, 1, MPTv2Items.armorPlate, 1, MPTv2Items.metren, 10, Items.lead, 2));
        }};//done

        metrenLargeNode = new PowerNode("metrenLargeNode"){{
            size = 2;
            health = 4000000;
            maxNodes = 8;
            laserRange = 36;
            hasPower = true;
            requirements(Category.power, with(MPTv2Items.largeMetrenFrame, 4, MPTv2Items.heavyArmorPlate, 4, MPTv2Items.metren, 8, Items.lead, 8));
        }};//done

        metrenTowerNode = new PowerNode("metrenTowerNode"){{
            size = 2;
            health = 4000000;
            maxNodes = 3;
            laserRange = 320;
            hasPower = true;
            requirements(Category.power, with(MPTv2Items.specialMetrenFrame, 4, MPTv2Items.specialArmorPlate, 4, MPTv2Items.metrenSilicon, 3, MPTv2Items.metren, 8));
        }};//done

        //battery
        metrenBattery = new Battery("metrenBattery"){{
            size = 1;
            health = 1000000;
            hasPower = true;
            consumePowerBuffered(500000);
            requirements(Category.power, with(MPTv2Items.metrenFrame, 1, MPTv2Items.armorPlate, 1, MPTv2Items.metren, 2, Items.lead, 4));
        }};//done

        largeMetrenBattery = new Battery("metrenLargeBattery"){{
            size = 3;
            health = 9000000;
            hasPower = true;
            consumePowerBuffered(5000000);
            requirements(Category.power, with(MPTv2Items.largeMetrenFrame, 1, MPTv2Items.heavyArmorPlate, 1, MPTv2Items.metren, 18, Items.lead, 4));
        }};

        powerCondenser = new Battery("powerCondenser"){{
            size = 5;
            health = 25000000;
            hasPower = true;
            consumePowerBuffered(2147483647);
            requirements(Category.power, with(MPTv2Items.specialMetrenFrame, 25, MPTv2Items.specialArmorPlate, 25, MPTv2Items.metrenSilicon, 40, MPTv2Items.metren, 50, Items.lead, 60));
        }};

        metrenReactor = new NuclearReactor("metrenReactor"){{
            size = 4;
            health = 16000000;

            hasItems = hasPower = hasLiquids = true;
            itemCapacity = 80;
            liquidCapacity = 600f;
            itemDuration = 300f;
            powerProduction = 2000;
            baseExplosiveness = 1000000;
            heating = 0.2f;
            explosionRadius = 56;
            explosionDamage = 99999999;
            smokeThreshold = 0.5f;
            flashThreshold = 0.75f;

            coolantPower = 0.225f;
            consumeItems(with(MPTv2Items.compressedThorium, 1));
            consumeLiquid(Liquids.cryofluid, 0.55f / 100f);

            requirements(Category.power, with(MPTv2Items.metrenFrame, 16, MPTv2Items.armorPlate, 16, MPTv2Items.metren, 32));
        }};

        deuteriumReactor = new ImpactReactor("deuteriumReactor"){{
            size = 7;
            health = 49000000;
            itemCapacity = 120;
            liquidCapacity = 750f;

            powerProduction = 10000;
            consumePower(500);
            consumeItem(MPTv2Items.deuterium, 1);
            consumeLiquid(Liquids.cryofluid, 0.15f / 90f);

            requirements(Category.power, with(MPTv2Items.largeMetrenFrame, 49, MPTv2Items.heavyArmorPlate, 49, MPTv2Items.metrenSilicon, 80, MPTv2Items.metren, 98));
        }};

        antimatterReactor = new ConsumeGenerator("antimatterReactor"){{
            size = 11;
            health = 121000000;
            itemCapacity = 210;
            powerProduction = 999999999;
            consumeItems(with(MPTv2Items.deuterium, 1, MPTv2Items.antimatterCell, 1));
            requirements(Category.power, with(MPTv2Items.specialMetrenFrame, 121, MPTv2Items.specialArmorPlate, 121, MPTv2Items.metrenSilicon, 180,MPTv2Items.metren, 242));
        }};//done
    }

    private static void loadUnits() {
        roombaFactory = new UnitFactory("roombaFactory"){{
            size = 3;

            consumePower(3f);

            plans = Seq.with(
                    new UnitPlan(MPTv2UnitTypes.roomba, 30f * 60f, with(MPTv2Items.metrenSilicon, 40)),
                    new UnitPlan(MPTv2UnitTypes.miningRoomba, 30f * 60f, with(MPTv2Items.metrenSilicon, 40))
            );

            requirements(Category.units, with(MPTv2Items.metren, 10,MPTv2Items.metrenSilicon, 80));
        }};

        metrenedAdditiveReconstructor = new Reconstructor("metrenedAdditiveReconstructor"){{
            size = 3;

            consumePower(5);
        }};

        antimatteredUnitFactory = new UnitFactory("antimatteredUnitFactory"){{
            size = 3;
            health = 9000000;

            configurable = false;
            plans.add(new UnitPlan(MPTv2UnitTypes.beast, 27f * 60f, with(MPTv2Items.metren, 20, MPTv2Items.metrenSilicon, 10,MPTv2Items.metrenDiamond, 8)));
            consumePower(4f);

            requirements(Category.units, with(MPTv2Items.specialMetrenFrame, 9, MPTv2Items.specialArmorPlate, 9, MPTv2Items.metrenSilicon, 220));
        }};

        antimatteredAdditiveReconstructor = new Reconstructor("antimatteredAdditiveReconstructor"){{
            size = 3;
            health = 9000000;

            consumePower(4.5f);
            consumeItems(with(MPTv2Items.armorPlate, 10, MPTv2Items.metrenSilicon, 20));

            constructTime = 35f * 60f;

            upgrades.addAll(
                    new UnitType[]{MPTv2UnitTypes.beast, MPTv2UnitTypes.matter}
            );

            requirements(Category.units, with(MPTv2Items.specialMetrenFrame, 9, MPTv2Items.specialArmorPlate, 9, MPTv2Items.metrenSilicon, 260));
        }};

        antimatteredMultiplicativeReconstructor = new Reconstructor("antimatteredMultiplicativeReconstructor"){{
            size = 5;
            health = 25000000;

            consumePower(5f);
            consumeItems(with(MPTv2Items.heavyArmorPlate, 10, MPTv2Items.metrenSilicon, 20));

            constructTime = 45f * 60f;

            upgrades.addAll(
                    new UnitType[]{MPTv2UnitTypes.matter, MPTv2UnitTypes.ecru}
            );

            requirements(Category.units, with(MPTv2Items.specialMetrenFrame, 25, MPTv2Items.specialArmorPlate, 25, MPTv2Items.metrenSilicon, 320));
        }};

        antimatteredAssembler = new UnitAssembler("antimatteredAssembler"){{
            size = 7;
            health = 49000000;

            areaSize = 50;
            droneType = MPTv2UnitTypes.metrenAssemblyDrone;

            plans.add(
                    new AssemblerUnitPlan(MPTv2UnitTypes.ecru, 60f * 60f, PayloadStack.list(MPTv2UnitTypes.beast, 8, MPTv2Blocks.metrenWall, 8)),
                    new AssemblerUnitPlan(MPTv2UnitTypes.eter, 360f * 60f, PayloadStack.list(MPTv2UnitTypes.matter, 8, MPTv2Blocks.metrenWall, 24)),
                    new AssemblerUnitPlan(MPTv2UnitTypes.destAllier, 600f * 60f, PayloadStack.list(MPTv2UnitTypes.ecru, 10, MPTv2Blocks.metrenWallLarge, 48))
            );

            consumePower(8);
            consumeLiquid(Liquids.cryofluid,  12f / 60f);

            requirements(Category.units, with(MPTv2Items.specialMetrenFrame, 49, MPTv2Items.specialArmorPlate, 49, MPTv2Items.metrenSilicon, 380));
        }};

        antimatteredAssemblerModule = new UnitAssemblerModule("antimatteredAssemblerModule"){{
            size = 5;
            health = 25000000;

            tier = 2;

            consumePower(6.5f);

            requirements(Category.units, with(MPTv2Items.specialMetrenFrame, 25, MPTv2Items.specialArmorPlate, 25, MPTv2Items.metrenSilicon, 420));
        }};

        metrenConstructor = new Constructor("metrenConstructor"){{
            size = 3;
            health = 9000000;

            consumePower(3);
            buildSpeed = 0.65f;
            hasPower = true;

            filter = Seq.with(MPTv2Blocks.titaniumAlloyWall, MPTv2Blocks.titaniumAlloyWallLarge, MPTv2Blocks.metrenWall, MPTv2Blocks.metrenWallLarge);

            requirements(Category.units, with(MPTv2Items.metrenFrame, 9, MPTv2Items.armorPlate, 9,MPTv2Items.metrenSilicon, 20));
        }};

        metrenLargeConstructor = new Constructor("metrenLargeConstructor"){{
            size = 7;
            health = 49000000;

            consumePower(6);
            buildSpeed = 0.7f;
            hasPower = true;

            maxBlockSize = 9;
            minBlockSize = 3;

            requirements(Category.units, with(MPTv2Items.largeMetrenFrame, 49, MPTv2Items.heavyArmorPlate, 49, MPTv2Items.metrenSilicon, 80));
        }};

        smallMetrenDeconstructor = new PayloadDeconstructor("smallMetrenDeconstructor"){{
            size = 3;
            health = 9000000;

            itemCapacity = 250;
            consumePower(3);
            deconstructSpeed = 2f;

            requirements(Category.units, with(MPTv2Items.metrenFrame, 9, MPTv2Items.armorPlate, 9, MPTv2Items.metrenSilicon, 20));
        }};

        metrenDeconstructor = new PayloadDeconstructor("metrenDeconstructor"){{
            size = 7;
            health = 49000000;

            itemCapacity = 500;
            consumePower(6);
            deconstructSpeed = 3f;

            requirements(Category.units, with(MPTv2Items.largeMetrenFrame, 49, MPTv2Items.heavyArmorPlate, 49, MPTv2Items.metrenSilicon, 80));
        }};
    }

    private static void loadEffects() {
        metrender = new MendProjector("metrender"){{
            size = 3;
            health = 9000000;
            reload = 180f;
            useTime = 600;
            range = 500;
            healPercent = 20;
            consumePower(20);
            consumeItems(with(MPTv2Items.coolingCell, 1)).boost();
            phaseBoost = 80;
            phaseRangeBoost = 40;
            requirements(Category.effect, with(MPTv2Items.largeMetrenFrame, 9, MPTv2Items.heavyArmorPlate, 9, MPTv2Items.multiCore, 3, MPTv2Items.metrenSilicon, 50, MPTv2Items.metren, 18));
        }};

        boostDriveProjector = new OverdriveProjector("boostDriveProjector"){{
            size = 3;
            health = 9000000;
            range = 320;
            speedBoost = 12;
            useTime =300F;
            consumePower(10);
            consumeItems(with(MPTv2Items.coolingCell, 1)).boost();
            hasBoost = true;
            requirements(Category.effect, with(MPTv2Items.largeMetrenFrame, 9, MPTv2Items.heavyArmorPlate, 9, MPTv2Items.multiCore, 3, MPTv2Items.metrenSilicon, 50, MPTv2Items.metren, 18));
        }};

        metrenShieldDome = new ForceProjector("metrenShieldDome"){{
            size = 3;
            health = 9000000;
            shieldHealth = 60000f;
            radius = 400;
            cooldownNormal = 20;
            cooldownLiquid = 8;
            cooldownBrokenBase = 16;
            consumePower(15);
            consumeItems(with(MPTv2Items.coolingCell, 1)).boost();
            phaseUseTime = 200;
            phaseRadiusBoost = 200F;
            phaseShieldBoost = 40000;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 9, MPTv2Items.armorPlate, 9, MPTv2Items.multiCore, 3, MPTv2Items.metrenSilicon, 50, MPTv2Items.metren, 18));
        }};

        buildBlock = new BuildTurret("buildBlock"){{
            size = 3;
            health = 9000000;
            buildSpeed = 5;
            range = 800;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 9, MPTv2Items.turretFrame, 9, MPTv2Items.armorPlate, 9, MPTv2Items.metren, 18));
        }};
        loadCore();
        //storage
        metrenContainer = new StorageBlock("metrenContainer"){{
            size = 5;
            health = 25000000;
            itemCapacity = 7500000;
            requirements(Category.effect, with(MPTv2Items.largeMetrenFrame, 4, MPTv2Items.metrenFrame, 9, MPTv2Items.heavyArmorPlate, 5, MPTv2Items.armorPlate, 9, MPTv2Items.metren, 50));
        }};

        metrenUnloader = new Unloader("metrenUnloader"){{
            size = 1;
            health = 1000000;
            requirements(Category.effect, with(MPTv2Items.metren, 8));
        }};
    }

    private static void loadCore() {
        coreMetren = new CoreBlock("coreMetren"){{
            size = 6;
            health = 72000000;
            armor = 2500;
            itemCapacity = 2000000;
            unitType = MPTv2UnitTypes.metre;
//            unitType = UnitTypes.gamma;
            incinerateNonBuildable = true;
            unitCapModifier = 20;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 36, MPTv2Items.armorPlate, 36, MPTv2Items.metren, 288));
        }};

        coreAdvance = new CoreBlock("coreAdvance"){{
            size = 7;
            health = 98000000;
            armor = 5000;
            itemCapacity = 4000000;
            unitType = MPTv2UnitTypes.metre;
//            unitType = UnitTypes.gamma;
            incinerateNonBuildable = true;
            unitCapModifier = 30;
            requirements(Category.effect, with(MPTv2Items.largeMetrenFrame, 49, MPTv2Items.heavyArmorPlate, 49,MPTv2Items.metren, 392));
        }};

        coreExperimental = new CoreBlock("coreExperimental"){{
            size = 8;
            health = 128000000;
            itemCapacity = 8000000;
            unitType = MPTv2UnitTypes.metre;
//            unitType = UnitTypes.gamma;
            incinerateNonBuildable = true;
            unitCapModifier = 40;
            requirements(Category.effect, with(MPTv2Items.largeMetrenFrame, 64, MPTv2Items.heavyArmorPlate, 64, MPTv2Items.metren, 512));
        }};

        coreEmperorOfAntimatter = new CoreBlock("coreEmperorOfAntimatter"){{
            size = 9;
            health = 162000000;
            itemCapacity = 16000000;
            unitType = MPTv2UnitTypes.metre;
//            unitType = UnitTypes.gamma;
            incinerateNonBuildable = true;
            unitCapModifier = 60;
            requirements(Category.effect, with(MPTv2Items.specialMetrenFrame, 81, MPTv2Items.specialArmorPlate, 81, MPTv2Items.metren, 648));
        }};

        coreSDU = new CoreBlock("coreSDU"){{
            size = 16;
            health = 512000000;
            itemCapacity = 2000000000;
            unitType = MPTv2UnitTypes.aoe;
            incinerateNonBuildable = true;
            unitCapModifier = 120;
            requirements(Category.effect, with(MPTv2Items.specialMetrenFrame, 256, MPTv2Items.specialArmorPlate, 256, MPTv2Items.metren, 8900));
        }};
    }

    public static void load(){
        loadResearchCenter();

        loadFactory();

        titaniumAlloyWall = new Wall("titaniumAlloyWall"){{
            size = 1;
            health = 1500;
            armor = 15;

            requirements(Category.defense, with(MPTv2Items.titaniumAlloy, 8));
        }};

        titaniumAlloyWallLarge = new Wall("titaniumAlloyWallLarge"){{
            size = 2;
            health = 4500;
            armor = 45;

            requirements(Category.defense, with(MPTv2Items.titaniumAlloy, 32));
        }};

        metrenWall = new Wall("metrenWall"){{
            size = 1;
            health = 2000000;
            requirements(Category.defense, with(MPTv2Items.metren, 8));
        }};

        metrenWallLarge = new Wall("metrenWallLarge"){{
            size = 2;
            health = 8000000;
            armor = 200f;
            requirements(Category.defense, with(MPTv2Items.metren, 32));
        }};

        loadTurrets();

        loadDrill();

        loadDistribution();

        loadLiquid();

        loadPower();

        loadUnits();

        loadEffects();

        superPowerSource = new PowerSource("superPowerSource"){{
            size = 1;
            health = 1000000;
            powerProduction = 999999999;
            buildVisibility = BuildVisibility.sandboxOnly;
            category = Category.power;
        }};

        superItemSource = new ItemSource("superItemSource"){{
            size = 1;
            health = 1000000;
            buildVisibility = BuildVisibility.sandboxOnly;
            category = Category.distribution;
        }};

        superLiquidSource = new LiquidSource("superLiquidSource"){{
            size = 1;
            health = 1000000;
            buildVisibility = BuildVisibility.sandboxOnly;
            category = Category.liquid;
        }};
    }
}
