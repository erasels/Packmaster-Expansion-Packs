package thePackmaster.actions.sneckopack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class RandomizeCostAction extends AbstractGameAction {

    private AbstractCard[] cards;

    public RandomizeCostAction(AbstractCard ... cards) {
        this.cards = cards;
    }

    public RandomizeCostAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        if (cards == null) {
            ArrayList<AbstractCard> applicable = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.cost >= 0) {
                    applicable.add(c);
                }
            }
            int randomisations = Math.min(applicable.size(),amount);
            cards = new AbstractCard[randomisations];
            for (int i = 0 ; i < randomisations ; i++) {
                int r = AbstractDungeon.cardRandomRng.random(applicable.size() - 1);
                cards[i] = applicable.get(r);
                applicable.remove(r);
            }
        }

        for (AbstractCard card : cards) {
            if (card.cost >= 0) {// 24
                int newCost = AbstractDungeon.cardRandomRng.random(1, 3);
                newCost += card.costForTurn;
                newCost %= 4; //This makes cards that cost more than 3 a number below it. 4 costs can't roll 0, 5 Costs can't roll 1 etc
                card.setCostForTurn(newCost);
                card.superFlash(Color.LIME.cpy());
            }
        }
        isDone = true;
    }
}
