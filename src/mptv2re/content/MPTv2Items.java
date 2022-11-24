package mptv2re.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class MPTv2Items {
    public static Item
        //materials
        metren, metrenGlass, metrenDiamond, metrenSilicon,
            titaniumAlloy, compressedThorium,
        //building materials
        metrenFrame, largeMetrenFrame, superMetrenFrame,
        turretFrame, largeTurretFrame, superTurretFrame,
        armorPlate, heavyArmorPlate, superArmorPlate,
        //resources
        cell, coolingCell, deuterium, solidOxygen, unitCore, multiCore,
        antimatterCell,
        //ammo
        metrenAmmo, metrenMissile, emperorsCristal
    ;

    public static void load() {
        //material
        metren = new Item("metren", Color.valueOf("4f7e81"));
        metrenGlass = new Item("metrenGlass", Color.valueOf("bbbbbb"));
        metrenDiamond = new Item("metrenDiamond", Color.valueOf("5efffa"));
        metrenSilicon = new Item("metrenSilicon",Color.valueOf("5A7E80"));
        titaniumAlloy = new Item("titaniumAlloy", Color.valueOf("aaaaaa"));
        compressedThorium = new Item("compressedThorium", Color.valueOf("4f7e81")){{
            explosiveness = 0.9f;
            radioactivity = 4f;
            flammability = 1.5f;
        }};

        //building material
        metrenFrame = new Item("metrenFrame", Color.valueOf("4f7e81"));
        largeMetrenFrame = new Item("largeMetrenFrame", Color.valueOf("4f7e81"));
        superMetrenFrame = new Item("specialMetrenFrame", Color.valueOf("4f7e81"));
        turretFrame = new Item("turretFrame", Color.valueOf("4f7e81"));
        largeTurretFrame = new Item("largeTurretFrame", Color.valueOf("4f7e81"));
        superTurretFrame = new Item("specialTurretFrame", Color.valueOf("4f7e81"));
        armorPlate = new Item("armorPlate", Color.valueOf("4f7e81"));
        heavyArmorPlate = new Item("heavyArmorPlate", Color.valueOf("4f7e81"));
        superArmorPlate = new Item("specialArmorPlate", Color.valueOf("4f7e81"));

        //resource
        cell = new Item("cell", Color.valueOf("5b5e62"));
        coolingCell = new Item("coolingCell", Color.valueOf("e6ff00"));
        deuterium = new Item("deuterium", Color.valueOf("0f0f0f")){{
            explosiveness = 70f;
            radioactivity = 70f;
        }};
        solidOxygen = new Item("solidOxygen", Color.valueOf("aaaaaa")){{
            flammability = 100000;
        }};
        unitCore = new Item("unitCore", Color.valueOf("aaaaaa"));
        multiCore = new Item("multiCore", Color.valueOf("ffdc62"));
        antimatterCell = new Item("antimatterCell", Color.valueOf("0000000"));

        //ammo
        metrenAmmo = new Item("metrenAmmo", Color.valueOf("4f7e81"));
        metrenMissile = new Item("metrenMissile", Color.valueOf("4f7e81"));
        emperorsCristal = new Item("emperorsCristal", Color.valueOf("FFFFA0"));
    }
}
