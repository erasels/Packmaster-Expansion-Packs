package thePackmaster.actions.doppelpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;

import java.util.ArrayList;

public class GrandfatherParadoxAction extends AbstractGameAction {
    private final String text;
    private ArrayList<AbstractCard> affectedCards;

    public GrandfatherParadoxAction(int amount, String description) {
        this.amount = amount;
        this.text = description;
    }

    @Override
    public void update() {
        isDone = true;
        affectedCards = new ArrayList<>(AbstractDungeon.player.discardPile.group);
        for (AbstractCard card : affectedCards) {
            CardModifierManager.addModifier(card, new ShowDoppel());
        }

        addToBot(new SelectFromGridAction(
                (s, c) -> s == SelectFromGridAction.Source.DISCARD_PILE,
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
        int sumCost = 0;
        for (int i = abstractCards.length - 1; i >= 0; i--) {
            AbstractCard card = abstractCards[i];
            if (!card.freeToPlayOnce) {
                if (!card.freeToPlay() && card.costForTurn > 0) {
                    sumCost += card.costForTurn;
                }
                if (card.cost == -1) {
                    sumCost += EnergyPanel.totalCount;
                }
            }
            AbstractDungeon.player.discardPile.empower(card);
            SummonAction.doSummon(card, true);
        }
        if (sumCost > 0) {
            addToTop(new GainEnergyAction(sumCost));
        }
    }
}
