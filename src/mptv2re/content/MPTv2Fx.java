package mptv2re.content;

import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Rand;
import arc.math.geom.Vec2;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class MPTv2Fx extends Fx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect
            purpleLaserCharge = new Effect(80f, 100f, e -> {
                color(Pal.suppress);
                stroke(e.fin() * 2f);
                Lines.circle(e.x, e.y, 4f + e.fout() * 100f);

                Fill.circle(e.x, e.y, e.fin() * 20);

                randLenVectors(e.id, 20, 40f * e.fout(), (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, e.fin() * 5f);
                    Drawf.light(e.x + x, e.y + y, e.fin() * 15f, Pal.suppress, 0.7f);
                });

                color();

                Fill.circle(e.x, e.y, e.fin() * 10);
                Drawf.light(e.x, e.y, e.fin() * 20f, Pal.heal, 0.7f);
            }).followParent(true).rotWithParent(true);
}
