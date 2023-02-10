package mptv2re.content;

import arc.struct.Seq;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;

import static mindustry.content.TechTree.*;

public class MPTv2RETechTree {
    //public static ObjectMap<UnitType, ItemStack[]> unitBuildCost = new ObjectMap<>();


    public static void load() {
        //for all
        nodeRoot("mptv2-re", MPTv2Blocks.titaniumAlloySmelter, () -> {
            node(MPTv2Blocks.efficiencyTechnologyResearchCenter, () -> {});
            node(MPTv2Blocks.unitResearchCenter, () -> {});
            node(MPTv2Blocks.turretResearchCenter, () -> {});

            //distribution
            node(MPTv2Blocks.metrenConveyor, () -> {
                node(MPTv2Blocks.metrenBridgeConveyor, () -> {});
                node(MPTv2Blocks.metrenRouter, () -> {
                    node(MPTv2Blocks.metrenJunction, () -> {});
                });
            });

            //core
            node(MPTv2Blocks.coreMetren, () -> {
                node(MPTv2Blocks.coreAdvance, () -> {
                    node(MPTv2Blocks.coreExperimental, () -> {
                        node(MPTv2Blocks.coreEmperorOfAntimatter, () -> {
                            node(MPTv2Blocks.coreSDU);
                        });
                    });
                });
            });

            //effect
            node(MPTv2Blocks.boostDriveProjector, () -> {
                node(MPTv2Blocks.metrender, () -> {
                    node(MPTv2Blocks.metrenShieldDome, () -> {
                    });
                });

                node(MPTv2Blocks.metrenContainer, ()-> {
                });
            });

            node(MPTv2Blocks.titaniumAlloyDrill, () -> {
                node(MPTv2Blocks.metrenDrill, () -> {
                });
            });

            //factory
            node(MPTv2Blocks.metrenSmelter, () -> {
                node(MPTv2Blocks.metrenGlassSmelter, () -> {
                    node(MPTv2Blocks.metrenDiamondCompressor, () -> {
                    });
                    node(MPTv2Blocks.metrenSiliconSmelter, () -> {
                        node(MPTv2Blocks.hydrogenIsotopeChamber, () -> {
                        });
                    });
                });

                node(MPTv2Blocks.metrenFrameCrafter, () -> {
                    node(MPTv2Blocks.turretFrameCrafter, () -> {
                        node(MPTv2Blocks.antimatterTurretFrameCrafter);
                    });
                    node(MPTv2Blocks.armorPlateCrafter, () -> {
                        node(MPTv2Blocks.antimatterArmorPlateCrafter);
                    });
                    node(MPTv2Blocks.antimatterFrameCrafter);
                });

                node(MPTv2Blocks.metrenAmmoCrafter, () -> {
                    node(MPTv2Blocks.metrenExplosiveAmmoCrafter, () -> {});
//                        node(MPTv2Blocks.);
                });
            });

            //power
            node(MPTv2Blocks.metrenNode, () -> {
                node(MPTv2Blocks.metrenLargeNode, () -> {
                    node(MPTv2Blocks.metrenTowerNode, () -> {
                    });
                });

                node(MPTv2Blocks.metrenBattery, () -> {
                    node(MPTv2Blocks.largeMetrenBattery, () -> {
                        node(MPTv2Blocks.powerCondenser, () -> {
                        });
                    });
                });

                node(MPTv2Blocks.metrenReactor, () -> {
                    node(MPTv2Blocks.nuclearFusionReactor, () -> {
                        node(MPTv2Blocks.antimatterReactor);
                    });
                });
            });
            //node(MPTv2Blocks.);

            //wall
            node(MPTv2Blocks.titaniumAlloyWall, () -> {
                node(MPTv2Blocks.titaniumAlloyWallLarge, () -> {});
                node(MPTv2Blocks.metrenWall, () -> {
                    node(MPTv2Blocks.metrenWallLarge, () -> {});
                });
            });

            //turrets
            node(MPTv2Blocks.assaultCannon, () -> {
                node(MPTv2Blocks.missileSilo, ItemStack.with(MPTv2Items.turretResearchPack,80), () -> {
                });

                node(MPTv2Blocks.railgun, ItemStack.with(MPTv2Items.turretResearchPack, 150), () -> {
                    node(MPTv2Blocks.multiRailgun, ItemStack.with(MPTv2Items.turretResearchPack, 300, MPTv2Items.efficiencyTechnologyPack, 250), () -> {
                        node(MPTv2Blocks.antimatterRailgun, ItemStack.with(MPTv2Items.turretResearchPack, 2000), () -> {});
                    });
                });

                node(MPTv2Blocks.guardian, ItemStack.with(MPTv2Items.turretResearchPack, 500), () -> {
                });

                node(MPTv2Blocks.defendTurret, ItemStack.with(MPTv2Items.turretResearchPack, 85), () -> {
                    node(MPTv2Blocks.antimatterBlaster, ItemStack.with(MPTv2Items.turretResearchPack, 2250), () -> {
                        node(MPTv2Blocks.antimatterShockwaveCannon, ItemStack.with(MPTv2Items.turretResearchPack, 2500), () -> {});
                    });
                });
            });

            //units
            node(MPTv2Blocks.roombaFactory, () -> {
                node(MPTv2UnitTypes.roomba, ItemStack.with(MPTv2Items.unitResearchPack, 40), () -> {
                    node(MPTv2UnitTypes.attackRoomba, ItemStack.with(MPTv2Items.unitResearchPack, 80), Seq.with(new Objectives.Research(MPTv2Blocks.metrenAdditiveReconstructor)), () -> {
                        node(MPTv2UnitTypes.jibakuRoomba, ItemStack.with(MPTv2Items.unitResearchPack, 200), Seq.with(new Objectives.Research(MPTv2Blocks.metrenMultiplicativeReconstructor)), () -> {});
                        node(MPTv2UnitTypes.jibakuNukeRoomba, ItemStack.with(MPTv2Items.unitResearchPack, 120), Seq.with(new Objectives.Research(MPTv2Blocks.metrenAdditiveReconstructor)), () -> {
                        });
                    });
                    node(MPTv2UnitTypes.miningRoomba, ItemStack.with(MPTv2Items.unitResearchPack, 30), () -> {
                        node(MPTv2UnitTypes.repairRoomba, ItemStack.with(MPTv2Items.unitResearchPack, 60), Seq.with(new Objectives.Research(MPTv2Blocks.metrenAdditiveReconstructor)), () -> {
                            node(MPTv2UnitTypes.rebuildRoomba, ItemStack.with(MPTv2Items.unitResearchPack, 90), Seq.with(new Objectives.Research(MPTv2Blocks.metrenMultiplicativeReconstructor)), () -> {
                                node(MPTv2UnitTypes.builderRoomba, ItemStack.with(MPTv2Items.unitResearchPack, 120), Seq.with(new Objectives.Research(MPTv2Blocks.metrenAdditiveReconstructor)), () -> {
                                    node(MPTv2UnitTypes.shieldRoomba, ItemStack.with(MPTv2Items.unitResearchPack, 180), Seq.with(new Objectives.Research(MPTv2Blocks.metrenMultiplicativeReconstructor)), () -> {});
                                });
                            });
                        });
                    });
                });
                node(MPTv2Blocks.metrenUnitFactory, ItemStack.with(MPTv2Items.unitResearchPack, 50), () -> {
                    node(MPTv2UnitTypes.ayu, ItemStack.with(MPTv2Items.unitResearchPack, 60), () -> {
                        node(MPTv2UnitTypes.mino, ItemStack.with(MPTv2Items.unitResearchPack, 90), Seq.with(new Objectives.Research(MPTv2Blocks.metrenAdditiveReconstructor)), () -> {
                            node(MPTv2UnitTypes.ami, ItemStack.with(MPTv2Items.unitResearchPack, 120), Seq.with(new Objectives.Research(MPTv2Blocks.metrenMultiplicativeReconstructor)), () -> {
                                node(MPTv2UnitTypes.meru, ItemStack.with(MPTv2Items.unitResearchPack, 150), Seq.with(new Objectives.Research(MPTv2Blocks.ayuAssembler)), () -> {
                                    node(MPTv2UnitTypes.nimu, ItemStack.with(MPTv2Items.unitResearchPack, 180), Seq.with(new Objectives.Research(MPTv2Blocks.metrenAssemblerModule)), () -> {});
                                });
                            });
                        });
                    });
                    node(MPTv2UnitTypes.pemu, ItemStack.with(MPTv2Items.unitResearchPack, 90), () -> {
                        node(MPTv2UnitTypes.pemuCarrier, ItemStack.with(MPTv2Items.unitResearchPack, 900), Seq.with(new Objectives.Research(MPTv2Blocks.pemuAssembler)), () -> {});
                    });
                });
                node(MPTv2Blocks.metrenAdditiveReconstructor, ItemStack.with(MPTv2Items.unitResearchPack, 100), () -> {
                    node(MPTv2Blocks.metrenMultiplicativeReconstructor,ItemStack.with(MPTv2Items.unitResearchPack, 110), () -> {
                        node(MPTv2Blocks.ayuAssembler, ItemStack.with(MPTv2Items.unitResearchPack, 125), () -> {
                            node(MPTv2Blocks.metrenAssemblerModule,ItemStack.with(MPTv2Items.unitResearchPack, 150), () -> {});
                            node(MPTv2Blocks.pemuAssembler,ItemStack.with(MPTv2Items.unitResearchPack, 200), () -> {});
                        });
                    });
                });
                node(MPTv2Blocks.antimatteredUnitFactory, () -> {
                    node(MPTv2UnitTypes.beast, () -> {
                        node(MPTv2UnitTypes.matter, Seq.with(new Objectives.Research(MPTv2Blocks.metrenAdditiveReconstructor)), () -> {
                            node(MPTv2UnitTypes.ecru, Seq.with(new Objectives.Research(MPTv2Blocks.metrenMultiplicativeReconstructor)), () -> {
                                node(MPTv2UnitTypes.eter, Seq.with(new Objectives.Research(MPTv2Blocks.antimatteredAssembler)), () -> {
                                    node(MPTv2UnitTypes.destAllier, Seq.with(new Objectives.Research(MPTv2Blocks.antimatteredAssemblerModule)), () -> {});
                                });
                            });
                        });
                    });

                    node(MPTv2Blocks.antimatteredAssembler, () -> {
                        node(MPTv2Blocks.antimatteredAssemblerModule);
                    });
                });
            });


            nodeProduce(MPTv2Items.titaniumAlloy, () -> {
                nodeProduce(MPTv2Items.metren, () -> {
                    nodeProduce(MPTv2Items.metrenGlass, () -> {
                        nodeProduce(MPTv2Items.metrenDiamond, () -> {
                            nodeProduce(MPTv2Items.metrenSilicon, () -> {});
                        });
                    });
                    nodeProduce(MPTv2Items.compressedThorium, () -> {});

                    nodeProduce(MPTv2Items.efficiencyTechnologyPack, () -> {
                        nodeProduce(MPTv2Items.turretResearchPack, () -> {
                            nodeProduce(MPTv2Items.unitResearchPack, () -> {
                            });
                        });
                    });
                    nodeProduce(MPTv2Liquids.meter, () -> {});

                    nodeProduce(MPTv2Items.metrenFrame, () -> {
                        nodeProduce(MPTv2Items.turretFrame, () -> {
                            nodeProduce(MPTv2Items.armorPlate, () -> {
                                nodeProduce(MPTv2Items.antimatterArmorPlate, () -> {});
                            });
                            nodeProduce(MPTv2Items.antimatterTurretFrame, () -> {});
                        });
                        nodeProduce(MPTv2Items.antimatterFrame, () -> {});
                    });

                    nodeProduce(MPTv2Items.cell, () -> {
                        nodeProduce(MPTv2Items.coolingCell, () -> {
                            nodeProduce(MPTv2Items.oxygenCell, () -> {
                                nodeProduce(MPTv2Items.deuteriumCell,() -> {
                                    nodeProduce(MPTv2Items.tritiumCell, () -> {
                                        nodeProduce(MPTv2Items.antimatterCell, () -> {});
                                    });
                                });
                            });
                        });
                    });

                    nodeProduce(MPTv2Items.metrenAmmo, () -> {
                        nodeProduce(MPTv2Items.metrenExplosiveAmmo, () -> {
                        });
                        nodeProduce(MPTv2Items.metrenMissile, () ->{
                            nodeProduce(MPTv2Items.emperorsCristal, () -> {});
                        });
                    });
                });
            });
        });
        //for erekir
        nodeRoot("mptv2-re-erekir", MPTv2Blocks.carbideAlloyArcSmelter, () -> {
            node(MPTv2Blocks.metrenArcFurnace, () -> {
                node(MPTv2Blocks.metrenSRBeamNode, () -> {
                    node(MPTv2Blocks.metrenHRBeamNode, () -> {
                        node(MPTv2Blocks.metrenBeamTower);
                    });
                    node(MPTv2Blocks.metrenBeamCondenser);
                });
            });
        });
    }
}
