package mptv2re.content;

import mindustry.type.ItemStack;

import static mindustry.content.TechTree.*;

public class MPTv2RETechTree {
    //public static ObjectMap<UnitType, ItemStack[]> unitBuildCost = new ObjectMap<>();

    public static void load() {
        nodeRoot("mptv2-re", MPTv2Blocks.researchCenter, () -> {
            //research center
            node(MPTv2Blocks.advancedResearchCenter, ItemStack.with(MPTv2Items.smallResearchPack, 50), ()-> {
                node(MPTv2Blocks.experimentalResearchCenter, ItemStack.with(MPTv2Items.mediumResearchPack, 75), () -> {
                    node(MPTv2Blocks.superResearchCenter, ItemStack.with(MPTv2Items.largeResearchPack, 100), () -> {
                        node(MPTv2Blocks.specialResearchCenter, ItemStack.with(MPTv2Items.superResearchPack, 125), () ->{});
                    });
                });
            });
            //distribution
            node(MPTv2Blocks.metrenConveyor, ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {
                node(MPTv2Blocks.metrenBridgeConveyor,ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {});
                node(MPTv2Blocks.metrenRouter, ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {
                    node(MPTv2Blocks.metrenJunction, ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {});
                });
            });

            //core
            node(MPTv2Blocks.coreMetren, ItemStack.with(MPTv2Items.mediumResearchPack, 45), () -> {
                node(MPTv2Blocks.coreAdvance, ItemStack.with(MPTv2Items.mediumResearchPack, 100), () -> {
                    node(MPTv2Blocks.coreExperimental, ItemStack.with(MPTv2Items.largeResearchPack, 100), () -> {
                        node(MPTv2Blocks.coreEmperorOfAntimatter, ItemStack.with(MPTv2Items.superResearchPack, 74, MPTv2Items.specialResearchPack, 126), () -> {
                        });
                    });
                });
            });

            node(MPTv2Blocks.titaniumAlloySmelter, ItemStack.with(MPTv2Items.smallResearchPack, 2), () -> {
                //effect
                node(MPTv2Blocks.boostDriveProjector, ItemStack.with(MPTv2Items.largeResearchPack, 12), () -> {
                    node(MPTv2Blocks.metrender, ItemStack.with(MPTv2Items.largeResearchPack, 18), () -> {
                        node(MPTv2Blocks.metrenShieldDome, ItemStack.with(MPTv2Items.largeResearchPack, 30), () -> {
                        });
                    });

                    node(MPTv2Blocks.metrenContainer, ItemStack.with(MPTv2Items.largeResearchPack, 8), ()-> {
                    });
                });

                node(MPTv2Blocks.titaniumAlloyDrill, ItemStack.with(MPTv2Items.smallResearchPack, 8), () -> {
                    node(MPTv2Blocks.metrenDrill, ItemStack.with(MPTv2Items.mediumResearchPack, 5), () -> {
                    });
                });

                //factory
                node(MPTv2Blocks.metrenSmelter, ItemStack.with(MPTv2Items.smallResearchPack, 3), () -> {
                    node(MPTv2Blocks.metrenGlassSmelter, ItemStack.with(MPTv2Items.mediumResearchPack, 7), () -> {
                        node(MPTv2Blocks.metrenDiamondCompressor, ItemStack.with(MPTv2Items.mediumResearchPack, 7), () -> {
                        });
                        node(MPTv2Blocks.metrenSiliconSmelter, ItemStack.with(MPTv2Items.mediumResearchPack, 7), () -> {
                            node(MPTv2Blocks.antimatterGenerator, ItemStack.with(MPTv2Items.specialResearchPack, 50), () -> {
                            });
                        });
                    });

                    node(MPTv2Blocks.metrenFrameCrafter, ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {
                        node(MPTv2Blocks.largeMetrenFrameCrafter, ItemStack.with(MPTv2Items.largeResearchPack, 5), () -> {
                            node(MPTv2Blocks.specialMetrenFrameCrafter, ItemStack.with(MPTv2Items.specialResearchPack, 6), () -> {
                            });
                        });
                        node(MPTv2Blocks.turretFrameCrafter, ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {
                            node(MPTv2Blocks.largeTurretFrameCrafter, ItemStack.with(MPTv2Items.largeResearchPack, 6), () -> {
                                node(MPTv2Blocks.specialTurretFrameCrafter, ItemStack.with(MPTv2Items.specialResearchPack, 8), () -> {
                                });
                            });
                        });
                        node(MPTv2Blocks.armorPlateCrafter, ItemStack.with(MPTv2Items.mediumResearchPack, 6), () -> {
                            node(MPTv2Blocks.heavyArmorPlateCrafter, ItemStack.with(MPTv2Items.largeResearchPack, 8), () -> {
                                node(MPTv2Blocks.specialArmorPlateCrafter, ItemStack.with(MPTv2Items.specialResearchPack, 10), () -> {
                                });
                            });
                        });
                    });

                    node(MPTv2Blocks.metrenAmmoCrafter, ItemStack.with(MPTv2Items.mediumResearchPack, 20), () -> {
//                        node(MPTv2Blocks.metrenExplosiveAmmoCrafter, ItemStack.with(MPTv2Items.mediumResearchPack, 36), () -> {});
                    });
                });

                //power
                node(MPTv2Blocks.metrenNode, ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {
                    node(MPTv2Blocks.metrenLargeNode, ItemStack.with(MPTv2Items.largeResearchPack, 8), () -> {
                        node(MPTv2Blocks.metrenTowerNode, ItemStack.with(MPTv2Items.largeResearchPack, 16), () -> {
                        });
                    });

                    node(MPTv2Blocks.metrenBattery, ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {
                        node(MPTv2Blocks.largeMetrenBattery, ItemStack.with(MPTv2Items.largeResearchPack, 8), () -> {
                            node(MPTv2Blocks.powerCondenser, ItemStack.with(MPTv2Items.superResearchPack, 35), () -> {
                            });
                        });
                    });

                        node(MPTv2Blocks.metrenReactor, ItemStack.with(MPTv2Items.largeResearchPack, 45), () -> {
                            node(MPTv2Blocks.deuteriumReactor, ItemStack.with(MPTv2Items.superResearchPack, 30), () -> {
                                node(MPTv2Blocks.antimatterReactor, ItemStack.with(MPTv2Items.specialResearchPack, 120), () -> {});
                            });
                        });
                });
            });

            //wall
            node(MPTv2Blocks.titanoumAlloyWall, ItemStack.with(MPTv2Items.smallResearchPack, 4), () -> {
                node(MPTv2Blocks.titaniumAlloyWallLarge, ItemStack.with(MPTv2Items.smallResearchPack, 16), () -> {});
                node(MPTv2Blocks.metrenWall, ItemStack.with(MPTv2Items.mediumResearchPack, 4), () -> {
                    node(MPTv2Blocks.metrenWallLarge, ItemStack.with(MPTv2Items.mediumResearchPack, 16), () -> {});
                });
            });

            //turrets
            node(MPTv2Blocks.assaultCannon, ItemStack.with(MPTv2Items.smallResearchPack, 32), () -> {
                node(MPTv2Blocks.railgun, ItemStack.with(MPTv2Items.largeResearchPack, 55), () -> {
                    node(MPTv2Blocks.multiRailgun, ItemStack.with(MPTv2Items.superResearchPack, 70), () -> {
                        node(MPTv2Blocks.antimatterRailgun, ItemStack.with(MPTv2Items.specialResearchPack, 200), () -> {
                        });
                    });
                });

                node(MPTv2Blocks.guardian, ItemStack.with(MPTv2Items.superResearchPack, 30), () -> {
                    node(MPTv2Blocks.emperorOfGuardian, ItemStack.with(MPTv2Items.superResearchPack, 50), () -> {
                    });
                });

                node(MPTv2Blocks.defendTurret, ItemStack.with(MPTv2Items.mediumResearchPack, 25), () -> {
                    node(MPTv2Blocks.antimatterBlaster, ItemStack.with(MPTv2Items.specialResearchPack, 250), () -> {
                        node(MPTv2Blocks.antimatterShockwaveCannon, ItemStack.with(MPTv2Items.specialResearchPack, 300), () -> {
                        });
                    });
                });
            });

            //units
            node(MPTv2Blocks.roombaFactory, ItemStack.with(MPTv2Items.largeResearchPack, 55), () -> {
                node(MPTv2UnitTypes.roomba, ItemStack.with(MPTv2Items.largeResearchPack, 25), () -> {
                    node(MPTv2UnitTypes.jibakuRoomba, ItemStack.with(MPTv2Items.largeResearchPack, 20), () -> {
//                        node(MPTv2UnitTypes.jibakuNukeRoomba, ItemStack.with(MPTv2Items.largeResearchPack, 30), () -> {});
//                        node(MPTv2UnitTypes.attackRoomba, ItemStack.with(MPTv2Items.largeResearchPack, 30), () -> {});
                    });
                    node(MPTv2UnitTypes.miningRoomba, ItemStack.with(MPTv2Items.largeResearchPack, 25), () -> {
                        node(MPTv2UnitTypes.repairRoomba, ItemStack.with(MPTv2Items.largeResearchPack, 30), () -> {
                            node(MPTv2UnitTypes.builderRoomba, ItemStack.with(MPTv2Items.largeResearchPack, 40), () -> {
                                node(MPTv2UnitTypes.rebuildRoomba, ItemStack.with(MPTv2Items.largeResearchPack, 45), () -> {
                                    node(MPTv2UnitTypes.shieldRoomba, ItemStack.with(MPTv2Items.largeResearchPack, 50), () -> {});
                                });
                            });
                        });
                    });
                });
//                node(MPTv2Blocks.metrenedAirFactory, ItemStack.with(MPTv2Items.largeResearchPack, 50), () -> {});
//                node(MPTv2Blocks.metrenedAdditiveReconstructor, ItemStack.with(MPTv2Items.largeResearchPack, 110), () -> {
//                    node(MPTv2Blocks.metrenedExponentialReconstructor, ItemStack.with(MPTv2Items.largeResearchPack, 165), () -> {
//                        node(MPTv2Blocks.metrenedTetrativeReconstructor, ItemStack.with(MPTv2Items.superResearchPack, 50), () -> {});
//                    });
//                });
            });

            nodeProduce(MPTv2Items.smallResearchPack, () -> {
                nodeProduce(MPTv2Items.mediumResearchPack, () -> {
                    nodeProduce(MPTv2Items.largeResearchPack, () -> {
                        nodeProduce(MPTv2Items.superResearchPack, () -> {
                            nodeProduce(MPTv2Items.specialResearchPack, () -> {
                                nodeProduce(MPTv2Items.efficiencyTechnologyPack, () -> {});

                                nodeProduce(MPTv2Items.specialMetrenFrame, () -> {
                                    nodeProduce(MPTv2Items.specialTurretFrame, () -> {
                                        nodeProduce(MPTv2Items.specialArmorPlate, () -> {});
                                    });
                                });

                                nodeProduce(MPTv2Items.antimatterCell, () -> {});
                            });

                            nodeProduce(MPTv2Items.largeMetrenFrame, () -> {
                                nodeProduce(MPTv2Items.largeTurretFrame, () -> {
                                    nodeProduce(MPTv2Items.heavyArmorPlate, () -> {});
                                });
                            });

                            nodeProduce(MPTv2Items.emperorsCristal, () -> {});
                        });

                        nodeProduce(MPTv2Items.compressedThorium, () -> {});

                        nodeProduce(MPTv2Liquids.meter, () -> {});

                        nodeProduce(MPTv2Items.metrenFrame, () -> {
                            nodeProduce(MPTv2Items.turretFrame, () -> {
                                nodeProduce(MPTv2Items.armorPlate, () -> {});
                            });
                        });

                        nodeProduce(MPTv2Items.cell, () -> {
                            nodeProduce(MPTv2Items.coolingCell, () -> {});
                        });
                    });

                    nodeProduce(MPTv2Items.metren, () -> {
                        nodeProduce(MPTv2Items.metrenGlass, () -> {
                            nodeProduce(MPTv2Items.metrenDiamond, () -> {
                                nodeProduce(MPTv2Items.metrenSilicon, () -> {});
                            });
                        });

                        nodeProduce(MPTv2Items.metrenAmmo, () -> {});
                    });
                });
                nodeProduce(MPTv2Items.titaniumAlloy, () -> {});
            });
        });
    }
}
