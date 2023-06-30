package thePackmaster.actions.magnetizepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.cards.magnetizepack.Ferroshield;
import thePackmaster.cards.magnetizepack.InduceCurrent;
import thePackmaster.powers.magnetizepack.InduceCurrentPower;
import thePackmaster.util.Wiz;

public class MagnetizeAction extends AbstractGameAction {
    private final AbstractCard card;

    public MagnetizeAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        AbstractCardModifier newMagnetize = new MagnetizedModifier(false);
        if (newMagnetize.shouldApply(card)) {
            card.superFlash();

            for (AbstractPower pow : Wiz.adp().powers)
                if (pow instanceof InduceCurrentPower)
                    ((InduceCurrentPower) pow).onMagnetize(card);

            if (card instanceof Ferroshield)
                ((Ferroshield) card).onMagnetized();
            
            CardModifierManager.addModifier(card, newMagnetize);
        }

        isDone = true;
    }
}
