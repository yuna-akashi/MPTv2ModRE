package mptv2re;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import mptv2re.content.*;

public class MPTv2RE extends Mod{
    public static final String MOD_RELEASES = "https://github.com/Yunatexya/MPTv2ModRE/releases";
    public static final String MOD_REPO = "Yunatexya/MPTv2ModRE";
    public static final String MOD_GITHUB_URL = "https://github.com/Yunatexya/MPTv2ModRE.git";
    public static final String MOD_NAME = "mptv2-re";
    public static String name(String name){
        return MOD_NAME + "-" + name;
    }

    public MPTv2RE(){
        Log.info("Loaded MPTv2 constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("antimatter");
                dialog.cont.add("MPTv2 RE Mod").row();
                dialog.cont.image(Core.atlas.find("mptv2-re-antimatterRailgun-icon")).pad(5f).row();
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
            MPTv2Blocks.load();
            MPTv2Liquids.load();
            MPTv2RETechTree.load();
        }
        Log.info("Loading some MPTv2 content.");
    }

}
