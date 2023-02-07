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

//    public static void showNew(){
//        new BaseDialog("Detected Update"){{
//            addCloseListener();
//
//            cont.pane(main -> {
//                main.top();
//                main.pane(table -> {
//                    table.align(Align.topLeft);
//                    table.add(MOD.meta.version + ": ").row();
//                    table.image().height(OFFSET / 3).growX().color(Pal.accent).row();
//                    table.add(Core.bundle.get("mod.ui.update-log")).left();
//                }).growX().fillY().padBottom(LEN).row();
//                main.image().growX().height(4).pad(6).color(Color.lightGray).row();
//                main.pane(t -> {
//                    for(int index = 0; index < getUpdateContent().length; index++){
//                        FeatureLog c = getUpdateContent()[index];
//                        Table info = new Table(Tex.pane, table -> {
//                            if(c.important || c.content != null){
//                                table.background(Tex.whitePane);
//                                if(c.important)table.color.set(Pal.accent);
//                            }
//
//                            table.table(i -> {
//                                i.image(c.icon).fill();
//                            }).fill().get().pack();
//                            table.pane(i -> {
//                                i.top();
//                                i.add("[gray]NEW [lightgray]" + c.type.toUpperCase() + "[]: [accent]" + c.title + "[]").left().row();
//                                i.image().growX().height(OFFSET / 3).pad(OFFSET / 3).color(Color.lightGray).row();
//                                i.add("[accent]Description: []").left().row();
//                                i.add(c.description).padLeft(LEN).left().get().setWrap(true);
//                                if(c.modifier != null)i.table(i1 -> {
//                                    NHUIFunc.show(i1, c.content);
//                                }).grow().left().row();
//                                if(c.modifier != null)c.modifier.get(i);
//                            }).grow().padLeft(OFFSET).top();
//                            table.button(Icon.info, Styles.cleari, LEN, () -> {
//                                ContentInfoDialog dialog = new ContentInfoDialog();
//                                dialog.show(c.content);
//                            }).growY().width(LEN).padLeft(OFFSET).disabled(b -> c.content == null);
//                        });
//                        if(!c.important)t.add(info).grow().row();
//                        else{
//                            Label label = new Label("IMPORTANT", Styles.techLabel);
//                            label.setFontScale(1.25f);
//
//                            t.stack(new Table(table -> table.margin(OFFSET * 2).add(label)).bottom(), new Table(Styles.black6){{setFillParent(true);}}, info).grow().row();
//                        }
//                    }
//                }).growX().top().row();
//            }).grow().padLeft(LEN).padRight(LEN).padTop(LEN).row();
//
//            cont.table(table -> {
//                table.button("@back", Icon.left, Styles.cleart, this::hide).growX().height(LEN);
//            }).bottom().growX().height(LEN).padTop(OFFSET).padLeft(LEN).padRight(LEN);
//        }}.show();
//    }

    public MPTv2RE(){
        Log.info("Loaded MPTv2 constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Metren Processing Technology v2");
                dialog.cont.add("Mod loaded.").row();
                dialog.cont.image(Core.atlas.find("mptv2-re-pemu-carrier-full")).pad(5f).row();
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
