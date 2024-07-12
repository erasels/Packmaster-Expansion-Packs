package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

public class Logistics extends AbstractSiegeCard {
    public final static String ID = makeID("Logistics");
    private static final int COST = 1;

    //private static final int WEAK_AMOUNT = 2;
    //private static final int UPGRADE_WEAK_AMOUNT = 1;
    private static final int DEBUFF_REMOVAL_PLUS_DRAW = 1;
    private static final int UPGRADE_DEBUFF_REMOVAL_PLUS_DRAW = 1;

    private static final int SHELL_GAIN = 1;

    private static final int CARD_DRAW = 1;
    //private static final int UPGRADE_CARD_DRAW = 1;

    public Logistics() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = DEBUFF_REMOVAL_PLUS_DRAW;
        baseSecondMagic = secondMagic = magicNumber + CARD_DRAW;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        ReduceCommonDebuffs(p);

        Wiz.applyToSelf(new ShellPower(p, SHELL_GAIN));
        addToBot(new DrawCardAction(secondMagic));
    }

    private void ReduceCommonDebuffs(AbstractPlayer p) {
        atb(new ReducePowerAction(p, p, WeakPower.POWER_ID, this.magicNumber));
        atb(new ReducePowerAction(p, p, FrailPower.POWER_ID, this.magicNumber));
        atb(new ReducePowerAction(p, p, VulnerablePower.POWER_ID, this.magicNumber));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (player.hasPower(WeakPower.POWER_ID) || player.hasPower(FrailPower.POWER_ID) || player.hasPower(VulnerablePower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_DEBUFF_REMOVAL_PLUS_DRAW);
        upgradeSecondMagic(UPGRADE_DEBUFF_REMOVAL_PLUS_DRAW);
    }
}
