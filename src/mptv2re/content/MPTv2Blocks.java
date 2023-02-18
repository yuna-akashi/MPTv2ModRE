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
import mindustry.entities.bullet.ShrapnelBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.*;

import static mindustry.type.ItemStack.with;

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
import mindustry.world.blocks.units.*;
import mindustry.world.draw.*;
import mindustry.world.meta.BuildVisibility;
import mindustry.world.meta.Env;
import mptv2re.expand.block.turrets.RailgunTurret;

public class MPTv2Blocks{
    public static Block
            //*wall*/
            titaniumAlloyWall, titaniumAlloyWallLarge, metrenWall, metrenWallLarge, wallTurret,

            //turrets
            assaultCannon, missileSilo, defendTurret, railgun, multiRailgun, guardian,
            antimatterRailgun, antimatterBlaster, antimatterShockwaveCannon,

            //*drill*/
            titaniumAlloyDrill, metrenDrill,

            //*distribution*/
            superItemSource, metrenConveyor, metrenBridgeConveyor, metrenRouter, metrenJunction,

            //*liquids*/
            superLiquidSource, metrenConduit, metrenBridgeConduit, metrenLiquidRouter, metrenLiquidJunction, metrenLiquidTank,

            //^factory*/
            //base
            titaniumAlloySmelter, carbideAlloyArcSmelter, metrenSmelter, metrenArcSmelter, multiMetrenSmelter, multiMetrenArcSmelter,
            metrenFrameCrafter, turretFrameCrafter, armorPlateCrafter, multiBuildCrafter,
            antimatterFrameCrafter, antimatterTurretFrameCrafter, antimatterArmorPlateCrafter, multiAntiBuildCrafter,

            metrenGlassSmelter, metrenDiamondCompressor, metrenSiliconSmelter,
            thoriumCompressor,
            metrenAmmoCrafter, metrenExplosiveAmmoCrafter, multiCoreGenerator,
            cellFactory, hydrogenIsotopeChamber, antimatterGenerator,

            //*power*/
            metrenReactor, nuclearFusionReactor, antimatterReactor,
            metrenNode, metrenLargeNode, metrenTowerNode,
            metrenSRBeamNode, metrenHRBeamNode, metrenBeamTower,
            metrenBattery, largeMetrenBattery, powerCondenser, metrenBeamCondenser,
            superPowerSource,

            //*units*/
            roombaFactory, metrenUnitFactory, antimatteredUnitFactory,
            metrenAdditiveReconstructor, metrenMultiplicativeReconstructor,
            //assembler
            ayuAssembler, pemuAssembler, antimatteredAssembler, metrenAssemblerModule, antimatteredAssemblerModule,
            //constructor
            metrenConstructor, metrenLargeConstructor, smallMetrenDeconstructor, metrenDeconstructor,

            //*effect*/
            metrender, boostDriveProjector, metrenShieldDome, metrenRadar, buildBlock, metrenUnloader,
            //*storage*/
            metrenContainer,
            //*core*/
            coreMetren, coreAdvance, coreExperimental, coreEmperorOfAntimatter, coreSDU
    ;

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

            requirements(Category.turret, with( MPTv2Items.turretFrame, 72, MPTv2Items.armorPlate, 72));
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
            requirements(Category.turret, BuildVisibility.shown, with( MPTv2Items.turretFrame, 32, MPTv2Items.armorPlate, 32));
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
            requirements(Category.turret, with( MPTv2Items.turretFrame, 200, MPTv2Items.armorPlate, 200, MPTv2Items.metrenSilicon, 120));
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

            requirements(Category.turret, with( MPTv2Items.turretFrame, 200, MPTv2Items.armorPlate, 200,MPTv2Items.multiCore, 5, MPTv2Items.metrenSilicon, 240));
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

            requirements(Category.turret,with( MPTv2Items.turretFrame, 2000, MPTv2Items.armorPlate, 2000));
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
/*          maxShootCharge = 10;
            chargeTimePer1Shot = 30f * 60f;*/
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

            requirements(Category.turret, with( MPTv2Items.antimatterTurretFrame, 2048, MPTv2Items.antimatterArmorPlate, 2048, MPTv2Items.metrenSilicon, 1200));
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

            requirements(Category.turret, with( MPTv2Items.antimatterTurretFrame, 2048, MPTv2Items.antimatterArmorPlate, 2048, MPTv2Items.metrenSilicon, 1200));
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

            requirements(Category.turret, with(MPTv2Items.antimatterTurretFrame, 2048, MPTv2Items.antimatterArmorPlate, 2048, MPTv2Items.metrenSilicon, 1200));
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

            requirements(Category.production, with(MPTv2Items.metren, 32));
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
            requirements(Category.liquid, with(MPTv2Items.metren, 80, MPTv2Items.metrenGlass, 48));
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

        carbideAlloyArcSmelter = new GenericCrafter("carbideAlloyArcSmelter"){{
            size = 3;
            hasPower = hasLiquids = true;

            craftTime = 50f;
            itemCapacity = 30;

            consumePower(8f);
            consumeLiquid(Liquids.slag, 0.3f);
            consumeItems(with(Items.beryllium, 1, Items.graphite, 1, Items.carbide, 2));
            outputItem = new ItemStack(MPTv2Items.carbideAlloy, 2);

            craftEffect = Fx.none;
            envEnabled |= Env.space | Env.underwater;
            envDisabled = Env.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawArcSmelt(), new DrawDefault());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;
            fogRadius = 3;

            requirements(Category.crafting, with(Items.beryllium, 480, Items.tungsten, 360));
        }};

        metrenSmelter = new GenericCrafter("metrenSmelter"){{
            size = 3;
            health = 320;
            hasItems = hasPower = true;
            itemCapacity = 120;
            craftTime = 2f * 60f;

            consumePower(6);
            consumeItems(with(Items.graphite, 1, Items.surgeAlloy, 1, MPTv2Items.titaniumAlloy, 2));
            outputItems = with(MPTv2Items.metren, 1);

            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
            ambientSound = Sounds.smelter;
            craftEffect = Fx.smeltsmoke;

            requirements(Category.crafting, with(Items.copper, 156, Items.lead, 200, MPTv2Items.titaniumAlloy, 120));
        }};//done

        metrenArcSmelter = new GenericCrafter("metrenArcSmelter"){{
            size = 3;
            hasPower = hasLiquids = true;
            health = 320;

            craftTime = 2f *60f;
            itemCapacity = 30;

            consumePower(12f);
            consumeLiquid(Liquids.slag, 0.3f);
            consumeItems(with(Items.graphite, 1, Items.surgeAlloy, 1, MPTv2Items.carbideAlloy, 2));
            outputItem = new ItemStack(MPTv2Items.metren, 1);

            craftEffect = Fx.none;
            envEnabled |= Env.space | Env.underwater;
            envDisabled = Env.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawArcSmelt(), new DrawDefault());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;
            fogRadius = 3;

            requirements(Category.crafting, with(Items.beryllium, 480, Items.tungsten, 360, MPTv2Items.carbideAlloy, 120));
        }};

        multiMetrenSmelter = new GenericCrafter("multiMetrenSmelter"){{
            size = 5;
            health = 2500;

            hasPower = hasLiquids = true;
            craftTime = 5f * 60f;
            itemCapacity = 30;

            consumePower(24);
            consumeLiquid(Liquids.slag, 0.5f);
            consumeItems(with(Items.graphite, 2, Items.surgeAlloy, 2, MPTv2Items.titaniumAlloy, 2));
            outputItem = new ItemStack(MPTv2Items.metren, 6);

            craftEffect = Fx.none;
            envEnabled |= Env.space | Env.underwater;
            envDisabled = Env.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidRegion(Liquids.slag), new DrawArcSmelt(), new DrawDefault());
            fogRadius = 9;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;

            requirements(Category.crafting, with(Items.copper, 156, Items.lead, 200, MPTv2Items.titaniumAlloy, 120, MPTv2Items.multiCore, 5));
        }};

        multiMetrenArcSmelter = new GenericCrafter("multiMetrenArcSmelter"){{
            size = 5;
            health = 2500;

            hasPower = hasLiquids = true;
            craftTime = 5f * 60f;
            itemCapacity = 30;

            consumePower(24);
            consumeLiquid(Liquids.slag, 0.5f);
            consumeItems(with(Items.graphite, 2, Items.surgeAlloy, 2, MPTv2Items.carbideAlloy, 2));
            outputItems = with(MPTv2Items.metren, 6);

            craftEffect = Fx.none;
            envEnabled |= Env.space | Env.underwater;
            envDisabled = Env.none;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"), new DrawCircles(){{
                        color = Color.valueOf("ffc073").a(0.24f);
                        strokeMax = 2.5f;
                        radius = 10f;
                        amount = 3;
                    }}, new DrawLiquidRegion(Liquids.slag), new DrawDefault(),
                    new DrawHeatRegion(){{
                        color = Color.valueOf("ff6060ff");
                    }},
                    new DrawHeatRegion("-vents"){{
                        color.a = 1f;
                    }}
            );
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;
            fogRadius = 9;

            requirements(Category.crafting, with(Items.beryllium, 480, Items.tungsten, 360, MPTv2Items.carbideAlloy, 120, MPTv2Items.multiCore, 5));
        }};

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
            health = 2000000;
            hasItems = hasPower = true;

            craftTime = 2f * 60f;

            consumePower(2.5f);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.metrenFrame, 1);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metren, 32, MPTv2Items.metrenDiamond, 8));
        }};

        turretFrameCrafter = new GenericCrafter("turretFrameCrafter"){{
            size = 2;
            health =2000000;
            hasItems = hasPower = true;

            craftTime = 2f * 60f;

            consumePower(2.5f);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 2));
            outputItems = with(MPTv2Items.turretFrame, 2);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metren, 32, MPTv2Items.metrenDiamond, 8));
        }};

        armorPlateCrafter = new GenericCrafter("armorPlateCrafter"){{
            size = 2;
            health = 2000000;
            hasItems = hasPower = true;

            craftTime = 2f * 60f;

            consumePower(2.5f);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.armorPlate, 1);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metren, 32, MPTv2Items.metrenDiamond, 8));
        }};

        multiBuildCrafter = new GenericCrafter("multiBuildCrafter"){{
            size = 3;
            health = 3000000;

            hasItems = hasPower = true;
            craftTime = 4f * 60f;
            itemCapacity = 72;

            consumePower(4);
            consumeItems(with(MPTv2Items.metren, 36, MPTv2Items.metrenDiamond, 18));
            outputItems = with(MPTv2Items.metrenFrame, 3, MPTv2Items.turretFrame, 3, MPTv2Items.armorPlate, 3);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metren, 72, MPTv2Items.metrenDiamond, 18, MPTv2Items.multiCore, 5));
        }};

        antimatterFrameCrafter = new GenericCrafter("antimatterFrameCrafter"){{
            size = 3;
            health = 3000000;
            hasItems = hasPower = true;
            itemCapacity = 36;

            craftTime = 2f * 60f;

            consumePower(12f);
            consumeItems(with(MPTv2Items.metrenFrame, 9, MPTv2Items.antimatterCell, 12, MPTv2Items.metrenDiamond, 8));
            outputItems = with(MPTv2Items.antimatterFrame, 1);
            craftTime = 10f * 60f;

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 720, MPTv2Items.armorPlate, 720, MPTv2Items.metrenDiamond, 120));
        }};

        antimatterTurretFrameCrafter = new GenericCrafter("antimatterTurretFrameCrafter"){{
            size = 3;
            health = 3000000;
            hasItems = hasPower = true;
            itemCapacity = 36;

            craftTime = 2f * 60f;

            consumePower(12f);
            consumeItems(with(MPTv2Items.turretFrame, 9, MPTv2Items.antimatterCell, 12, MPTv2Items.metrenDiamond, 8, MPTv2Items.metrenSilicon, 8));
            outputItems = with(MPTv2Items.antimatterTurretFrame, 1);
            craftTime = 10f * 60f;

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 720, MPTv2Items.armorPlate, 720, MPTv2Items.metrenDiamond, 120));
        }};

        antimatterArmorPlateCrafter = new GenericCrafter("antimatterArmorPlateCrafter"){{
            size = 3;
            health = 3000000;
            hasItems = hasPower = true;
            itemCapacity = 36;

            craftTime = 2f * 60f;

            consumePower(12f);
            consumeItems(with(MPTv2Items.armorPlate, 9, MPTv2Items.antimatterCell, 12, MPTv2Items.metrenDiamond, 6));
            outputItems = with(MPTv2Items.antimatterArmorPlate, 1);
            craftTime = 10f * 60f;

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 720, MPTv2Items.armorPlate, 720, MPTv2Items.metrenDiamond, 120));
        }};

        multiAntiBuildCrafter = new GenericCrafter("multiAntiBuildCrafter"){{
            size = 4;
            health = 8000000;
            itemCapacity = 216;

            craftTime = 4f * 60f;

            consumePower(16);
            consumeItems(with(MPTv2Items.metren, 108, MPTv2Items.antimatterCell, 36, MPTv2Items.metrenDiamond, 72));
            outputItems = with(MPTv2Items.antimatterFrame, 3, MPTv2Items.antimatterTurretFrame, 3, MPTv2Items.antimatterArmorPlate, 3);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 720, MPTv2Items.armorPlate, 720, MPTv2Items.metrenDiamond, 120, MPTv2Items.multiCore, 50));
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

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.metren, 18));
        }};

        thoriumCompressor = new GenericCrafter("thoriumCompressor"){{
            size = 2;
            health = 900;
            craftTime = 5f * 60f;

            consumePower(3);
            consumeItems(with(Items.thorium, 3));
            outputItems = with(MPTv2Items.compressedThorium, 1);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 32, MPTv2Items.armorPlate, 32, MPTv2Items.metrenDiamond,  18));
        }};

        metrenAmmoCrafter = new GenericCrafter("metrenAmmoCrafter"){{
            size = 2;
            health = 2000000;
            craftTime = 3.75f * 60f;

            consumePower(2.5f);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.metrenAmmo, 2);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 32, MPTv2Items.armorPlate, 32, MPTv2Items.metrenDiamond, 8));
        }};

        metrenExplosiveAmmoCrafter = new GenericCrafter("metrenExplosiveAmmoCrafter"){{
            size = 2;
            health = 320;
            craftTime = 5f * 60f;

            consumePower(3);
            consumeItems(with(MPTv2Items.compressedThorium, 3, MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.metrenExplosiveAmmo, 2);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 32, MPTv2Items.armorPlate, 32, MPTv2Items.metrenDiamond, 24));
        }};

        multiCoreGenerator = new GenericCrafter("multiCoreGenerator"){{
            size = 3;
            health = 3000000;

            craftTime = 12.5f * 60f;

            consumePower(20);
            consumeItems(with(MPTv2Items.cell, 1));
            outputItems = with(MPTv2Items.multiCore, 1);

            craftEffect = Fx.none;
            envEnabled |= Env.space | Env.underwater;
            envDisabled = Env.none;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawArcSmelt(), new DrawDefault());
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;
            fogRadius = 9;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.metrenSilicon, 80));
        }};

        hydrogenIsotopeChamber = new GenericCrafter("hydrogenIsotopeChamber"){{
            size = 7;
            health = 16000000;
            hasItems = hasLiquids = hasPower = true;
            itemCapacity = 40;
            craftTime = 10.54f * 60f;

            consumePower(4.5f);
            consumeItems(with(MPTv2Items.cell, 3));
            consumeLiquids(LiquidStack.with(Liquids.water, 0.5f));
            outputItems = with(MPTv2Items.tritiumCell, 1, MPTv2Items.oxygenCell, 1, MPTv2Items.oxygenCell, 1);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 392, MPTv2Items.armorPlate, 392, MPTv2Items.metrenGlass, 30, MPTv2Items.metrenSilicon, 20));
        }};

        antimatterGenerator = new GenericCrafter("antimatterGenerator"){{
            size = 9;
            health = 81000000;
            hasItems = hasPower = true;
            itemCapacity = 80;
            craftTime = 20f * 60f;

            consumePower(25000000);
            consumeItems(with(MPTv2Items.deuteriumCell, 3, MPTv2Items.tritiumCell, 2));
            outputItems = with(MPTv2Items.antimatterCell, 1, MPTv2Items.cell, 4);

            craftEffect = Fx.pulverizeMedium;

            requirements(Category.crafting, with(MPTv2Items.metrenFrame, 8100, MPTv2Items.armorPlate, 8100, MPTv2Items.metrenSilicon, 2000, MPTv2Items.metren, 648));
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
            requirements(Category.power, with(MPTv2Items.metrenFrame, 8, MPTv2Items.armorPlate, 8, Items.lead, 2));
        }};//done

        metrenLargeNode = new PowerNode("metrenLargeNode"){{
            size = 2;
            health = 4000000;
            maxNodes = 8;
            laserRange = 36;
            hasPower = true;
            autolink= false;
            requirements(Category.power, with(MPTv2Items.metrenFrame, 32, MPTv2Items.armorPlate, 32, Items.lead, 8));
        }};//done

        metrenTowerNode = new PowerNode("metrenTowerNode"){{
            size = 2;
            health = 4000000;
            maxNodes = 3;
            laserRange = 320;
            hasPower = true;
            autolink = false;
            requirements(Category.power, with(MPTv2Items.metrenFrame, 32, MPTv2Items.armorPlate, 32, MPTv2Items.metrenSilicon, 3));
        }};//done
        metrenSRBeamNode = new BeamNode("metrenSRBeamNode"){{
            size = 1;
            health = 500000;
            consumesPower = outputsPower = true;
            range = 8;
            fogRadius = 1;
            consumePowerBuffered(1000f);

            requirements(Category.power, with(MPTv2Items.metrenFrame, 8, MPTv2Items.armorPlate, 8, Items.beryllium, 2));
        }};

        metrenHRBeamNode = new BeamNode("metrenHRBeamNode"){{
            size = 1;
            health = 500000;
            consumesPower = outputsPower = true;
            range = 24;
            fogRadius = 1;
            consumePowerBuffered(1000f);

            requirements(Category.power, with(MPTv2Items.metrenFrame, 8, MPTv2Items.armorPlate, 8, MPTv2Items.metrenSilicon, 20, Items.beryllium, 2));
        }};

        metrenBeamTower = new BeamNode("metrenBeamTower"){{
            size = 3;
            health = 4500000;
            consumesPower = outputsPower = true;
            range = 40;
            fogRadius = 1;
            consumePowerBuffered(40000f);

            requirements(Category.power, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, Items.tungsten, 60));
        }};

        //battery
        metrenBattery = new Battery("metrenBattery"){{
            size = 1;
            health = 1000000;
            hasPower = true;
            consumePowerBuffered(500000);
            requirements(Category.power, with(MPTv2Items.metrenFrame, 8, MPTv2Items.armorPlate, 8, Items.lead, 4));
        }};//done

        largeMetrenBattery = new Battery("metrenLargeBattery"){{
            size = 3;
            health = 9000000;
            hasPower = true;
            consumePowerBuffered(5000000);
            requirements(Category.power, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, Items.lead, 4));
        }};

        powerCondenser = new Battery("powerCondenser"){{
            size = 5;
            health = 25000000;
            hasPower = true;
            consumePowerBuffered(2147483647);
            requirements(Category.power, with(MPTv2Items.metrenFrame, 2500, MPTv2Items.armorPlate, 2500, MPTv2Items.metrenSilicon, 40, Items.lead, 60));
        }};

        metrenBeamCondenser = new BeamNode("metrenBeamCondenser"){{
            size = 5;
            health = 12500000;
            consumesPower = outputsPower = true;
            range = 6;
            fogRadius = 1;
            consumePowerBuffered(400000000f);

            requirements(Category.power, with(MPTv2Items.metrenFrame, 250, MPTv2Items.armorPlate, 250, MPTv2Items.metrenSilicon, 320, Items.tungsten, 160));
        }};

        metrenReactor = new NuclearReactor("metrenReactor"){{
            size = 4;
            health = 16000000;

            hasItems = hasPower = hasLiquids = true;
            itemCapacity = 80;
            liquidCapacity = 600f;
            itemDuration = 300f;
            powerProduction = 2000f;
            baseExplosiveness = 1000000;
            heating = 0.2f;
            explosionRadius = 56;
            explosionDamage = 99999999;
            smokeThreshold = 0.5f;
            flashThreshold = 0.75f;

            coolantPower = 0.05f;
            consumeItems(with(MPTv2Items.compressedThorium, 1));
            consumeLiquid(Liquids.cryofluid, 1f);

            requirements(Category.power, with(MPTv2Items.metrenFrame, 128, MPTv2Items.armorPlate, 128, MPTv2Items.metrenSilicon, 120));
        }};

        nuclearFusionReactor = new ImpactReactor("nuclearFusionReactor"){{
            size = 7;
            health = 49000000;
            itemCapacity = 120;
            liquidCapacity = 750f;

            powerProduction = 10000;
            consumePower(500);
            consumeItems(with(MPTv2Items.deuteriumCell, 1, MPTv2Items.tritiumCell, 1));
            consumeLiquid(Liquids.cryofluid, 0.15f / 90f);

            requirements(Category.power, with(MPTv2Items.metrenFrame, 392, MPTv2Items.armorPlate, 392, MPTv2Items.metrenSilicon, 240));
        }};

        antimatterReactor = new ImpactReactor("antimatterReactor"){{
            size = 11;
            health = 121000000;
            itemCapacity = 120;
            liquidCapacity = 750f;

            powerProduction = 2147483647;
            consumePower(500);
            consumeItems(with(MPTv2Items.deuteriumCell, 1, MPTv2Items.tritiumCell, 1, MPTv2Items.antimatterCell, 2));
            consumeLiquid(Liquids.cryofluid, 0.15f / 90f);
            requirements(Category.power, with(MPTv2Items.antimatterFrame, 968, MPTv2Items.antimatterArmorPlate, 968, MPTv2Items.metrenSilicon, 360));
        }};//done
    }

    private static void loadUnits() {
        roombaFactory = new UnitFactory("roombaFactory"){{
            size = 3;
            health = 3000000;

            consumePower(3f);

            plans = Seq.with(
                    new UnitPlan(MPTv2UnitTypes.roomba, 30f * 60f, with(MPTv2Items.metrenSilicon, 40)),
                    new UnitPlan(MPTv2UnitTypes.miningRoomba, 30f * 60f, with(MPTv2Items.metrenSilicon, 40))
            );

            requirements(Category.units, with(MPTv2Items.metren, 72,MPTv2Items.metrenSilicon, 80));
        }};

        metrenUnitFactory = new UnitFactory("metrenUnitFactory"){{
            size = 3;
            health = 3000000;

            consumePower(3f);

            plans = Seq.with(
                    new UnitPlan(MPTv2UnitTypes.ayu, 30f * 60f, with(MPTv2Items.metrenSilicon, 60)),
                    new UnitPlan(MPTv2UnitTypes.pemu, 90f * 60f, with(MPTv2Items.metren, 300, MPTv2Items.metrenSilicon, 200))
            );

            requirements(Category.units, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.metrenSilicon, 80));
        }};

        antimatteredUnitFactory = new UnitFactory("antimatteredUnitFactory"){{
            size = 3;
            health = 9000000;

            configurable = false;
            plans.add(new UnitPlan(MPTv2UnitTypes.beast, 27f * 60f, with(MPTv2Items.metren, 20, MPTv2Items.metrenSilicon, 10,MPTv2Items.metrenDiamond, 8)));
            consumePower(4f);

            requirements(Category.units, with(MPTv2Items.antimatterFrame, 72, MPTv2Items.antimatterArmorPlate, 72, MPTv2Items.metrenSilicon, 220));
        }};

        metrenAdditiveReconstructor = new Reconstructor("metrenAdditiveReconstructor"){{
            size = 3;
            health = 9000000;

            consumePower(4.5f);
            consumeItems(with(MPTv2Items.armorPlate, 10, MPTv2Items.metrenSilicon, 20));

            constructTime = 35f * 60f;

            upgrades.addAll(
                    //attack roomba
                    new UnitType[]{MPTv2UnitTypes.roomba, MPTv2UnitTypes.attackRoomba},

                    new UnitType[]{MPTv2UnitTypes.jibakuRoomba, MPTv2UnitTypes.jibakuNukeRoomba},
                    //special roomba
                    new UnitType[]{MPTv2UnitTypes.miningRoomba, MPTv2UnitTypes.repairRoomba},
                    //ayu
                    new UnitType[]{MPTv2UnitTypes.ayu, MPTv2UnitTypes.mino},
                    //antimatter
                    new UnitType[]{MPTv2UnitTypes.beast, MPTv2UnitTypes.matter}
            );

            requirements(Category.units, with(MPTv2Items.antimatterFrame, 72, MPTv2Items.antimatterArmorPlate, 72, MPTv2Items.metrenSilicon, 260));
        }};

        metrenMultiplicativeReconstructor = new Reconstructor("metrenMultiplicativeReconstructor"){{
            size = 5;
            health = 25000000;

            consumePower(5f);
            consumeItems(with(MPTv2Items.armorPlate, 40, MPTv2Items.metrenSilicon, 20));

            constructTime = 45f * 60f;

            upgrades.addAll(
                    //roomba
                    new UnitType[]{MPTv2UnitTypes.attackRoomba, MPTv2UnitTypes.jibakuRoomba},
                    //ayu
                    new UnitType[]{MPTv2UnitTypes.mino, MPTv2UnitTypes.ami},
                    //antimatter
                    new UnitType[]{MPTv2UnitTypes.matter, MPTv2UnitTypes.ecru}
            );

            requirements(Category.units, with(MPTv2Items.antimatterFrame, 200, MPTv2Items.antimatterArmorPlate, 200, MPTv2Items.metrenSilicon, 320));
        }};

        ayuAssembler = new UnitAssembler("ayuAssembler"){{
            size = 5;
            health = 12500000;

            droneType = MPTv2UnitTypes.metrenAssemblyDrone;

            plans.add(
                    new AssemblerUnitPlan(MPTv2UnitTypes.meru, 60f *60f, PayloadStack.list(MPTv2UnitTypes.mino, 8, MPTv2Blocks.metrenWall, 10)),
                    new AssemblerUnitPlan(MPTv2UnitTypes.nimu, 90f * 60f, PayloadStack.list(MPTv2UnitTypes.ami, 10, MPTv2Blocks.metrenWallLarge, 20))
            );

            consumePower(5.5f);
            consumeLiquid(Liquids.cryofluid, 2f);

            requirements(Category.units, with(MPTv2Items.metrenFrame, 200, MPTv2Items.armorPlate, 200, MPTv2Items.metrenSilicon, 120));
        }};

        pemuAssembler = new UnitAssembler("pemuAssembler"){{
            size = 5;
            health = 12500000;

            areaSize = 65;
            droneType = MPTv2UnitTypes.metrenAssemblyDrone;

            plans.add(
                    new AssemblerUnitPlan(MPTv2UnitTypes.pemuCarrier, 360f * 60f, PayloadStack.list(MPTv2UnitTypes.pemu, 20, MPTv2Blocks.metrenWallLarge, 200))
            );

            consumePower(7f);
            consumeLiquid(Liquids.cryofluid, 4f);

            requirements(Category.units, with(MPTv2Items.metrenFrame, 200, MPTv2Items.armorPlate, 200, MPTv2Items.metrenSilicon, 480));
        }};

        antimatteredAssembler = new UnitAssembler("antimatteredAssembler"){{
            size = 7;
            health = 49000000;

            areaSize = 50;
            droneType = MPTv2UnitTypes.metrenAssemblyDrone;

            plans.add(
                    new AssemblerUnitPlan(MPTv2UnitTypes.ecru, 60f * 60f, PayloadStack.list(MPTv2UnitTypes.beast, 8, MPTv2Blocks.metrenWall, 80)),
                    new AssemblerUnitPlan(MPTv2UnitTypes.eter, 360f * 60f, PayloadStack.list(MPTv2UnitTypes.matter, 8, MPTv2Blocks.metrenWall, 240)),
                    new AssemblerUnitPlan(MPTv2UnitTypes.destAllier, 600f * 60f, PayloadStack.list(MPTv2UnitTypes.ecru, 10, MPTv2Blocks.metrenWallLarge, 480))
            );

            consumePower(8);
            consumeLiquid(Liquids.cryofluid,  12f / 60f);

            requirements(Category.units, with(MPTv2Items.antimatterFrame, 392, MPTv2Items.antimatterArmorPlate, 392, MPTv2Items.metrenSilicon, 380));
        }};

        metrenAssemblerModule = new UnitAssemblerModule("metrenAssemblerModule"){{
            size = 5;
            health = 25000000;

            tier = 1;

            consumePower(6.5f);

            requirements(Category.units, with(MPTv2Items.metrenFrame, 200, MPTv2Items.armorPlate, 200, MPTv2Items.metrenSilicon, 420));
        }};

        antimatteredAssemblerModule = new UnitAssemblerModule("antimatteredAssemblerModule"){{
            size = 5;
            health = 25000000;

            tier = 2;

            consumePower(6.5f);

            requirements(Category.units, with(MPTv2Items.antimatterFrame, 200, MPTv2Items.antimatterArmorPlate, 200, MPTv2Items.metrenSilicon, 420));
        }};

        metrenConstructor = new Constructor("metrenConstructor"){{
            size = 3;
            health = 9000000;

            consumePower(3);
            buildSpeed = 0.65f;
            hasPower = true;

            filter = Seq.with(MPTv2Blocks.titaniumAlloyWall, MPTv2Blocks.titaniumAlloyWallLarge, MPTv2Blocks.metrenWall, MPTv2Blocks.metrenWallLarge);

            requirements(Category.units, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.metrenSilicon, 80));
        }};

        metrenLargeConstructor = new Constructor("metrenLargeConstructor"){{
            size = 7;
            health = 49000000;

            consumePower(6);
            buildSpeed = 0.7f;
            hasPower = true;

            maxBlockSize = 9;
            minBlockSize = 3;

            requirements(Category.units, with(MPTv2Items.metrenFrame, 392, MPTv2Items.armorPlate, 392, MPTv2Items.metrenSilicon, 160));
        }};

        smallMetrenDeconstructor = new PayloadDeconstructor("smallMetrenDeconstructor"){{
            size = 3;
            health = 9000000;

            itemCapacity = 250;
            consumePower(3);
            deconstructSpeed = 2f;

            requirements(Category.units, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.metrenSilicon, 80));
        }};

        metrenDeconstructor = new PayloadDeconstructor("metrenDeconstructor"){{
            size = 7;
            health = 49000000;

            itemCapacity = 500;
            consumePower(6);
            deconstructSpeed = 3f;

            requirements(Category.units, with(MPTv2Items.metrenFrame, 392, MPTv2Items.armorPlate, 392, MPTv2Items.metrenSilicon, 160));
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
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.multiCore, 3, MPTv2Items.metrenSilicon, 70));
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
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.multiCore, 3, MPTv2Items.metrenSilicon, 70));
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
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.multiCore, 3, MPTv2Items.metrenSilicon, 70));
        }};

        metrenRadar = new Radar("metrenRadar"){{
            size = 3;
            health = 3000000;

            outlineColor = Color.valueOf("4a4b53");
            fogRadius = 102;
            researchCost = with(Items.silicon, 70, Items.graphite, 70);

            consumePower(0.6f);

            requirements(Category.effect, BuildVisibility.fogOnly, with(MPTv2Items.metrenFrame, 72, MPTv2Items.armorPlate, 72, MPTv2Items.metrenSilicon, 160));
        }};

        buildBlock = new BuildTurret("buildBlock"){{
            size = 3;
            health = 9000000;

            buildSpeed = 5;
            range = 800;

            consumePower(12f);

            requirements(Category.effect, with(MPTv2Items.metrenFrame, 72, MPTv2Items.turretFrame, 72, MPTv2Items.armorPlate, 72));
        }};
    }

    private static void loadCoreStorage() {
        coreMetren = new CoreBlock("coreMetren"){{
            size = 6;
            health = 72000000;
            armor = 2500;
            itemCapacity = 2000000;
            unitType = MPTv2UnitTypes.cargoDrone;
//            unitType = UnitTypes.gamma;
            incinerateNonBuildable = true;
            unitCapModifier = 20;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 288, MPTv2Items.armorPlate, 288));
        }};

        coreAdvance = new CoreBlock("coreAdvance"){{
            size = 7;
            health = 98000000;
            armor = 5000;
            itemCapacity = 4000000;
            unitType = MPTv2UnitTypes.cargoDrone;
//            unitType = UnitTypes.gamma;
            incinerateNonBuildable = true;
            unitCapModifier = 30;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 392, MPTv2Items.armorPlate, 392));
        }};

        coreExperimental = new CoreBlock("coreExperimental"){{
            size = 8;
            health = 128000000;
            itemCapacity = 8000000;
            unitType = MPTv2UnitTypes.cargoDrone;
//            unitType = UnitTypes.gamma;
            incinerateNonBuildable = true;
            unitCapModifier = 40;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 512, MPTv2Items.armorPlate, 512));
        }};

        coreEmperorOfAntimatter = new CoreBlock("coreEmperorOfAntimatter"){{
            size = 9;
            health = 162000000;
            itemCapacity = 16000000;
            unitType = MPTv2UnitTypes.cargoDrone;
//            unitType = UnitTypes.gamma;
            incinerateNonBuildable = true;
            unitCapModifier = 60;
            requirements(Category.effect, with(MPTv2Items.antimatterFrame, 648, MPTv2Items.antimatterArmorPlate, 648));
        }};

        coreSDU = new CoreBlock("coreSDU"){{
            size = 16;
            health = 512000000;
            itemCapacity = 2000000000;
            unitType = MPTv2UnitTypes.aoe;
            incinerateNonBuildable = true;
            unitCapModifier = 120;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 25600, MPTv2Items.armorPlate, 25600));
        }};

        //storage
        metrenContainer = new StorageBlock("metrenContainer"){{
            size = 5;
            health = 25000000;
            itemCapacity = 7500000;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 200, MPTv2Items.armorPlate, 200));
        }};

        metrenUnloader = new Unloader("metrenUnloader"){{
            size = 1;
            health = 1000000;
            requirements(Category.effect, with(MPTv2Items.metren, 8));
        }};
    }

    public static void load(){
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

        wallTurret = new ItemTurret("wallTurret"){{
            size = 1;
            health = 2000000;
            reload = 30f;
            cooldownTime = 12f;
            range = 40;

            ammo(
                    Items.copper, new ShrapnelBulletType(){{
                        length = 40f;
                        damage = 200000000;
                        shootEffect = smokeEffect = Fx.thoriumShoot;
                    }},
                    MPTv2Items.antimatterCell, new ShrapnelBulletType(){{
                        length = 40f;
                        damage = 2000000000;
                        toColor = MPTv2Items.antimatterCell.color;
                        shootEffect = smokeEffect = Fx.thoriumShoot;
                    }}
            );

            drawer = new DrawTurret(){{
                parts.add(
                        new RegionPart("-cap-r"){{
                              under = turretShading = mirror = false;
                              moveX = 4f;
                              moveY = 0;
                              progress = PartProgress.warmup;
                        }},
                        new RegionPart("-cap-l"){{
                            under = turretShading = mirror = false;
                            moveX = -4;
                            moveY = 0;
                            progress = PartProgress.warmup;
                        }}
                );
            }};

            requirements(Category.defense, with(MPTv2Items.turretFrame, 800, MPTv2Items.armorPlate, 800));
        }};

        loadEffects();

        loadDistribution();
        superItemSource = new ItemSource("superItemSource"){{
            size = 1;
            health = 1000000;
            buildVisibility = BuildVisibility.sandboxOnly;
            category = Category.distribution;
        }};

        loadLiquid();
        superLiquidSource = new LiquidSource("superLiquidSource"){{
            size = 1;
            health = 1000000;
            buildVisibility = BuildVisibility.sandboxOnly;
            category = Category.liquid;
        }};

        loadPower();
        superPowerSource = new PowerSource("superPowerSource"){{
            size = 1;
            health = 1000000;
            powerProduction = 999999999;
            buildVisibility = BuildVisibility.sandboxOnly;
            category = Category.power;
        }};

        loadDrill();

        loadCoreStorage();

        loadTurrets();

        loadUnits();
    }
}
