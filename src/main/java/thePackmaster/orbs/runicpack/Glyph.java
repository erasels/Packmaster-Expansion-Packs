package thePackmaster.orbs.runicpack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleXLEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.spherespack.BlazeOrbActivateEffect;
import thePackmaster.vfx.spherespack.BlazeOrbFlareEffect;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.adp;

public class Glyph extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Glyph.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/orbs/spherespack/Blaze.png");
    private static final float SPIRIT_WIDTH = 96.0f;

    private final static int BASE_PASSIVE = 1;
    private final static int BASE_EVOKE = 4;

    private float vfxTimer = 0.2f;

    private final BobEffect bobEffect = new BobEffect(2f, 3f);
    private DamageInfo info;
    private AbstractOrb orb;

    public Glyph() {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_EVOKE, "", "", IMG_PATH);
        int randomRune = AbstractDungeon.miscRng.random(0, 7);
        switch (randomRune){
            case 0:
                this.img = new Texture("anniv5Resources/images/vfx/runicpack/Rune1.png");
                break;
            case 1:
                this.img = new Texture("anniv5Resources/images/vfx/runicpack/Rune2.png");
                break;
            case 2:
                this.img = new Texture("anniv5Resources/images/vfx/runicpack/Rune3.png");
                break;
            case 3:
                this.img = new Texture("anniv5Resources/images/vfx/runicpack/Rune4.png");
                break;
            case 4:
                this.img = new Texture("anniv5Resources/images/vfx/runicpack/Rune5.png");
                break;
            case 5:
                this.img = new Texture("anniv5Resources/images/vfx/runicpack/Rune6.png");
                break;
            case 6:
                this.img = new Texture("anniv5Resources/images/vfx/runicpack/Rune7.png");
                break;
            default:
                this.img = new Texture("anniv5Resources/images/vfx/runicpack/Rune1.png");
                break;
        }
        applyFocus();
        updateDescription();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1f);
    }

    @Override
    public void applyFocus() {
        AbstractPower power = adp().getPower(FocusPower.POWER_ID);
        this.passiveAmount = power != null ? Math.max(0, basePassiveAmount + power.amount) : this.basePassiveAmount;
        this.evokeAmount = power != null ? Math.max(0, baseEvokeAmount + power.amount) : this.baseEvokeAmount;
    }

    @Override
    public void onEndOfTurn() {
        float speedTime = Settings.FAST_MODE ? 0.0F : 0.6F / (float)AbstractDungeon.player.orbs.size();
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new GlyphOrbEffect(this), speedTime));
        AbstractCreature m = Wiz.getRandomEnemy();
        if (m != null) {
            this.info = new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageInfo.DamageType.THORNS);
            this.info.output = AbstractOrb.applyLockOn(m, passiveAmount);
            Wiz.atb(new DamageAction(m, this.info, AbstractGameAction.AttackEffect.NONE, true));
        }
        Wiz.atb(new GainBlockAction(Wiz.p(), passiveAmount, true));
        // Wiz.atb(new ApplyPowerAction(Wiz.p(), Wiz.p(), new VigorPower(Wiz.p(), evokeAmount), evokeAmount, true));
        this.updateDescription();
    }

    @Override
    public void onEvoke() {
        AbstractCreature m = Wiz.getRandomEnemy();
        if (m != null) {
            this.info = new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageInfo.DamageType.THORNS);
            info.output = AbstractOrb.applyLockOn(m, evokeAmount);
            Wiz.atb(new DamageAction(m, this.info, AbstractGameAction.AttackEffect.NONE, true));
        }
        Wiz.atb(new GainBlockAction(Wiz.p(), evokeAmount, true));
        // Wiz.atb(new ApplyPowerAction(Wiz.p(), Wiz.p(), new VigorPower(Wiz.p(), evokeAmount), evokeAmount, true));
    }

    @Override
    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("TINGSHA", 0.1f);
        AbstractDungeon.effectsQueue.add(new BlazeOrbActivateEffect(this.cX, this.cY));
    }

    @Override
    public void updateAnimation() {
        bobEffect.update();

        vfxTimer -= Gdx.graphics.getDeltaTime();

        cX = MathHelper.orbLerpSnap(cX, adp().animX + tX);
        cY = MathHelper.orbLerpSnap(cY, adp().animY + tY);
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - SPIRIT_WIDTH /2F, cY - SPIRIT_WIDTH /2F + bobEffect.y, SPIRIT_WIDTH /2F, SPIRIT_WIDTH /2F,
                SPIRIT_WIDTH, SPIRIT_WIDTH, scale, scale, 0f, 0, 0, (int) SPIRIT_WIDTH, (int) SPIRIT_WIDTH,
                false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, this.c, this.fontScale);
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESCRIPTIONS[0].replace("{0}", this.passiveAmount + "").replace("{1}", this.evokeAmount + "");
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Glyph();
    }
}