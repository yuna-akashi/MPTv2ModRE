package mptv2re.content;

import arc.graphics.Color;
import mindustry.content.Liquids;
import mindustry.type.*;

public class MPTv2Liquids extends Liquids {
    public static Liquid
        deuterium, tritium, meter
    ;

    public static void load() {
        deuterium = new Liquid("deuterium", Color.valueOf("41b9cb")){{
            gas = true;
            flammability = 1.25f;
        }};
        tritium = new Liquid("tritium", Color.valueOf("927fff")){{
            gas = true;
            flammability = 1.75f;
        }};
        meter = new Liquid("meter", Color.valueOf("076c83")){{
            heatCapacity = 99f;
            temperature = -270f;
            viscosity = 0.0000001f;
        }};
    }
}
