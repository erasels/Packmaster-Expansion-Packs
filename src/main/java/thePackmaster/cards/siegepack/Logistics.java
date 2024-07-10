package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

public class Logistics extends AbstractSiegeCard {
    public final static String ID = makeID("Logistics");
    private static final int COST = 1;
    private static final int WEAK_AMOUNT = 2;
    //private static final int UPGRADE_WEAK_AMOUNT = 1;
    private static final int SHELL_GAIN = 1;
    private static final int CARD_DRAW = 2;
    private static final int UPGRADE_CARD_DRAW = 1;

    public Logistics() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = WEAK_AMOUNT;
        baseSecondMagic = secondMagic = CARD_DRAW;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        Wiz.applyToSelf(new ShellPower(p, SHELL_GAIN));
        addToBot(new DrawCardAction(secondMagic));
    }

    @Override
    public void upp() {
        //upgradeMagicNumber(UPGRADE_WEAK_AMOUNT);
        upgradeSecondMagic(UPGRADE_CARD_DRAW);
    }


}
