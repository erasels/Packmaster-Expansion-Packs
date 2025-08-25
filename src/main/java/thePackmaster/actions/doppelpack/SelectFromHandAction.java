package thePackmaster.actions.doppelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SelectFromHandAction extends AbstractGameAction {

    private final Predicate<AbstractCard> cardSelector;
    private final Consumer<List<AbstractCard>> then;
    private final String uiString;
    private final boolean skippable;

    private final ArrayList<AbstractCard> cannotBeSelected = new ArrayList<>();
    private final boolean anyNumber;
    private final boolean selectOrderMatters;

    public SelectFromHandAction(Predicate<AbstractCard> cardSelector,
                                Consumer<List<AbstractCard>> then,
                                String uiString,
                                int amount,
                                boolean anyNumber,
                                boolean skippable,
                                ActionType actionType,
                                boolean selectOrderMatters) {
        this.actionType = actionType;
        this.cardSelector = cardSelector;
        this.then = then;
        this.uiString = uiString;
        this.amount = amount;
        this.anyNumber = anyNumber;
        this.skippable = skippable;
        this.duration = Settings.ACTION_DUR_FAST;
        this.selectOrderMatters = selectOrderMatters;
    }

    @Override
    public void update() {
        AbstractPlayer player = AbstractDungeon.player;
        if (duration == Settings.ACTION_DUR_FAST) {
            cannotBeSelected.clear();
            cannotBeSelected.addAll(player.hand.group
                    .stream()
                    .filter(c -> !cardSelector.test(c))
                    .collect(Collectors.toList()));

            if (cannotBeSelected.size() == player.hand.group.size()) {
                then.accept(Collections.emptyList());
                isDone = true;
                return;
            }

            if (!skippable && !anyNumber && player.hand.group.size() - cannotBeSelected.size() <= this.amount &&
                    (!selectOrderMatters || player.hand.group.size() - cannotBeSelected.size() == 1)) {
                then.accept(player.hand.group.stream().filter(cardSelector).collect(Collectors.toList()));
                isDone = true;
                return;
            }

            player.hand.group.removeAll(cannotBeSelected);
            if (!player.hand.group.isEmpty()) {
                if (amount > player.hand.group.size()) {
                    amount = player.hand.group.size();
                }
                AbstractDungeon.handCardSelectScreen.open(uiString, amount, anyNumber, skippable, false, false);
                tickDuration();
            } else {
                then.accept(Collections.emptyList());
                returnCards(player);
                isDone = true;
            }
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard card : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                player.hand.addToTop(card);
            }

            then.accept(AbstractDungeon.handCardSelectScreen.selectedCards.group);

            returnCards(player);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }

        tickDuration();
    }

    private void returnCards(AbstractPlayer player) {
        for (AbstractCard card : cannotBeSelected) {
            player.hand.addToTop(card);
        }

        player.hand.refreshHandLayout();
    }
}
