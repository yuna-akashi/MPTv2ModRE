package mptv2re.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class MPTv2Items {
    public static Item
        //materials
        titaniumAlloy,
        metren, metrenGlass, metrenDiamond, metrenSilicon,
        compressedThorium,

        //building materials
        metrenFrame,
        turretFrame,
        armorPlate,

        //resources
        cell, coolingCell, oxygenCell, deuteriumCell, tritiumCell,
        unitCore, multiCore,

        //Research Items
        basicResearchPack, normalResearchPack, advancedResearchPack, superResearchPack, specialResearchPack,
        turretResearchPack, unitResearchPack, efficiencyTechnologyPack,

        //ammo
        metrenAmmo, metrenExplosiveAmmo, metrenMissile, emperorsCristal
    ;

    public static void load() {
        //Research Pack
        basicResearchPack = new Item("basicResearchPack", Color.valueOf("F9423A"));//done
        normalResearchPack = new Item("normalResearchPack", Color.valueOf("F6A04D"));//done
        advancedResearchPack = new Item("advancedResearchPack", Color.valueOf("F3D321"));//done
        superResearchPack = new Item("superResearchPack", Color.valueOf("00BC7B"));//done
        specialResearchPack = new Item("specialResearchPack", Color.valueOf("486AFF"));//done
        turretResearchPack = new Item("turretResearchPack", Color.valueOf("2e2f34"));//done
        unitResearchPack = new Item("unitResearchPack", Color.valueOf("ffa3f8"));//done
        efficiencyTechnologyPack = new Item("efficiencyTechnologyPack", Color.valueOf("4D49BE"));//done

        //material
        titaniumAlloy = new Item("titaniumAlloy", Color.valueOf("9C81CE"));//done
        metren = new Item("metren", MPTv2Color.metren);//done
        metrenGlass = new Item("metrenGlass", Color.valueOf("BBBBBB"));//done
        metrenDiamond = new Item("metrenDiamond", Color.valueOf("5EFFFA"));//done
        metrenSilicon = new Item("metrenSilicon",Color.valueOf("5A7E80"));//done
        compressedThorium = new Item("compressedThorium", Color.valueOf("DF6262")){{
            explosiveness = 0.85f;
            radioactivity = 4.5f;
        }};//done

        //building material
        metrenFrame = new Item("metrenFrame", MPTv2Color.metrenLight);//done
        turretFrame = new Item("turretFrame", MPTv2Color.metrenLight);//done
        armorPlate = new Item("armorPlate", MPTv2Color.metrenLight);//done

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
