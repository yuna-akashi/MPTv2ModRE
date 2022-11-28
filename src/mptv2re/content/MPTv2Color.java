package mptv2re.content;

import arc.graphics.Color;
import arc.graphics.Colors;
import mindustry.graphics.Pal;

public class MPTv2Color {
    public static Color
        metren = Color.valueOf("#4f7e81"),
        metrenLight = Color.valueOf("#639194"),
        metrenDark = Color.valueOf("3c676a")
    ;

    static{
        Colors.put("heal", Pal.heal);
    }
}
