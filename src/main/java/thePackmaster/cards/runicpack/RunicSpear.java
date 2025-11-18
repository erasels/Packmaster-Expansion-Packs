package thePackmaster.cards.runicpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.effects.runicpack.RunicSpearEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RunicSpear extends AbstractRunicCard {

    private static final int COST = 2;
    private static final int DAMAGE = 17;
    private static final int UPG_DMG = 6;
    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 2;
    public final static String ID = makeID("RunicSpear");

    public RunicSpear() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new VFXAction(RunicSpearEffect.RunicLightningSpearThrow(abstractMonster, false), 1.0f));
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
            int originalBaseDamage = this.baseDamage;
            if (AbstractDungeon.player.hasPower(FocusPower.POWER_ID)) {
                this.baseDamage += AbstractDungeon.player.getPower(FocusPower.POWER_ID).amount * magicNumber;
            }
            if (mo.hasPower(LockOnPower.POWER_ID)) {
                this.baseDamage = AbstractOrb.applyLockOn(mo, baseDamage);
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
            else {
                triggerOnGlowCheck();
            }
            super.calculateCardDamage(mo);
            this.baseDamage = originalBaseDamage;
            this.isDamageModified = this.damage != originalBaseDamage;
    }

    @Override
    public void applyPowers() {
        int originalBaseDamage = this.baseDamage;
        if (AbstractDungeon.player.hasPower(FocusPower.POWER_ID)) {
            baseDamage += AbstractDungeon.player.getPower(FocusPower.POWER_ID).amount * magicNumber;
        }
        super.applyPowers();
        this.baseDamage = originalBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void triggerOnGlowCheck(){
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasPower(FocusPower.POWER_ID)) {
            this.glowColor = Color.BLUE.cpy();
        }
    }


    @Override
    public void upp() {
        upgradeDamage(UPG_DMG);
        upgradeMagicNumber(UPG_MAGIC);
    }
}
