package thePackmaster.stances.runicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.util.TexLoader;

public class RuneParticle
        extends AbstractGameEffect {
    private static final float EFFECT_DUR = 2.0F;
    private float x;
    private float y;
    private float speed;
    private float speedStart;
    private float speedTarget;
    private float scaleXMod;
    private float flipper;
    private Texture img;
    private float dur_div2 = this.duration / 2.0F;
    public static Texture RUNE;
    private float baseScale;
    private static final Texture[] Runes = {
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune1.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune2.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune3.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune4.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune5.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune6.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune7.png")
    };

    public RuneParticle(Color color) {
        int randomRune = AbstractDungeon.miscRng.random(0, 6);
        this.img = Runes[randomRune];
        this.rotation = 0f;
        this.scale = MathUtils.random(0.1f, 0.5f);
        this.baseScale = scale;
        this.x = AbstractDungeon.player.hb.cX + (150f * Settings.scale) - (float)(Math.random() * 300f * Settings.scale);
        this.y = AbstractDungeon.player.hb.cY + (120f * Settings.scale) - (float)(Math.random() * 200f * Settings.scale);
        this.duration = EFFECT_DUR;
        this.color = color;
        this.renderBehind = MathUtils.randomBoolean();
        this.speedStart = MathUtils.random(0.5f, 2F) * Settings.scale;
        this.speedTarget = 0f;
        this.speed = this.speedStart;

        if (MathUtils.randomBoolean()) {
            this.flipper = 90.0F;
        } else {
            this.flipper = 270.0F;
        }
        color.g -= MathUtils.random(0.1F);
        color.b -= MathUtils.random(0.2F);
        color.a = 0.0F;
    }

    public void update() {
        this.speed = Interpolation.fade.apply(this.speedStart, this.speedTarget, (EFFECT_DUR - this.duration) / this.duration);
        this.y -= this.speed;

        if (this.duration > dur_div2) {
            this.scale = Interpolation.fade.apply(this.baseScale, this.baseScale * 1.2f, (EFFECT_DUR - this.duration) / dur_div2);
        }
        else {
            this.scale -= Interpolation.fade.apply(this.scale, 0f, (EFFECT_DUR - dur_div2 - this.duration) / dur_div2);
        }
        this.duration -= Gdx.graphics.getDeltaTime();

        if (this.duration < 0.0F) {
            this.isDone = true;
        } else if (this.duration > 1.0f) {
            this.color.a = Interpolation.fade.apply(0.0F, 0.7F, (EFFECT_DUR - this.duration) / this.duration );
        } else if (this.duration < 1.0f) {
            this.color.a = Interpolation.fade.apply(0.7f, 0.0f, 1.0f - this.duration );
        }
    }


    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, this.img.getWidth() * this.scale, this.img.getHeight() * this.scale);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}


