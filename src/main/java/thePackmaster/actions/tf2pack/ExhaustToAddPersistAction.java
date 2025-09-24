package thePackmaster.actions.tf2pack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cards.tf2pack.PersistHelper;
import thePackmaster.util.Wiz;

public class ExhaustToAddPersistAction extends AbstractGameAction {

    private final AbstractCard card;

    public ExhaustToAddPersistAction(AbstractCard card) {
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

        // if there are cards in hand that aren't this card, you choose one to exhaust and increment persist
        this.addToBot(new ExhaustAction(1, false, false, false));
        PersistHelper.IncrementPersist(card);
        this.isDone = true;
    }
}
