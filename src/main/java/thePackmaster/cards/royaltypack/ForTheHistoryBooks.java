package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.ForTheHistoryBooksAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ForTheHistoryBooks extends AbstractRoyaltyCard {

    public final static String ID = makeID("ForTheHistoryBooks");

    public final static int AMOUNT_OF_TEMP_STR_AND_DEX = 3;
    public final static int INCREASE_ON_UPGRADE = 2;

    public ForTheHistoryBooks() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = AMOUNT_OF_TEMP_STR_AND_DEX;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(INCREASE_ON_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new ForTheHistoryBooksAction(abstractPlayer, this.freeToPlayOnce, magicNumber));
    }
}
