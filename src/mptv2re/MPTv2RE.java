package mptv2re;

import arc.*;
import arc.graphics.Color;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.graphics.Pal;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import mptv2re.content.*;

public class MPTv2RE extends Mod{
    public static final String MOD_RELEASES = "https://github.com/Yunatexya/MPTv2ModRE/releases";
    public static final String MOD_REPO = "Yunatexya/MPTv2ModRE";
    public static final String MOD_GITHUB_URL = "https://github.com/Yunatexya/MPTv2ModRE.git";
    public static final String MOD_NAME = "mptv2-re";

    public static Mods.LoadedMod MOD;
    public static String name(String name){
        return MOD_NAME + "-" + name;
    }

    public MPTv2RE(){
        Log.info("Loaded MPTv2 constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Metren Processing Technology v2");
                dialog.cont.add("Attention!").row();
                dialog.cont.add("This mod is integrated into AncientRaids.").row();
                dialog.cont.button("Ok", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){

        {
            MPTv2Items.load();
            MPTv2UnitTypes.load();
            MPTv2Liquids.load();
            MPTv2Blocks.load();
            MPTv2RETechTree.load();
        }
        Log.info("Loading some MPTv2 content.");
    }

}
