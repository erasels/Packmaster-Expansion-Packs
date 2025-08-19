package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.NeedsGoldToBePartiallyDoneAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TacticalReroll extends AbstractRoyaltyCard {

    public final static String ID = makeID("TacticalReroll");
    public final static int BASE_AMOUNT_OF_RETRIEVES = 2;
    public final static int GOLD_COST = 10;

    public TacticalReroll() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = BASE_AMOUNT_OF_RETRIEVES;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractGameAction[] actionArrayAfterPay = new AbstractGameAction[0];

        Wiz.atb(new NeedsGoldToBePartiallyDoneAction(GOLD_COST,
                new BetterDiscardPileToHandAction(magicNumber),
                actionArrayAfterPay,
                cardStrings.EXTENDED_DESCRIPTION[0]));
    }
}
