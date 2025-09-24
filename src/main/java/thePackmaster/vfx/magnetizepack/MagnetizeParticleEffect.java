package thePackmaster.vfx.magnetizepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

// Largely copied/referenced from Psychic Pack's RuneParticleEffect
public class MagnetizeParticleEffect extends AbstractGameEffect {
    private static final TextureAtlas.AtlasRegion img = ImageMaster.ATK_SLASH_V;
    private static float SPEED = 1f;
    private static float SCALE = 0.3f;
    private static float MIN_X_OFFSET = 0f * Settings.scale;
    private static float MAX_X_OFFSET = 200f * Settings.scale;
    private static float Y_OFFSET = -200f;
    private static float START_DURATION = 2f;
    private static float MID_DURATION = START_DURATION / 2f;
    private static Color col = Color.GRAY;
    private float x;
    private float y;

    public MagnetizeParticleEffect(float x, float y, float scale) {
        duration = startingDuration = START_DURATION;
        color = col.cpy();

        this.x = x + MathUtils.random(MIN_X_OFFSET, MAX_X_OFFSET) * scale;
        this.y = y + Y_OFFSET;
        this.scale = SCALE * scale;
    }

    public void update() {
        duration -= Gdx.graphics.getDeltaTime();

        y += SPEED;

        if (duration > MID_DURATION)
            color.a = Interpolation.pow2Out.apply(0f, 1f, (START_DURATION - duration) / MID_DURATION);
        else
            color.a = Interpolation.pow2Out.apply(1f, 0f, (MID_DURATION - duration )/ MID_DURATION);

        isDone = duration < 0;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(img, x, y, img.packedWidth / 2f, img.packedHeight / 2f, img.packedWidth, img.packedHeight, scale, scale, 0.0F);
    }

    public void dispose() {
    }
}
