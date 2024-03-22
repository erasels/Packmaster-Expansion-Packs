package thePackmaster.vfx.needlework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowShivEffect;

public class MiniDieDieEffect extends AbstractGameEffect {
    private float interval = 0.0F;

    public MiniDieDieEffect() {
        this.duration = 0.1F;
    }

    public void update() {
        this.interval -= Gdx.graphics.getDeltaTime();
        if (this.interval < 0.0F) {
            this.interval = MathUtils.random(0.015F, 0.03F);
            int amt = MathUtils.random(1, 3);

            for(int i = 0; i < amt; ++i) {
                AbstractDungeon.effectsQueue.add(new ThrowShivEffect(MathUtils.random(1200.0F, 2000.0F) * Settings.scale, AbstractDungeon.floorY + MathUtils.random(-100.0F, 500.0F) * Settings.scale));
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}