package mptv2re.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class MPTv2Items {
    public static Item
        //materials
        i1_1metren, i1_1metrenGlass, i1_2metrenDiamond, i1_2metrenSilicon,
        i1_3titaniumAlloy, i1_4compressedThorium,
        //building materials
        i2_1metrenFrame, i2_2hugeMetrenFrame, i2_3emperorsBuildingFrame,
        i2_4mTurretFrame, i2_5hugeMTurretFrame, i2_6emperorsTurretFrame,
        i2_7armorPlate, i2_8hugeArmorPlate, i2_9emperorsArmorPlate,
        //resources
        i3_1cell, i3_2coolingCell, i3_2deuterium, i3_2solidOxygen, i3_3unitCore, i3_4multiCore,
        i3_5antimatterCell,
        //ammo
        i4_1metrenAmmo, i4_1metrenMissile, i4_2emperorsCristal
    ;

    public static void load() {
        //material
        i1_1metren = new Item("metren", Color.valueOf("4f7e81"));
        i1_1metrenGlass = new Item("metrenGlass", Color.valueOf("bbbbbb"));
        i1_2metrenDiamond = new Item("metrenDiamond", Color.valueOf("5efffa"));
        i1_2metrenSilicon = new Item("metrenSilicon",Color.valueOf("5A7E80"));
        i1_3titaniumAlloy = new Item("titaniumAlloy", Color.valueOf("aaaaaa"));
        i1_4compressedThorium = new Item("compressedThorium", Color.valueOf("4f7e81")){{
            explosiveness = 0.9f;
            radioactivity = 4f;
            flammability = 1.5f;
        }};

        //building material
        i2_1metrenFrame = new Item("metrenFrame", Color.valueOf("4f7e81"));
        i2_2hugeMetrenFrame = new Item("hugeMetrenFrame", Color.valueOf("4f7e81"));
        i2_3emperorsBuildingFrame = new Item("emperorsBuildingFrame", Color.valueOf("4f7e81"));
        i2_4mTurretFrame = new Item("mTurretFrame", Color.valueOf("4f7e81"));
        i2_5hugeMTurretFrame = new Item("hugeMTurretFrame", Color.valueOf("4f7e81"));
        i2_6emperorsTurretFrame = new Item("emperorsTurretFrame", Color.valueOf("4f7e81"));
        i2_7armorPlate = new Item("armorPlate", Color.valueOf("4f7e81"));
        i2_8hugeArmorPlate = new Item("hugeArmorPlate", Color.valueOf("4f7e81"));
        i2_9emperorsArmorPlate = new Item("emperorsArmorPlate", Color.valueOf("4f7e81"));
        //resource
        i3_1cell = new Item("cell", Color.valueOf("5b5e62"));
        i3_2coolingCell = new Item("coolingCell", Color.valueOf("e6ff00"));
        i3_2deuterium = new Item("deuterium", Color.valueOf("0f0f0f")){{
            explosiveness = 70f;
            radioactivity = 70f;
        }};
        i3_2solidOxygen = new Item("solidOxygen", Color.valueOf("aaaaaa")){{
            flammability = 100000;
        }};
        i3_3unitCore = new Item("unitCore", Color.valueOf("aaaaaa"));
        i3_4multiCore = new Item("multiCore", Color.valueOf("ffdc62"));
        i3_5antimatterCell = new Item("antimatterCell", Color.valueOf("0000000"));

        //ammo
        i4_1metrenAmmo = new Item("metrenAmmo", Color.valueOf("4f7e81"));
        i4_1metrenMissile = new Item("metrenMissile", Color.valueOf("4f7e81"));
        i4_2emperorsCristal = new Item("emperorsCristal", Color.valueOf("FFFFA0"));
    }
}
