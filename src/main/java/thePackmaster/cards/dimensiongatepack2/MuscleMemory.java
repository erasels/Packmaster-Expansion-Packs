package thePackmaster.cards.dimensiongatepack2;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGrift;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MuscleMemory extends AbstractDimensionalCardGrift {
    public final static String ID = makeID("MuscleMemory");

    public MuscleMemory() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new SelectCardsAction(p.drawPile.group, 1, cardStrings.EXTENDED_DESCRIPTION[0],
                (cards) -> {
                    for (AbstractCard c2 : cards) {
                        Wiz.atb(new MakeTempCardInHandAction(c2));
                    }
                }
        ));
    }

    public void upp() {
        ExhaustiveVariable.setBaseValue(this, 2);
        exhaust = false;
    }
}