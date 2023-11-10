package thePackmaster.vfx.discopack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makePath;

public class DarkenFX extends AbstractGameEffect {
    private final float HALF_DUR;
    private float opacity;
    private Texture img;

    public DarkenFX(float DUR) {
        this.duration = DUR;
        this.HALF_DUR = DUR/2f;
        this.img = TexLoader.getTexture(SpireAnniversary5Mod.makePath("images/vfx/discopack/DarkenerFXimage.jpg"));
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > HALF_DUR) {
            opacity = Interpolation.pow5In.apply(0.20F, 0.F, (this.duration - HALF_DUR) / HALF_DUR);
        } else {
            opacity = Interpolation.exp10In.apply(0F, 0.20F, this.duration / HALF_DUR);
        }
        if (this.duration < 0.0F) {
            opacity = 0.0F;
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(1f, 1f, 1f, opacity);
        sb.setBlendFunction(770, 771);
        sb.draw(img,  0f, 0f, (float)Settings.WIDTH, (float)Settings.HEIGHT);
    }// 44

    public void dispose() {
    }
}
