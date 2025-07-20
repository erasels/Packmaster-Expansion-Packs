package thePackmaster.actions.turmoilpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class AbandonAction extends AbstractGameAction {
    private final Function<AbstractCard, Boolean> condition;
    private final Consumer<List<AbstractCard>> followup;

    public AbandonAction(Function<AbstractCard, Boolean> condition, Consumer<List<AbstractCard>> followup) {
        this.condition = condition;
        this.followup = followup;
    }

    @Override
    public void update() {
        this.isDone = true;

        List<AbstractCard> discardedCards = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (this.condition.apply(c)) {
                discardedCards.add(c);
            }
        }

        this.addToTop(new AbstractGameAction() {
            @Override
            public void update() {
                followup.accept(discardedCards);
                this.isDone = true;
            }
        });
        int n = discardedCards.size();
        for (int i = 0; i < n; i++) {
            this.addToTop(new DiscardSpecificCardAction(discardedCards.get(n - i - 1)));
        }
    }
}
