package thePackmaster.orbs.runicpack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.vfx.BobEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.spherespack.BlazeOrbActivateEffect;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class Glyph extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Glyph.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/orbs/spherespack/Blaze.png");
    private static final float SPIRIT_WIDTH = 96.0f;
    private static final Texture[] Runes = {
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune1.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune2.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune3.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune4.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune5.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune6.png"),
            TexLoader.getTexture("anniv5Resources/images/vfx/runicpack/Rune7.png")
    };

    private final static int BASE_PASSIVE = 1;
    private final static int BASE_EVOKE = 3;

    private float vfxTimer = 0.2f;

    private final BobEffect bobEffect = new BobEffect(2f, 3f);
    private DamageInfo info;
    private AbstractOrb orb;

    public Glyph() {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_EVOKE, "", "", IMG_PATH);
        int randomRune = AbstractDungeon.miscRng.random(0, 6);
        this.img = Runes[randomRune];
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
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCreature m = Wiz.getRandomEnemy();
                if (m != null) {
                    info = new DamageInfo(AbstractDungeon.player, passiveAmount, DamageInfo.DamageType.THORNS);
                    info.output = AbstractOrb.applyLockOn(m, passiveAmount);
                    Wiz.atb(new DamageAction(m, info, AbstractGameAction.AttackEffect.NONE, true));
                }
                this.isDone = true;
            }
        });
        Wiz.atb(new GainBlockAction(Wiz.p(), passiveAmount, true));
        this.updateDescription();
    }

    @Override
    public void onEvoke() {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCreature m = Wiz.getRandomEnemy();
                if (m != null) {
                    info = new DamageInfo(AbstractDungeon.player, evokeAmount, DamageInfo.DamageType.THORNS);
                    info.output = AbstractOrb.applyLockOn(m, evokeAmount);
                    Wiz.atb(new DamageAction(m, info, AbstractGameAction.AttackEffect.NONE, true));
                }
                this.isDone = true;
            }
        });
        Wiz.atb(new GainBlockAction(Wiz.p(), evokeAmount, true));
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
        super.renderText(sb);
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