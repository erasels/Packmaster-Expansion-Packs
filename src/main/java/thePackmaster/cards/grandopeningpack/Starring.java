package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Starring extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Starring");

    public Starring() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseBlock = block = 7;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.doBlk(block);
        for(int i = 0; i<magicNumber; i++) {
                addToBot(new MakeTempCardInHandAction(Wiz.getRandomItem(Wiz.getCardsMatchingPredicate(c -> c.isInnate && !c.hasTag(CardTags.HEALING), true)).makeCopy(), false));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}