package mptv2re.content;

import arc.struct.Seq;
import mindustry.game.Objectives.*;
import mindustry.type.ItemStack;

import static mindustry.content.SectorPresets.planetaryTerminal;
import static mindustry.content.TechTree.*;

public class MPTv2RETechTree {
    //public static ObjectMap<UnitType, ItemStack[]> unitBuildCost = new ObjectMap<>();

    public static void load() {
        nodeRoot("mptv2-re", MPTv2Blocks.basicResearchCenter, () -> {
            //research center
            node(MPTv2Blocks.normalResearchCenter, ItemStack.with(MPTv2Items.basicResearchPack, 50), ()-> {
                node(MPTv2Blocks.advancedResearchCenter, ItemStack.with(MPTv2Items.normalResearchPack, 100), () -> {
                    node(MPTv2Blocks.superResearchCenter, ItemStack.with(MPTv2Items.advancedResearchPack, 100), () -> {
                        node(MPTv2Blocks.specialResearchCenter, ItemStack.with(MPTv2Items.superResearchPack, 125), () ->{});
                    });
                    node(MPTv2Blocks.efficiencyTechnologyResearchCenter, ItemStack.with(MPTv2Items.advancedResearchPack, 100), () -> {});
                });
                node(MPTv2Blocks.unitResearchCenter, ItemStack.with(MPTv2Items.normalResearchPack, 150), () -> {});
                node(MPTv2Blocks.turretResearchCenter, ItemStack.with(MPTv2Items.normalResearchPack, 150), () -> {});
            });

            //distribution
            node(MPTv2Blocks.metrenConveyor, ItemStack.with(MPTv2Items.normalResearchPack, 10), () -> {
                node(MPTv2Blocks.metrenBridgeConveyor,ItemStack.with(MPTv2Items.normalResearchPack, 10), () -> {});
                node(MPTv2Blocks.metrenRouter, ItemStack.with(MPTv2Items.normalResearchPack, 10), () -> {
                    node(MPTv2Blocks.metrenJunction, ItemStack.with(MPTv2Items.normalResearchPack, 10), () -> {});
                });
            });

            //core
            node(MPTv2Blocks.coreMetren, ItemStack.with(MPTv2Items.normalResearchPack, 1000), () -> {
                node(MPTv2Blocks.coreAdvance, ItemStack.with(MPTv2Items.advancedResearchPack, 1000), () -> {
                    node(MPTv2Blocks.coreExperimental, ItemStack.with(MPTv2Items.superResearchPack, 1000), () -> {
                        node(MPTv2Blocks.coreEmperorOfAntimatter, ItemStack.with(MPTv2Items.superResearchPack, 1740, MPTv2Items.specialResearchPack, 2260), () -> {
                            node(MPTv2Blocks.coreSDU, ItemStack.with(MPTv2Items.specialResearchPack, 500000), Seq.with(new OnSector(planetaryTerminal)), () -> {});
                        });
                    });
                });
            });

            node(MPTv2Blocks.titaniumAlloySmelter, ItemStack.with(MPTv2Items.basicResearchPack, 2), () -> {
                //effect
                node(MPTv2Blocks.boostDriveProjector, ItemStack.with(MPTv2Items.advancedResearchPack, 12), () -> {
                    node(MPTv2Blocks.metrender, ItemStack.with(MPTv2Items.advancedResearchPack, 18), () -> {
                        node(MPTv2Blocks.metrenShieldDome, ItemStack.with(MPTv2Items.advancedResearchPack, 30), () -> {
                        });
                    });

                    node(MPTv2Blocks.metrenContainer, ItemStack.with(MPTv2Items.advancedResearchPack, 8), ()-> {
                    });
                });

                node(MPTv2Blocks.titaniumAlloyDrill, ItemStack.with(MPTv2Items.basicResearchPack, 8), () -> {
                    node(MPTv2Blocks.metrenDrill, ItemStack.with(MPTv2Items.normalResearchPack, 5), () -> {
                    });
                });

                //factory
                node(MPTv2Blocks.metrenSmelter, ItemStack.with(MPTv2Items.basicResearchPack, 3), () -> {
                    node(MPTv2Blocks.metrenGlassSmelter, ItemStack.with(MPTv2Items.normalResearchPack, 7), () -> {
                        node(MPTv2Blocks.metrenDiamondCompressor, ItemStack.with(MPTv2Items.normalResearchPack, 7), () -> {
                        });
                        node(MPTv2Blocks.metrenSiliconSmelter, ItemStack.with(MPTv2Items.normalResearchPack, 7), () -> {
                            node(MPTv2Blocks.deuteriumChamber, ItemStack.with(MPTv2Items.superResearchPack, 450), () -> {
                                node(MPTv2Blocks.antimatterGenerator, ItemStack.with(MPTv2Items.superResearchPack, 750), () -> {
                                });
                            });
                        });
                    });

                    node(MPTv2Blocks.metrenFrameCrafter, ItemStack.with(MPTv2Items.normalResearchPack, 4), () -> {
                        node(MPTv2Blocks.largeMetrenFrameCrafter, ItemStack.with(MPTv2Items.advancedResearchPack, 5), () -> {
                            node(MPTv2Blocks.specialMetrenFrameCrafter, ItemStack.with(MPTv2Items.specialResearchPack, 6), () -> {
                            });
                        });
                        node(MPTv2Blocks.turretFrameCrafter, ItemStack.with(MPTv2Items.normalResearchPack, 4), () -> {
                            node(MPTv2Blocks.largeTurretFrameCrafter, ItemStack.with(MPTv2Items.advancedResearchPack, 6), () -> {
                                node(MPTv2Blocks.specialTurretFrameCrafter, ItemStack.with(MPTv2Items.specialResearchPack, 8), () -> {
                                });
                            });
                        });
                        node(MPTv2Blocks.armorPlateCrafter, ItemStack.with(MPTv2Items.normalResearchPack, 6), () -> {
                            node(MPTv2Blocks.heavyArmorPlateCrafter, ItemStack.with(MPTv2Items.advancedResearchPack, 8), () -> {
                                node(MPTv2Blocks.specialArmorPlateCrafter, ItemStack.with(MPTv2Items.specialResearchPack, 10), () -> {
                                });
                            });
                        });
                    });

                    node(MPTv2Blocks.metrenAmmoCrafter, ItemStack.with(MPTv2Items.normalResearchPack, 20), () -> {
                        node(MPTv2Blocks.metrenExplosiveAmmoCrafter, ItemStack.with(MPTv2Items.normalResearchPack, 28), () -> {});
//                        node(MPTv2Blocks.);
                    });
                });

                //power
                node(MPTv2Blocks.metrenNode, ItemStack.with(MPTv2Items.normalResearchPack, 10), () -> {
                    node(MPTv2Blocks.metrenLargeNode, ItemStack.with(MPTv2Items.advancedResearchPack, 20), () -> {
                        node(MPTv2Blocks.metrenTowerNode, ItemStack.with(MPTv2Items.advancedResearchPack, 40), () -> {
                        });
                    });

                    node(MPTv2Blocks.metrenBattery, ItemStack.with(MPTv2Items.normalResearchPack, 10), () -> {
                        node(MPTv2Blocks.largeMetrenBattery, ItemStack.with(MPTv2Items.advancedResearchPack, 20), () -> {
                            node(MPTv2Blocks.powerCondenser, ItemStack.with(MPTv2Items.superResearchPack, 240), () -> {
                            });
                        });
                    });

                        node(MPTv2Blocks.metrenReactor, ItemStack.with(MPTv2Items.advancedResearchPack, 140), () -> {
                            node(MPTv2Blocks.nuclearFusionReactor, ItemStack.with(MPTv2Items.superResearchPack, 240), () -> {
                                node(MPTv2Blocks.antimatterReactor, ItemStack.with(MPTv2Items.specialResearchPack, 470), () -> {});
                            });
                        });
                });
            });

            //wall
            node(MPTv2Blocks.titaniumAlloyWall, ItemStack.with(MPTv2Items.basicResearchPack, 10), () -> {
                node(MPTv2Blocks.titaniumAlloyWallLarge, ItemStack.with(MPTv2Items.basicResearchPack, 20), () -> {});
                node(MPTv2Blocks.metrenWall, ItemStack.with(MPTv2Items.normalResearchPack, 10), () -> {
                    node(MPTv2Blocks.metrenWallLarge, ItemStack.with(MPTv2Items.normalResearchPack,25), () -> {});
                });
            });

            //turrets
            node(MPTv2Blocks.assaultCannon, ItemStack.with(MPTv2Items.normalResearchPack, 40), () -> {
                node(MPTv2Blocks.missileSilo, ItemStack.with(MPTv2Items.normalResearchPack, 100, MPTv2Items.turretResearchPack,80), () -> {
                });

                node(MPTv2Blocks.railgun, ItemStack.with(MPTv2Items.advancedResearchPack, 100, MPTv2Items.turretResearchPack, 150), () -> {
                    node(MPTv2Blocks.multiRailgun, ItemStack.with(MPTv2Items.superResearchPack, 150, MPTv2Items.turretResearchPack, 300, MPTv2Items.efficiencyTechnologyPack, 250), () -> {
                        node(MPTv2Blocks.antimatterRailgun, ItemStack.with(MPTv2Items.specialResearchPack, 500, MPTv2Items.turretResearchPack, 1000), () -> {
                        });
                    });
                });

                node(MPTv2Blocks.guardian, ItemStack.with(MPTv2Items.superResearchPack, 300, MPTv2Items.turretResearchPack, 500), () -> {
                });

                node(MPTv2Blocks.defendTurret, ItemStack.with(MPTv2Items.normalResearchPack, 75, MPTv2Items.turretResearchPack, 85), () -> {
                    node(MPTv2Blocks.antimatterBlaster, ItemStack.with(MPTv2Items.specialResearchPack, 750, MPTv2Items.turretResearchPack, 1250), () -> {
                        node(MPTv2Blocks.antimatterShockwaveCannon, ItemStack.with(MPTv2Items.specialResearchPack, 1000, MPTv2Items.turretResearchPack, 1500), () -> {
                        });
                    });
                });
            });

            //units
            node(MPTv2Blocks.roombaFactory, ItemStack.with(MPTv2Items.advancedResearchPack, 55), () -> {
                node(MPTv2UnitTypes.roomba, ItemStack.with(MPTv2Items.advancedResearchPack, 25, MPTv2Items.unitResearchPack, 40), () -> {
                    node(MPTv2UnitTypes.jibakuRoomba, ItemStack.with(MPTv2Items.advancedResearchPack, 20, MPTv2Items.unitResearchPack, 80), () -> {
                        node(MPTv2UnitTypes.jibakuNukeRoomba, ItemStack.with(MPTv2Items.advancedResearchPack, 30, MPTv2Items.unitResearchPack, 200), () -> {});
                        node(MPTv2UnitTypes.attackRoomba, ItemStack.with(MPTv2Items.advancedResearchPack, 30, MPTv2Items.unitResearchPack, 120), () -> {
//                            node(MPTv2UnitTypes.);
                        });
                    });
                    node(MPTv2UnitTypes.miningRoomba, ItemStack.with(MPTv2Items.advancedResearchPack, 25, MPTv2Items.unitResearchPack, 30), () -> {
                        node(MPTv2UnitTypes.repairRoomba, ItemStack.with(MPTv2Items.advancedResearchPack, 30, MPTv2Items.unitResearchPack, 60), () -> {
                            node(MPTv2UnitTypes.builderRoomba, ItemStack.with(MPTv2Items.advancedResearchPack, 40, MPTv2Items.unitResearchPack, 90), () -> {
                                node(MPTv2UnitTypes.rebuildRoomba, ItemStack.with(MPTv2Items.advancedResearchPack, 45, MPTv2Items.unitResearchPack, 120), () -> {
                                    node(MPTv2UnitTypes.shieldRoomba, ItemStack.with(MPTv2Items.advancedResearchPack, 50, MPTv2Items.unitResearchPack, 180), () -> {});
                                });
                            });
                        });
                    });
                });
//                node(MPTv2Blocks.metrenedAirFactory, ItemStack.with(MPTv2Items.advancedResearchPack, 50), () -> {});
                node(MPTv2Blocks.antimatteredUnitFactory, ItemStack.with(MPTv2Items.specialResearchPack, 200), () -> {
                    node(MPTv2UnitTypes.beast, ItemStack.with(MPTv2Items.specialResearchPack, 250, MPTv2Items.unitResearchPack, 1000), () -> {
                        node(MPTv2UnitTypes.matter, ItemStack.with(MPTv2Items.specialResearchPack, 300, MPTv2Items.unitResearchPack, 1250), Seq.with(new Research(MPTv2Blocks.antimatteredAdditiveReconstructor)), () -> {
                            node(MPTv2UnitTypes.ecru, ItemStack.with(MPTv2Items.specialResearchPack, 350, MPTv2Items.unitResearchPack,  1500), Seq.with(new Research(MPTv2Blocks.antimatteredMultiplicativeReconstructor)), () -> {
                                node(MPTv2UnitTypes.eter, ItemStack.with(MPTv2Items.specialResearchPack, 400, MPTv2Items.unitResearchPack, 1750), Seq.with(new Research(MPTv2Blocks.antimatteredAssembler)), () -> {
                                    node(MPTv2UnitTypes.destAllier, ItemStack.with(MPTv2Items.specialResearchPack, 500, MPTv2Items.unitResearchPack, 2500), Seq.with(new Research(MPTv2Blocks.antimatteredAssembler), new Research(MPTv2Blocks.antimatteredAssemblerModule)), () -> {
                                        node(MPTv2UnitTypes.aoe, ItemStack.with(MPTv2Items.specialResearchPack, 5000, MPTv2Items.unitResearchPack, 10000), Seq.with(new Research(MPTv2Blocks.coreEmperorOfAntimatter), new OnSector(planetaryTerminal)), () -> {});
                                    });
                                });
                            });
                        });
                    });
                    node(MPTv2Blocks.antimatteredAdditiveReconstructor, ItemStack.with(MPTv2Items.specialResearchPack, 300), () -> {
                        node(MPTv2Blocks.antimatteredMultiplicativeReconstructor, ItemStack.with(MPTv2Items.specialResearchPack, 400), () -> {
                            node(MPTv2Blocks.antimatteredAssembler, ItemStack.with(MPTv2Items.specialResearchPack, 475), () -> {
                                node(MPTv2Blocks.antimatteredAssemblerModule, ItemStack.with(MPTv2Items.specialResearchPack, 500), () -> {
                                });
                            });
                        });
                    });
                });
//                node(MPTv2Blocks.metrenedAdditiveReconstructor, ItemStack.with(MPTv2Items.advancedResearchPack, 110), () -> {
//                    node(MPTv2Blocks.metrenedExponentialReconstructor, ItemStack.with(MPTv2Items.advancedResearchPack, 165), () -> {
//                        node(MPTv2Blocks.metrenedTetrativeReconstructor, ItemStack.with(MPTv2Items.superResearchPack, 50), () -> {});
//                    });
//                });
            });

            nodeProduce(MPTv2Items.basicResearchPack, () -> {
                nodeProduce(MPTv2Items.normalResearchPack, () -> {
                    nodeProduce(MPTv2Items.advancedResearchPack, () -> {
                        nodeProduce(MPTv2Items.superResearchPack, () -> {
                            nodeProduce(MPTv2Items.specialResearchPack, () -> {
                                nodeProduce(MPTv2Items.specialMetrenFrame, () -> {
                                    nodeProduce(MPTv2Items.specialTurretFrame, () -> {
                                        nodeProduce(MPTv2Items.specialArmorPlate, () -> {});
                                    });
                                });

                                nodeProduce(MPTv2Items.antimatterCell, () -> {});
                            });
                            nodeProduce(MPTv2Items.efficiencyTechnologyPack, () -> {});

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
                    nodeProduce(MPTv2Items.turretResearchPack, () -> {});
                    nodeProduce(MPTv2Items.unitResearchPack, () -> {});

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
