package thePackmaster.actions.discopack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.util.Wiz.att;
import static thePackmaster.util.Wiz.p;

public class AnyNumberDiscardAction extends AbstractGameAction {

    private final List<AbstractCard> selectedCards = new ArrayList<>();

    public AnyNumberDiscardAction(List<AbstractCard> selectedCards) {
        this.amount = amount;
        this.selectedCards.addAll(selectedCards);
    }
    //use with "atb(new SelectCardsInHandAction(x, text, true, true, card -> y,(list) -> atb(new AnyNumberDiscardAction(list))));"
    //where x is number of cards selectable and y is any boolean condition the cards must fulfill ie card.isEthereal
    @Override
    public void update() {
        for (AbstractCard c : selectedCards) {
            DiscardCard(c);
        }
        isDone = true;
    }
    public void DiscardCard(AbstractCard c){
        //SpireAnniversary5Mod.logger.info("Starting discard with card:  " + c);
        p().hand.moveToDiscardPile(c);
        c.triggerOnManualDiscard();
        GameActionManager.incrementDiscard(false);
    }
}