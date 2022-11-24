package mptv2re.content;

import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import static mindustry.type.ItemStack.with;

import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.production.GenericCrafter;

public class MPTv2Blocks {
    public static Block
        //wall
        metrenWall, metrenWallLarge,
        //turrets
        guardian, emperorOfGuardian, antimatterRailgun,
        //base factory
        metrenSmelter, titaniumAlloySmelter,
        metrenFrameCrafter, heavyMetrenFrameCrafter, superMetrenFrameCrafter,
        turretFrameCrafter, heavyTurretFrameCrafter, superTurretFrameCrafter,
        armorPlateCrafter, heavyArmorPlateCrafter, superArmorPlateCrafter
        //metren's factory
    ;
    private static void loadBaseFactory() {
        metrenSmelter = new GenericCrafter("metrenSmelter"){{
            size = 3;
            health = 320;
            hasItems = hasPower = true;
            itemCapacity = 120;
            craftTime = 72;
            consumePower(6);
            consumeItems(with(Items.graphite, 10,Items.lead, 15, Items.titanium, 25));
            outputItems = with(MPTv2Items.metren, 5);
            requirements(Category.crafting, with(Items.copper, 156, Items.lead, 200, Items.titanium, 250));
        }};

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

        metrenFrameCrafter = new GenericCrafter("metrenFrameCrafter"){{
            size = 2;
            health = 320;
            hasItems = hasPower =true;
            consumePower(4);
            consumeItems(with(MPTv2Items.metren, 4, MPTv2Items.metrenDiamond, 2));
            outputItems = with(MPTv2Items.metrenFrame, 2);
            requirements(Category.crafting, with(MPTv2Items.titaniumAlloy, 40, Items.copper, 120, Items.lead, 80));
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
    }
}
