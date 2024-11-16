package thePackmaster.actions.doppelpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;

import java.util.ArrayList;

public class EmergencyContactAction extends AbstractGameAction {
    private final String text;
    private ArrayList<AbstractCard> affectedCards;

    public EmergencyContactAction(int amount, String description) {
        this.amount = amount;
        this.text = description;
    }

    @Override
    public void update() {
        isDone = true;
        affectedCards = new ArrayList<>(AbstractDungeon.player.drawPile.group);
        for (AbstractCard card : affectedCards) {
            CardModifierManager.addModifier(card, new ShowDoppel());
        }

        addToBot(new SelectFromGridAction(
                (s, c) -> s == SelectFromGridAction.Source.DRAW_PILE,
                this::afterSelected,
                text,
                ActionType.CARD_MANIPULATION,
                amount,
                false
        ));
    }

    private void afterSelected(SelectFromGridAction.Source[] sources, AbstractCard[] abstractCards) {
        if (affectedCards != null) {
            for (AbstractCard card : affectedCards) {
                CardModifierManager.removeModifiersById(card, ShowDoppel.ID, false);
            }
        }
        for (int i = abstractCards.length - 1; i >= 0; i--) {
            AbstractCard card = abstractCards[i];
            AbstractDungeon.player.drawPile.empower(card);
            SummonAction.doSummon(card, true);
        }
    }
}
