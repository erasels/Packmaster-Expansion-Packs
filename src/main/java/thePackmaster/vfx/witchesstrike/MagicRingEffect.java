package thePackmaster.vfx.witchesstrike;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MagicRingEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private TextureAtlas.AtlasRegion img;
    private Color altColor;

    public MagicRingEffect(float X, float Y, Color setColor, Color altColor) {
        if (this.img == null) {// 22
            this.img = ImageMaster.CRYSTAL_IMPACT;// 23
        }

        this.x = X - (float)this.img.packedWidth / 2.0F;// 26
        this.y = Y - (float)this.img.packedHeight / 2.0F;// 27
        this.startingDuration = 0.7F;// 29
        this.duration = this.startingDuration;// 30
        this.scale =  (float) (Settings.scale*0.1);// 31
        this.altColor = altColor;// 32
        this.color = setColor.cpy();// 33
        this.color.a = 0.0F;// 34
        this.renderBehind = false;// 35
    }// 37

    public MagicRingEffect() {
        if (this.img == null) {// 40
            this.img = ImageMaster.CRYSTAL_IMPACT;// 41
        }

        this.x = AbstractDungeon.player.hb.cX - (float)this.img.packedWidth / 2.0F;// 44
        this.y = AbstractDungeon.player.hb.cY - (float)this.img.packedHeight / 2.0F;// 45
        this.startingDuration = 0.7F;// 47
        this.duration = this.startingDuration;// 48
        this.scale = (float) (Settings.scale*0.1);// 49
        this.altColor = new Color(1.0F, 0.6F, 0.2F, 0.0F);// 50
        this.color = Settings.GOLD_COLOR.cpy();// 51
        this.color.a = 0.0F;// 52
        this.renderBehind = false;// 53
    }// 54

    public void update() {

        this.duration -= Gdx.graphics.getDeltaTime();// 61
        if (this.duration > this.startingDuration / 2.0F) {// 63
            this.color.a = Interpolation.fade.apply(0.5F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;// 64
        } else {
            this.color.a = Interpolation.fade.apply(0.01F, 0.5F, this.duration / (this.startingDuration / 2.0F)) * Settings.scale;// 66
        }

        this.scale = Interpolation.pow5In.apply(1.25F, 0.3F, this.duration / this.startingDuration) * Settings.scale;// 69
        if (this.duration < 0.0F) {// 71
            this.isDone = true;// 72
        }

    }// 74

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);// 78
        this.altColor.a = this.color.a;// 79
        sb.setColor(this.altColor);// 80
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 1.1F, this.scale * 1.1F, 0.0F);// 81
        sb.setColor(this.color);// 93
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * 0.9F, this.scale * 0.9F, 0.0F);// 94
        sb.setBlendFunction(770, 771);// 105
    }// 106

    public void dispose() {
    }// 110
}
