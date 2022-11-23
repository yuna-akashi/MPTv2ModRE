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
        nodeRoot("mptv2-re", MPTv2Blocks.metrenSmelter, () -> {
            //wall
            node(MPTv2Blocks.metrenWall, () -> {
               node(MPTv2Blocks.metrenWallLarge);
            });
            //factory
            //power
            //turrets
            //effect
        });
    }
}
