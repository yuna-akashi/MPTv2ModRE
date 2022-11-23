package mptv2re.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class MPTv2Items {
    public static Item
        //materials
        i1-1metren, i1-1metrenGlass, i1-2metrenDiamond, i1-3titaniumAlloy,
        //building materials
        i2-1metrenFrame, i2-2hugeMetrenFrame, i2-3emperorsBuildingFrame,
        i2-4mTurretFrame, i2-5hugeMTurretFrame, i2-6emperorsTurretFrame,
        i2-7armorPlate, i2-8hugeArmorPlate, i2-9emperorsArmorPlate,
        //resources
        i3-1cell, i3-2deuterium, i3-2solidOxygen, i3-3unitCore, i3-4multiCore,
        i3-5antimatterCell,
        //ammo
        i4-1metrenAmmo, i4-1metrenMissile, i4-2emperorsCristal
    ;

    public static void load() {
        i1-1metren = new Item("i1-1metren", Color.valueOf("4f7e81"));
        i1-1metrenGlass = new Item("i1-1metrenGlass", Color.valueOf("aaaaaa"));
        i1-2metrenDiamond = new Item("i1-2metrenDiamond", Color.valueOf("aaaaaa"));
    }
}
