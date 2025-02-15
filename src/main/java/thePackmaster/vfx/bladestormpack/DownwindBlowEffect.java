package thePackmaster.vfx.bladestormpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactCurvyEffect;

//REFS: WeightyImpactEffect (base game).
public class DownwindBlowEffect extends AbstractGameEffect {
    private static final float EFFECT_DUR = 0.5F;   //was 1.0f

    private final float x;

    private float y;

    private final float targetY;

    private static TextureAtlas.AtlasRegion img;

    private boolean impactHook = false;

    public DownwindBlowEffect(float x, float y, Color newColor) {
        if (img == null)
            img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");
        this.scale = Settings.scale;
        this.x = x - img.packedWidth / 2.0F;
        this.y = Settings.HEIGHT - img.packedHeight / 2.0F;
        this.duration = EFFECT_DUR;   //was 1.0f
        this.targetY = y - 180.0F * Settings.scale;
        this.rotation = MathUtils.random(-1.0F, 1.0F);
        this.color = newColor.cpy();
    }

    public void update() {
        y = Interpolation.fade.apply(Settings.HEIGHT, targetY, 1.0F - duration / EFFECT_DUR);
        scale += Gdx.graphics.getDeltaTime();
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            this.isDone = true;
            CardCrawlGame.sound.playA("ATTACK_IRON_2", -0.5F);
        } else if (duration < 0.04F) {  //was 0.2f
            if (!impactHook) {
                impactHook = true;
                AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GRAY));
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.SHORT, true);
                int i;
                for (i = 0; i < 5; i++)
                    AbstractDungeon.effectsQueue.add(
                            new DamageImpactCurvyEffect(x + img.packedWidth / 2.0F, y + img.packedWidth / 2.0F));
                for (i = 0; i < 30; i++)
                    AbstractDungeon.effectsQueue.add(new UpgradeShineParticleEffect(x +
                            MathUtils.random(-100.0F, 100.0F) * Settings.scale + img.packedWidth / 2.0F, y +
                            MathUtils.random(-50.0F, 120.0F) * Settings.scale + img.packedHeight / 2.0F));
            }
            color.a = Interpolation.fade.apply(0.0F, 0.25F, 0.1F / duration);
        } else {
            color.a = Interpolation.pow2Out.apply(0.3F, 0.0F, duration / EFFECT_DUR);
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        color.g = 0.5F;    //was 1.0f
        sb.setColor(color);
        sb.draw(img, x, y + 140.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (duration + 0.2F) * 5.0F, scale *
                MathUtils.random(0.99F, 1.01F) * 0.3F, scale *
                MathUtils.random(0.99F, 1.01F) * 2.0F * (duration + 0.8F), rotation);

        color.g = 1.0F;    //was 0.6f
        sb.setColor(color);
        sb.draw(img, x, y + 40.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (duration + 0.2F) * 5.0F, scale *
                MathUtils.random(0.99F, 1.01F) * 0.7F, scale *
                MathUtils.random(0.99F, 1.01F) * 1.3F * (duration + 0.8F), rotation);

        color.g = 1.0F;    //was 0.2f
        sb.setColor(color);
        sb.draw(img, x, y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (duration + 0.2F) * 5.0F, scale *
                MathUtils.random(0.99F, 1.01F), scale *
                MathUtils.random(0.99F, 1.01F) * (duration + 0.8F), rotation);

        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}