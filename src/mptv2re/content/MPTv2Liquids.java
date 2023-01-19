package mptv2re.content;

import arc.graphics.Color;
import mindustry.content.Liquids;
import mindustry.type.*;

public class MPTv2Liquids extends Liquids {
    public static Liquid
        meter
    ;

    public static void load() {
        meter = new Liquid("meter", Color.valueOf("076c83")){{
            heatCapacity = 99f;
            temperature = -270f;
            viscosity = 0.0000001f;
        }};
    }
}
