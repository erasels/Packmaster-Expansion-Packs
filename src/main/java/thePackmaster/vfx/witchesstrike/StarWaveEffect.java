package thePackmaster.vfx.witchesstrike;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BlurWaveAdditiveEffect;
import com.megacrit.cardcrawl.vfx.combat.BlurWaveChaoticEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

public class StarWaveEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private ShockWaveEffect.ShockWaveType type;
    private Color color;

    public StarWaveEffect(float x, float y, Color color, ShockWaveEffect.ShockWaveType type) {
        this.x = x;// 19
        this.y = y;// 20
        this.type = type;// 21
        this.color = color;// 22
    }// 23

    public void update() {
        float speed = MathUtils.random(1000.0F, 1200.0F) * Settings.scale;// 26
        int i;
        label32:
        switch (this.type) {// 27
            case ADDITIVE:
                i = 0;

                while(true) {
                    if (i >= 40) {
                        break label32;
                    }

                    AbstractDungeon.effectsQueue.add(new BlurWaveAdditiveEffect(this.x, this.y, this.color.cpy(), speed));// 30
                    ++i;// 29
                }
            case NORMAL:
                i = 0;

                while(true) {
                    if (i >= 60) {
                        break label32;
                    }

                    AbstractDungeon.effectsQueue.add(new StarEffect(this.x, this.y, this.color.cpy(), speed));// 35
                    ++i;// 34
                }
            case CHAOTIC:
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, false);// 39

                for(i = 0; i < 40; ++i) {// 40
                    AbstractDungeon.effectsQueue.add(new BlurWaveChaoticEffect(this.x, this.y, this.color.cpy(), speed));// 41
                }
        }

        this.isDone = true;// 45
    }// 46

    public void render(SpriteBatch sb) {
    }// 55

    public void dispose() {
    }// 60

    public static enum ShockWaveType {
        ADDITIVE,
        NORMAL,
        CHAOTIC;

        private ShockWaveType() {
        }// 48
    }
}