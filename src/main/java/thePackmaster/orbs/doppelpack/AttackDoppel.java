package thePackmaster.orbs.doppelpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.AnimateDoppelAction;
import thePackmaster.packs.DoppelPack;

public class AttackDoppel extends AbstractDoppel {
    public static String ORB_ID = SpireAnniversary5Mod.makeID(AttackDoppel.class.getSimpleName());
    public static final OrbStrings ORB_STRING = CardCrawlGame.languagePack.getOrbString(ORB_ID);

    public int baseDamage;
    protected int damage;
    protected final boolean multiDamage;
    protected AbstractGameAction.AttackEffect attackEffect;
    protected DoppelPack.CardProperty cardProperty;

    public AttackDoppel(AbstractCard card, DoppelPack.CardProperty cardProperty) {
        super(card);
        this.ID = ORB_ID;
        this.name = ORB_STRING.NAME;
        this.cardProperty = cardProperty;
        AbstractGameAction.AttackEffect attackEffect = cardProperty.getAttackEffect();
        this.attackEffect = attackEffect == null || attackEffect == AbstractGameAction.AttackEffect.NONE ?
                AbstractGameAction.AttackEffect.BLUNT_LIGHT : attackEffect;
        this.baseDamage = this.getDoppelDamage(card);
        this.multiDamage = ReflectionHacks.getPrivate(card, AbstractCard.class, "isMultiDamage");
        this.img = getAttackImage(baseDamage);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        applyFocus();
        this.description = String.format(ORB_STRING.DESCRIPTION[multiDamage ? 1 : 0], this.damage, this.getCardName());
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
        updateAttackAnimation();
    }

    @Override
    protected void applyPowers(int numFocus, int numWormHole) {
        AbstractPlayer player = AbstractDungeon.player;
        AbstractPower strengthPower = player == null ? null : player.getPower(StrengthPower.POWER_ID);
        int numStrength = strengthPower == null ? 0 : strengthPower.amount;
        this.damage = Math.max(0, this.baseDamage + numFocus + numStrength * numWormHole);
        this.img = getAttackImage(damage);
    }

    @Override
    public void onEndOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new AnimateDoppelAction(this, 0.4f));
        if (this.multiDamage) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(
                    AbstractDungeon.player,
                    DamageInfo.createDamageMatrix(this.damage, true, false),
                    DamageInfo.DamageType.THORNS,
                    this.attackEffect));
        } else {
            AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
                    new DamageInfo(AbstractDungeon.player, this.damage, DamageInfo.DamageType.THORNS),
                    this.attackEffect));
        }
    }

    @Override
    protected AbstractDoppel makeDoppelCopy() {
        return new AttackDoppel(this.card, this.cardProperty);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        int size = this.img.getWidth();
        sb.draw(this.img,
                this.cX - size / 2f,
                this.cY - size / 2f + this.bobEffect.y,
                size / 2f, size / 2f,
                size, size,
                this.scale, this.scale,
                0,
                0, 0,
                size, size,
                true, false);

        if (!this.showEvokeValue) {
            String text = String.valueOf(this.damage);
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, text, this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, this.c, this.fontScale);
        }

        this.hb.render(sb);
    }

    protected Texture getAttackImage(int dmg) {
        if (dmg < 5)
            return ImageMaster.INTENT_ATK_1;
        if (dmg < 10)
            return ImageMaster.INTENT_ATK_2;
        if (dmg < 15)
            return ImageMaster.INTENT_ATK_3;
        if (dmg < 20)
            return ImageMaster.INTENT_ATK_4;
        if (dmg < 25)
            return ImageMaster.INTENT_ATK_5;
        if (dmg < 30) {
            return ImageMaster.INTENT_ATK_6;
        }
        return ImageMaster.INTENT_ATK_7;
    }

    protected void updateAttackAnimation() {
        AbstractPlayer player = AbstractDungeon.player;

        float targetPos = (player != null && player.flipHorizontal ? -1 : 1) * 90.0F * Settings.scale;
        if (this.animationTimer > 0.5F) {
            this.animX = Interpolation.exp5In.apply(0.0F, targetPos, (1.0F - this.animationTimer) * 2.0F);
        } else if (this.animationTimer < 0.0F) {
            this.animationTimer = 0.0F;
            this.animX = 0.0F;
        } else {
            this.animX = Interpolation.fade.apply(0.0F, targetPos, this.animationTimer * 2.0F);
        }
    }
}
