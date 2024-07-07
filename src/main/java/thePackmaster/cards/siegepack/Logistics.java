package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Logistics extends AbstractSiegeCard {
    public final static String ID = makeID("Logistics");
    private static final int COST = 1;
    private static final int WEAK_AMOUNT = 2;
    private static final int SHELL_GAIN = 1;
    private static final int CARD_DRAW = 1;
    private static final int UPGRADE_CARD_DRAW = 1;

    public Logistics() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = WEAK_AMOUNT;
        baseSecondMagic = secondMagic = CARD_DRAW;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Apply 2 Weak
        Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        //Gain 1 Shell.
        Wiz.applyToSelf(new ShellPower(p, SHELL_GAIN));
        //Draw 2 (3) cards.
        addToBot(new DrawCardAction(secondMagic));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(UPGRADE_CARD_DRAW);
    }
}
