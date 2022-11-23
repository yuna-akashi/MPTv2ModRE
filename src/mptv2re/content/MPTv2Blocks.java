package mptv2re.content;

import mindustry.content.Items;
import mindustry.type.Category;
import static mindustry.type.ItemStack.with;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.production.GenericCrafter;

public class MPTv2Blocks {
    public static Block
        //wall
        metrenWall, metrenWallLarge,
        //turrets
        //base factory
        metrenSmelter, metrenFrameCrafter, hugeFrameCrafter
        //metren's factory
    ;

    public static void load(){
        metrenSmelter = new GenericCrafter("metrenSmelter"){{
            size = 3;
            health = 320;
            hasItems = hasPower = true;
            craftTime = 72;
            consumePower(6);
            consumeItems(with(Items.graphite, 10,Items.lead, 15, Items.titanium, 25));
            outputItems = with(MPTv2Items.i1_1metren, 5);
            requirements(Category.crafting, with(Items.copper, 156, Items.lead, 200, Items.titanium, 250));
        }};

        metrenWall = new Wall("metrenWall"){{
            size = 1;
            health = 2000000;
            requirements(Category.defense, with(MPTv2Items.i1_1metren, 8));
        }};

        metrenWallLarge = new Wall("metrenWallLarge"){{
            size = 2;
            health = 8000000;
            armor = 200f;
            requirements(Category.defense, with(MPTv2Items.i1_1metren, 32));
        }};
    }
}
