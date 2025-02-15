package thePackmaster.vfx.bladestormpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.combat.SmokeBlurEffect;

//REFS: TabooDrop (Professor mod).
public class ColoredSmokeBlurEffect extends SmokeBlurEffect {
    public ColoredSmokeBlurEffect(float x, float y, Color c) {
        super(x, y);
        color.set(c);
        color.r *= MathUtils.random(0.8f, 1.2f);
        color.g *= MathUtils.random(0.8f, 1.2f);
        color.b *= MathUtils.random(0.8f, 1.2f);
        color.clamp();
    }
}
