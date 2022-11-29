package mptv2re.content;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.type.Category;

import static mindustry.type.ItemStack.with;

import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PointDefenseTurret;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.sandbox.ItemSource;
import mindustry.world.blocks.sandbox.LiquidSource;
import mindustry.world.blocks.sandbox.PowerSource;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.draw.DrawTurret;
import mindustry.world.meta.BuildVisibility;
import mptv2re.expand.block.drawer.MPTv2AntiRailCharge;
import mptv2re.expand.block.turrets.RailgunTurret;

public class MPTv2Blocks {
    public static Block
        //wall
        metrenWall, metrenWallLarge,

        //turrets
        assaultCannon, missileSilo, defendTurret, railgun, multiRailgun, guardian, emperorOfGuardian, antimatterRailgun,

        //distribution
        superItemSource,

        //liquids
        superLiquidSource,

        //base factory
        metrenSmelter, titaniumAlloySmelter,
        metrenFrameCrafter, largeMetrenFrameCrafter, specialMetrenFrameCrafter,
        turretFrameCrafter, largeTurretFrameCrafter, specialTurretFrameCrafter,
        armorPlateCrafter, heavyArmorPlateCrafter, specialArmorPlateCrafter,

        //metren's factory
        metrenGlassSmelter, metrenDiamondCompressor, metrenSiliconSmelter, antimatterGenerator,

        //power
        metrenReactor, deuteriumReactor, antimatterReactor, superPowerSource,
        metrenNode, metrenLargeNode, metrenTowerNode,
        metrenBattery, largeMetrenBattery, powerCondenser,

        //effect
        metrender, boostDriveProjector, metrenShieldDome, buildBlock,
        //storage
        metrenContainer,
        coreMetren, coreAdvance, coreExperimental, coreEmperorOfAntimatter
    ;
    private static void loadBaseFactory() {
        titaniumAlloySmelter = new GenericCrafter("titaniumAlloySmelter"){{
            size = 3;
            health = 320;
            hasItems = hasPower = hasLiquids = true;
            itemCapacity = 40;
            liquidCapacity = 80;
            craftTime = 72;
            consumePower(4.5f);
            consumeItems(with(Items.titanium, 25));
            consumeLiquids(LiquidStack.with(Liquids.slag, 0.25f));
            outputItems = with(MPTv2Items.titaniumAlloy, 4);
            requirements(Category.crafting, with(Items.copper, 156, Items.lead, 200, Items.titanium, 250));
        }};

        metrenSmelter = new GenericCrafter("metrenSmelter"){{
            size = 3;
            health = 320;
            hasItems = hasPower = true;
            itemCapacity = 120;
            craftTime = 72;
            consumePower(6);
            consumeItems(with(Items.graphite, 10,Items.lead, 15, Items.titanium, 25));
            outputItems = with(MPTv2Items.metren, 5);
            requirements(Category.crafting, with(Items.copper, 156, Items.lead, 200, MPTv2Items.titaniumAlloy, 120));
        }};

        metrenFrameCrafter = new GenericCrafter("metrenFrameCrafter"){{
            size = 2;
            health = 320;
            hasItems = hasPower = true;
            consumePower(4);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.metrenFrame, 1);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 40, Items.copper, 120, Items.lead, 80));
        }};

        largeMetrenFrameCrafter = new GenericCrafter("largeMetrenFrameCrafter"){{
            size = 3;
            health = 960;
            hasItems = hasPower = true;
            consumePower(8);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, Items.silicon, 4, MPTv2Items.metrenFrame, 4));
            outputItems = with(MPTv2Items.largeMetrenFrame, 1);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 80, Items.copper, 240, Items.lead, 160));
        }};

        specialMetrenFrameCrafter = new GenericCrafter("specialMetrenFrameCrafter"){{
            size = 4;
            health = 1280;
            hasItems = hasPower = true;
            consumePower(16);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 4, MPTv2Items.largeMetrenFrame, 4));
            outputItems = with(MPTv2Items.specialMetrenFrame, 1);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 120, Items.copper, 360, Items.lead, 240));
        }};

        turretFrameCrafter = new GenericCrafter("turretFrameCrafter"){{
            size = 2;
            health = 320;
            hasItems = hasPower = true;
            consumePower(4);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 2));
            outputItems = with(MPTv2Items.turretFrame, 2);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 40, Items.copper, 120, Items.lead, 80));
        }};

        largeTurretFrameCrafter = new GenericCrafter("largeTurretFrameCrafter"){{
            size = 3;
            health = 960;
            hasItems = hasPower = true;
            consumePower(8);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, Items.silicon, 4, MPTv2Items.metrenSilicon, 2, MPTv2Items.turretFrame, 4));
            outputItems = with(MPTv2Items.largeTurretFrame, 1);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 80, Items.copper, 240, Items.lead, 160));
        }};

        specialTurretFrameCrafter = new GenericCrafter("specialTurretFrameCrafter"){{
            size = 4;
            health = 1280;
            hasItems = hasPower = true;
            consumePower(16);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 6, MPTv2Items.largeMetrenFrame, 4));
            outputItems = with(MPTv2Items.specialMetrenFrame, 1);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 120, Items.copper, 360, Items.lead, 240));
        }};

        armorPlateCrafter = new GenericCrafter("armorPlateCrafter"){{
            size = 2;
            health = 320;
            hasItems = hasPower = true;
            consumePower(4);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.armorPlate, 1);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 40, Items.copper, 120, Items.lead, 80));
        }};

        heavyArmorPlateCrafter = new GenericCrafter("heavyArmorPlateCrafter"){{
            size = 3;
            health = 960;
            hasItems = hasPower = true;
            consumePower(8);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, Items.silicon, 4, MPTv2Items.armorPlate, 4));
            outputItems = with(MPTv2Items.heavyArmorPlate, 1);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 80, Items.copper, 240, Items.lead, 160));
        }};

        specialArmorPlateCrafter = new GenericCrafter("specialArmorPlateCrafter"){{
            size = 4;
            health = 1280;
            hasItems = hasPower = true;
            consumePower(16);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2, MPTv2Items.metrenSilicon, 4, MPTv2Items.heavyArmorPlate, 4));
            outputItems = with(MPTv2Items.specialArmorPlate, 1);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 120, Items.copper, 360, Items.lead, 240));
        }};
    }

    private static void loadFactory() {
        metrenGlassSmelter = new GenericCrafter("metrenGlassSmelter"){{
            size = 2;
            health = 4000000;
            hasItems = hasPower = true;
            itemCapacity = 40;
            consumePower(2);
            consumeItems(with(MPTv2Items.metren, 4, Items.sand, 4));
            outputItems = with(MPTv2Items.metrenGlass, 4);
            requirements(Category.crafting, with(MPTv2Items.metren, 32));
        }};

        metrenDiamondCompressor = new GenericCrafter("metrenDiamondCompressor"){{
            size = 2;
            health = 4000000;
            hasItems = hasPower = true;
            itemCapacity = 80;
            consumePower(2.5f);
            consumeItems(with(Items.graphite, 8));
            outputItems = with(MPTv2Items.metrenDiamond, 4);
            requirements(Category.crafting, with(MPTv2Items.metren, 32));
        }};

        metrenSiliconSmelter = new GenericCrafter("metrenSiliconSmelter"){{
            size = 2;
            health = 4000000;
            hasItems = hasPower = true;
            itemCapacity = 40;
            consumePower(2);
            consumeItems(with(MPTv2Items.metren, 4, Items.coal, 4, Items.sand, 4));
            outputItems = with(MPTv2Items.metrenSilicon, 4);
            requirements(Category.crafting, with(MPTv2Items.metren, 32));
        }};

        antimatterGenerator = new GenericCrafter("antimatterGenerator"){{
            size = 9;
            health = 81000000;
            hasItems = hasPower = true;
            itemCapacity = 80;
            consumePower(25000000);
            consumeItems(with(MPTv2Items.cell, 1));
            outputItems = with(MPTv2Items.antimatterCell, 1);
            requirements(Category.crafting, with(MPTv2Items.specialArmorPlate, 81, MPTv2Items.specialArmorPlate, 81, MPTv2Items.metrenSilicon, 2000, MPTv2Items.metren, 648));
        }};
    }

    private static void loadTurrets() {
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
            requirements(Category.turret, BuildVisibility.shown, with(MPTv2Items.metrenFrame, 4, MPTv2Items.turretFrame, 4, MPTv2Items.armorPlate, 4, MPTv2Items.metren, 8));
        }};

        railgun = new RailgunTurret("railgun"){{
            size = 5;
            health = 25000000;
            range = 4000;
            hasPower = true;
            reload = 1;
            ammo(
                    MPTv2Items.metrenAmmo, new BasicBulletType(30, 500){{
                        width = 10;
                        height = 15;
                    }}
            );
            requirements(Category.turret, with(MPTv2Items.largeMetrenFrame, 25, MPTv2Items.heavyArmorPlate, 25, MPTv2Items.metrenSilicon, 50, MPTv2Items.metren, 50));
        }};
        emperorOfGuardian = new ItemTurret("emperorOfGuardian"){{
            size = 11;
            health = 121000000;
            range = 12000;
            hasPower = true;
            shake = 0;
            reload = 50;
            canOverdrive = false;
            ammo(
                    MPTv2Items.metrenAmmo, new BasicBulletType(30, 9999999){{
                        width = 6;
                        height = 15;
                        hitColor = backColor = lightColor = trailColor = MPTv2Items.metrenAmmo.color.cpy().lerp(Color.white, 0.2f);
                        frontColor = backColor.cpy().lerp(Color.white, 0.55f);
                        ammoMultiplier = 20;
                        pierceArmor = true;
                    }},
                    MPTv2Items.emperorsCristal, new BasicBulletType(55, 999999999){{
                        width = 20;
                        height = 20;
                        frontColor = backColor = MPTv2Items.emperorsCristal.color.cpy().lerp(Color.white, 0.1f);
                        hitColor = lightColor = trailColor = MPTv2Items.emperorsCristal.color.cpy().lerp(Color.white, 0.4f);
                        ammoMultiplier = 1;
                        pierceArmor = true;
                        splashDamage = 150000;
                        splashDamageRadius = 500;
                    }}
            );
            maxAmmo = 400;
            ammoPerShot = 5;
            consumePower(2000000);
            requirements(Category.turret, with(MPTv2Items.specialMetrenFrame, 121, MPTv2Items.specialTurretFrame, 121, MPTv2Items.specialArmorPlate, 121, MPTv2Items.metren, 242));
        }};
        antimatterRailgun = new ItemTurret("antimatterRailgun"){{
            size = 16;
            health = 256000000;
            range = 160000;
            hasPower = true;
            reload = 240;
            recoil = 0;
            shootY = -11f;
            //maxShootCharge = 10;
            //chargeTimePer1Shot = 25;
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
                    progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                }},
                new RegionPart("-barrel-side"){{
                    under = true;
                    progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                }},
                new RegionPart("-barrel-center"){{
                     under = mirror = true;
                    layerOffset = -0.0025f;
                    moveX = -3.1f;
                    moveY = 25f;
                    y = 2;
                    x = 3f;
                    progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                }});
                //parts.add(new MPTv2AntiRailCharge){{
                //    progress = aa;
                // }}
            }};

            consumePower(500000000);
            //consumePowerCond(500000000, RailgunTurretBuild::isCharge);
            requirements(Category.turret, with(MPTv2Items.specialMetrenFrame, 256, MPTv2Items.specialTurretFrame, 256,MPTv2Items.specialArmorPlate, 256, MPTv2Items.metren, 512));
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
        }};

        metrenLargeNode = new PowerNode("metrenLargeNode"){{
            size = 2;
            health = 4000000;
            maxNodes = 8;
            laserRange = 36;
            hasPower = true;
            requirements(Category.power, with(MPTv2Items.largeMetrenFrame, 4, MPTv2Items.heavyArmorPlate, 4, MPTv2Items.metren, 8, Items.lead, 8));
        }};

        metrenTowerNode = new PowerNode("metrenTowerNode"){{
            size = 2;
            health = 4000000;
            maxNodes = 3;
            laserRange = 320;
            hasPower = true;
            requirements(Category.power, with(MPTv2Items.specialMetrenFrame, 4, MPTv2Items.specialArmorPlate, 4, MPTv2Items.metrenSilicon, 3, MPTv2Items.metren, 8));
        }};

        //battery
        metrenBattery = new Battery("metrenBattery"){{
            size = 1;
            health = 1000000;
            hasPower = true;
            consumePowerBuffered(500000);
            requirements(Category.power, with(MPTv2Items.metrenFrame, 1, MPTv2Items.armorPlate, 1, MPTv2Items.metren, 2, Items.lead, 4));
        }};

        largeMetrenBattery = new Battery("largeMetrenBattery"){{
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

//        metrenReactor = new NuclearReactor("metrenReactor"){{
//            size = 4;
//            health = 16000000;
//            hasItems = hasPower = hasLiquids = true;
//            itemCapacity = 80;
//            liquidCapacity = 600f;
//            itemDuration = 300f;
//            powerProduction = 2000;
//            baseExplosiveness = 1000000;
//            heating = 0.02f;
//            explosionRadius = 56;
//            explosionDamage = 99999999;
//            smokeThreshold = 0.5f;
//            flashThreshold = 0.75f;
//            coolantPower = 0.225f;
//            consumeItems(with(MPTv2Items.compressedThorium, 1));
//            consumeLiquid(MPTv2Liquids.meter, 0.55f / 100f);
//            requirements(Category.power, with(MPTv2Items.metrenFrame, 16, MPTv2Items.armorPlate, 16, MPTv2Items.metren, 32));
//        }};

//        deuteriumReactor = new ImpactReactor("deuteriumReactor"){{
//            size = 7;
//            health = 49000000;
//            itemCapacity = 120;
//            liquidCapacity = 750f;
//            powerProduction = 10000;
//            consumePower(500);
//            consumeItem(MPTv2Items.deuterium, 1);
//            consumeLiquid(MPTv2Liquids.meter, 0.15f / 90f);
//            requirements(Category.power, with(MPTv2Items.largeMetrenFrame, 49, MPTv2Items.heavyArmorPlate, 49, MPTv2Items.metrenSilicon, 80, MPTv2Items.metren, 98));
//        }};

        antimatterReactor = new ConsumeGenerator("antimatterReactor"){{
            size = 11;
            health = 121000000;
            itemCapacity = 210;
            powerProduction = 999999999;
            consumeItems(with(MPTv2Items.deuterium, 1, MPTv2Items.antimatterCell, 1));
            requirements(Category.power, with(MPTv2Items.specialMetrenFrame, 121, MPTv2Items.specialArmorPlate, 121, MPTv2Items.metrenSilicon, 180,MPTv2Items.metren, 242));
        }};
    }

    private static void loadEffects() {
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
        //storage
        metrenContainer = new StorageBlock("metrenContainer"){{
            size = 5;
            health = 25000000;
            itemCapacity = 7500000;
            requirements(Category.effect, with(MPTv2Items.largeMetrenFrame, 4, MPTv2Items.metrenFrame, 9, MPTv2Items.heavyArmorPlate, 5, MPTv2Items.armorPlate, 9, MPTv2Items.metren, 50));
        }};

        buildBlock = new BuildTurret("buildBlock"){{
            size = 3;
            health = 9000000;
            buildSpeed = 5;
            range = 1600;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 9, MPTv2Items.armorPlate, 9, MPTv2Items.metren, 18));
        }};
    }

    private static void loadCore() {
        coreMetren = new CoreBlock("coreMetren"){{
            size = 6;
            health = 72000000;
            itemCapacity = 2000000;
            unitType = UnitTypes.gamma;
            unitCapModifier = 25;
            requirements(Category.effect, with(MPTv2Items.metrenFrame, 36, MPTv2Items.armorPlate, 36, MPTv2Items.metren, 288));
        }};

        coreAdvance = new CoreBlock("coreAdvance"){{
            size = 7;
            health = 98000000;
            itemCapacity = 4000000;
            unitType = UnitTypes.gamma;
            unitCapModifier = 30;
            requirements(Category.effect, with(MPTv2Items.largeMetrenFrame, 49, MPTv2Items.heavyArmorPlate, 49,MPTv2Items.metren, 392));
        }};

        coreExperimental = new CoreBlock("coreExperimental"){{
            size = 8;
            health = 128000000;
            itemCapacity = 8000000;
            unitType = UnitTypes.gamma;
            unitCapModifier = 40;
            requirements(Category.effect, with(MPTv2Items.largeMetrenFrame, 64, MPTv2Items.heavyArmorPlate, 64, MPTv2Items.metren, 512));
        }};

        coreEmperorOfAntimatter = new CoreBlock("coreEmperorOfAntimatter"){{
            size = 9;
            health = 162000000;
            itemCapacity = 16000000;
            unitType = UnitTypes.gamma;
            unitCapModifier = 60;
            requirements(Category.effect, with(MPTv2Items.specialMetrenFrame, 81, MPTv2Items.specialArmorPlate, 81, MPTv2Items.metren, 648));
        }};
    }

    public static void load(){
        loadBaseFactory();
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
        loadPower();
        loadEffects();
        loadCore();

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
