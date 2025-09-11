//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package thePackmaster.effects.runicpack;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;

public class RunicSpearEffect {
    public static Texture SPEAR = new Texture("anniv5Resources/images/vfx/runicpack/spear.png");

    public RunicSpearEffect() {
    }

    public static AbstractGameEffect RunicLightningSpearThrow(AbstractCreature c, boolean muted) {
        AbstractPlayer p = AbstractDungeon.player;
        float angle = getAngle(c);
        Color color = new Color(0.8f, 1.0F, 1.0f, 1.0F);
        return muted ? (new VfxBuilder(SPEAR, p.hb.cX, p.hb.cY + p.hb.height / 2.0F, 0.5F)).setAngle(angle).setColor(color).scale(0.0F, 0.3F).emitEvery((x, y) -> {
            return (new VfxBuilder(ImageMaster.GLOW_SPARK_2, x, y, 0.25F)).fadeOut(0.3F).setX(x + MathUtils.random(-300.0F * Settings.scale, 300.0F * Settings.scale)).setY(y + MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale)).setScale(0.3F).setColor(color).build();
        }, 0.01F).andThen(0.25F).emitEvery((x, y) -> {
            return (new VfxBuilder(ImageMaster.GLOW_SPARK_2, x, y, 0.25F)).fadeOut(0.3F).setX(x + MathUtils.random(-300.0F * Settings.scale, 300.0F * Settings.scale)).setY(y + MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale)).setScale(0.3F).setColor(color).build();
        }, 0.01F).andThen(0.2F).setScale(0.3F).moveX(p.hb.cX, c.hb.cX).moveY(p.hb.cY + p.hb.height / 2.0F, c.hb.cY).emitEvery((x, y) -> {
            return (new VfxBuilder(SPEAR, x, y, 0.5F)).fadeOutFromAlpha(0.3F, 0.4F).setScale(0.3F).setAngle(angle).setColor(color).build();
        }, 0.05F).build() : (new VfxBuilder(SPEAR, p.hb.cX, p.hb.cY + p.hb.height / 2.0F, 0.5F)).playSoundAt(0.01F, SpireAnniversary5Mod.makeID("RipPack_Charge")).setAngle(angle).setColor(color).scale(0.0F, 0.3F).emitEvery((x, y) -> {
            return (new VfxBuilder(ImageMaster.GLOW_SPARK_2, x, y, 0.25F)).fadeOut(0.3F).setX(x + MathUtils.random(-300.0F * Settings.scale, 300.0F * Settings.scale)).setY(y + MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale)).setScale(0.3F).setColor(color).build();
        }, 0.01F).andThen(0.25F).emitEvery((x, y) -> {
            return (new VfxBuilder(ImageMaster.GLOW_SPARK_2, x, y, 0.25F)).fadeOut(0.3F).setX(x + MathUtils.random(-300.0F * Settings.scale, 300.0F * Settings.scale)).setY(y + MathUtils.random(-100.0F * Settings.scale, 100.0F * Settings.scale)).setScale(0.3F).setColor(color).build();
        }, 0.01F).andThen(0.2F).playSoundAt(0.01F, SpireAnniversary5Mod.makeID("RipPack_SpearThrow")).setScale(0.3F).moveX(p.hb.cX, c.hb.cX).moveY(p.hb.cY + p.hb.height / 2.0F, c.hb.cY).emitEvery((x, y) -> {
            return (new VfxBuilder(SPEAR, x, y, 0.5F)).fadeOutFromAlpha(0.3F, 0.4F).setScale(0.3F).setAngle(angle).setColor(color).build();
        }, 0.05F).build();
    }

    public static AbstractGameEffect RunicLightningSpearThrowFast(AbstractCreature c, boolean muted) {
        AbstractPlayer p = AbstractDungeon.player;
        float angle = getAngle(c);
        Color color = new Color(0.8f, 1.0F, 1.0f, 1.0F);
        return muted ? (new VfxBuilder(SPEAR, p.hb.cX, p.hb.cY + p.hb.height / 2.0F, 0.25F)).setAngle(angle).setScale(0.3F).moveX(p.hb.cX, c.hb.cX).moveY(p.hb.cY + p.hb.height / 2.0F, c.hb.cY).emitEvery((x, y) -> {
            return (new VfxBuilder(SPEAR, x, y, 0.5F)).fadeOutFromAlpha(0.3F, 0.4F).setScale(0.3F).setAngle(angle).setColor(color).build();
        }, 0.05F).build() : (new VfxBuilder(SPEAR, p.hb.cX, p.hb.cY + p.hb.height / 2.0F, 0.25F)).setAngle(angle).playSoundAt(0.01F, SpireAnniversary5Mod.makeID("RipPack_SpearThrow")).setScale(0.3F).moveX(p.hb.cX, c.hb.cX).moveY(p.hb.cY + p.hb.height / 2.0F, c.hb.cY).emitEvery((x, y) -> {
            return (new VfxBuilder(SPEAR, x, y, 0.5F)).fadeOutFromAlpha(0.3F, 0.4F).setScale(0.3F).setAngle(angle).setColor(color).build();
        }, 0.05F).build();
    }

    private static float getAngle(AbstractCreature c) {
        AbstractPlayer p = AbstractDungeon.player;
        float distance = c.hb.cX - p.hb.cX;
        float height = p.hb.cY + p.hb.height / 2.0F - c.hb.cY;
        float angle = (float)Math.toDegrees(Math.atan((double)(distance / height)));
        return height < 0.0F ? angle + 180.0F : angle;
    }
}
