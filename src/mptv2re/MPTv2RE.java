package mptv2re;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import mptv2re.content.MPTv2Blocks;
import mptv2re.content.MPTv2Items;
import mptv2re.content.MPTv2RETechTree;

public class MPTv2RE extends Mod{

    public MPTv2RE(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("mptv2-re-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){

        MPTv2Items.load();
        MPTv2Blocks.load();
        MPTv2RETechTree.load();
        Log.info("Loading some example content.");
    }

}
