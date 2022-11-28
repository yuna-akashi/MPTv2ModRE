package mptv2re.content;

import arc.struct.ObjectMap;
import arc.struct.Seq;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;

import static mindustry.content.TechTree.*;

public class MPTv2RETechTree {
    //public static ObjectMap<UnitType, ItemStack[]> unitBuildCost = new ObjectMap<>();

    public static void load() {
        nodeRoot("mptv2-re", MPTv2Blocks.titaniumAlloySmelter, () -> {
            node(MPTv2Blocks.metrenSmelter, () -> {
                //wall
                node(MPTv2Blocks.metrenWall, () -> {
                    node(MPTv2Blocks.metrenWallLarge);
                });

                //factory

                //power
                node(MPTv2Blocks.metrenNode, () -> {
                    node(MPTv2Blocks.metrenLargeNode, () -> {
                        node(MPTv2Blocks.metrenTowerNode);
                    });

                    node(MPTv2Blocks.metrenBattery, () -> {
                        node(MPTv2Blocks.largeMetrenBattery, () -> {
                            node(MPTv2Blocks.powerCondenser);
                        });
                    });

                    //node(MPTv2Blocks.metrenReactor, () -> {
                        //node(MPTv2Blocks.deuteriumReactor, () -> {
                            node(MPTv2Blocks.antimatterReactor);
                        //});
                    //});
                });

                //turrets
                node(MPTv2Blocks.defendTurret, () -> {
                    node(MPTv2Blocks.emperorOfGuardian, () -> {
                        node(MPTv2Blocks.antimatterRailgun);
                    });
                });

                //effect
                node(MPTv2Blocks.boostDriveProjector,() -> {
                    node(MPTv2Blocks.metrender, () -> {
                        node(MPTv2Blocks.metrenShieldDome);
                    });

                    node(MPTv2Blocks.metrenContainer);
                });

                node(MPTv2Blocks.coreMetren,() -> {
                    node(MPTv2Blocks.coreAdvance, () -> {
                        node(MPTv2Blocks.coreExperimental, () -> {
                            node(MPTv2Blocks.coreEmperorOfAntimatter);
                        });
                    });
                });
            });

            node(MPTv2Blocks.metrenFrameCrafter, () -> {
                node(MPTv2Blocks.largeMetrenFrameCrafter, () -> {
                    node(MPTv2Blocks.specialMetrenFrameCrafter);
                });
                node(MPTv2Blocks.turretFrameCrafter, () -> {
                    node(MPTv2Blocks.largeTurretFrameCrafter, () -> {
                        node(MPTv2Blocks.specialTurretFrameCrafter);
                    });
                });
                node(MPTv2Blocks.armorPlateCrafter, () -> {
                    node(MPTv2Blocks.heavyArmorPlateCrafter, () -> {
                        node(MPTv2Blocks.specialArmorPlateCrafter);
                    });
                });
            });
        });
    }
}
