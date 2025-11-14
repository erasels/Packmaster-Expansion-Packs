package thePackmaster.actions.tf2pack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.util.Wiz;

public class ExhaustToEnergyAndCreateCardAction extends AbstractGameAction {

    private final AbstractCard card;

    public ExhaustToEnergyAndCreateCardAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        int cardsInHand = Wiz.hand().size();
        if (cardsInHand <= 0
                || (cardsInHand == 1 && Wiz.hand().group.get(0) == card)) {
            this.isDone = true;
            return;
        }

        // if there are cards in hand that aren't this card, you choose one to exhaust and do the thing
        this.addToBot(new ExhaustAction(1, false, false, false));
        this.addToBot(new GainEnergyAction(1));
        this.addToBot(new MakeTempCardInHandAction(card, 1));
        this.isDone = true;
    }
}
