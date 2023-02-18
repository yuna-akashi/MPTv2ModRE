package mptv2re.content;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.type.Item;

public class MPTv2Items extends Items {
    public static Item
        //materials
        titaniumAlloy, carbideAlloy,
        metren, metrenGlass, metrenDiamond, metrenSilicon,
        compressedThorium,

        //building materials
        metrenFrame, turretFrame, armorPlate,
        antimatterFrame, antimatterTurretFrame, antimatterArmorPlate,

        //resources
        cell, coolingCell, oxygenCell, deuteriumCell, tritiumCell, antimatterCell,
        unitCore, multiCore,

        //ammo
        metrenAmmo, metrenExplosiveAmmo, metrenMissile, emperorsCristal
    ;

    public static void load() {

        //material
        titaniumAlloy = new Item("titaniumAlloy", Color.valueOf("9C81CE")){{
            cost = 1.2f;
        }};//done
        carbideAlloy = new Item("carbideAlloy", Color.valueOf("89769a")){{
            cost = 1.2f;
        }};//done
        metren = new Item("metren", MPTv2Color.metren){{
            cost = 2;
        }};//done
        metrenGlass = new Item("metrenGlass", Color.valueOf("BBBBBB")){{
            cost = 1.2f;
        }};//done
        metrenDiamond = new Item("metrenDiamond", Color.valueOf("5EFFFA")){{
            cost = 1.2f;
        }};//done
        metrenSilicon = new Item("metrenSilicon",Color.valueOf("5A7E80")){{
            cost = 1.2f;
        }};//done
        compressedThorium = new Item("compressedThorium", Color.valueOf("DF6262")){{
            hardness = 4;
            cost = 1.1f;
            explosiveness = 0.85f;
            radioactivity = 4f;
        }};//done

        //building material
        metrenFrame = new Item("metrenFrame", MPTv2Color.metrenLight){{
            cost = 0.75f;
        }};//done
        turretFrame = new Item("turretFrame", MPTv2Color.metrenLight){{
            cost = 0.75f;
        }};//done
        armorPlate = new Item("armorPlate", MPTv2Color.metrenLight){{
            cost = 0.75f;
        }};//done
        antimatterFrame = new Item("antimatterFrame", Color.valueOf("470B70")){{
            cost = 0.75f;
        }};//done
        antimatterTurretFrame = new Item("antimatterTurretFrame", Color.valueOf("470B70")){{
            cost = 0.75f;
        }};//done
        antimatterArmorPlate = new Item("antimatterArmorPlate", Color.valueOf("470B70")){{
            cost = 0.75f;
        }};//done

        //resource
        cell = new Item("cell", MPTv2Color.metren);//done
        coolingCell = new Item("coolingCell", Color.valueOf("076C83"));//done
        oxygenCell = new Item("oxygenCell", Color.valueOf("83F6AB")){{
            flammability = 10f;
        }};//done
        deuteriumCell = new Item("deuteriumCell", Color.valueOf("0F0F0F")){{
            radioactivity = 0.2f;
            flammability = 1.52f;
        }};//done
        tritiumCell = new Item("tritiumCell", Color.valueOf("927fff")){{
            radioactivity = 0.2f;
            flammability = 1.75f;
        }};//done
        antimatterCell = new Item("antimatterCell", Color.valueOf("470B70"));//done

        unitCore = new Item("unitCore", Color.valueOf("FF80F9"));//done
        multiCore = new Item("multiCore", Color.valueOf("FFA548"));//done

        //ammo
        metrenAmmo = new Item("metrenAmmo", MPTv2Color.metren);//done
        metrenExplosiveAmmo = new Item("metrenExplosiveAmmo", Color.valueOf("DF6262")){{
            explosiveness = 1.5f;
        }};
        metrenMissile = new Item("metrenMissile", MPTv2Color.metren);//done
        emperorsCristal = new Item("emperorsCristal", Color.valueOf("FF80F9"));//done
    }
}
