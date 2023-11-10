package thePackmaster.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import basemod.abstracts.DynamicVariable;
import thePackmaster.cards.discopack.AbstractSmoothCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SecondBlock extends DynamicVariable{
    @Override
    public String key() {
        return makeID("sb");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractSmoothCard) {
            return ((AbstractSmoothCard) card).isSecondBlockModified;
        }
        return false;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractSmoothCard) {
            ((AbstractSmoothCard) card).isSecondBlockModified = v;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractSmoothCard) {
            return ((AbstractSmoothCard) card).secondBlock;
        }
        return -1;
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractSmoothCard) {
            return ((AbstractSmoothCard) card).baseSecondBlock;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractSmoothCard) {
            return ((AbstractSmoothCard) card).upgradedSecondBlock;
        }
        return false;
    }
}
