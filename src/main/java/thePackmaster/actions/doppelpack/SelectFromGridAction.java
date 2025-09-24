package thePackmaster.actions.doppelpack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectFromGridAction extends AbstractGameAction {

    private final BiPredicate<Source, AbstractCard> cardSelector;
    private final BiConsumer<Source[], AbstractCard[]> then;
    private final String uiString;
    private final int numCards;
    private final boolean anyNumber;
    private final BiConsumer<Source, AbstractCard> beforeShow;
    private final BiConsumer<Source, AbstractCard> afterShow;
    private boolean complete = false;

    private List<AbstractCard> selectedCards;
    private final List<AbstractCard> preselectedCards;

    public SelectFromGridAction(BiPredicate<Source, AbstractCard> cardSelector,
                                BiConsumer<Source[], AbstractCard[]> then,
                                String uiString,
                                ActionType actionType,
                                int numCards,
                                boolean anyNumber) {
        this(cardSelector, then, uiString, actionType, numCards, anyNumber, null, null, null);
    }

    public SelectFromGridAction(BiPredicate<Source, AbstractCard> cardSelector,
                                BiConsumer<Source[], AbstractCard[]> then,
                                String uiString,
                                ActionType actionType,
                                int numCards,
                                boolean anyNumber,
                                BiConsumer<Source, AbstractCard> beforeShow,
                                BiConsumer<Source, AbstractCard> afterShow,
                                List<AbstractCard> preselectedCards) {
        this.anyNumber = anyNumber;
        this.beforeShow = beforeShow;
        this.afterShow = afterShow;
        this.actionType = actionType;
        this.cardSelector = cardSelector;
        this.then = then;
        this.uiString = uiString;
        this.numCards = numCards;
        this.duration = Settings.ACTION_DUR_FAST;
        this.preselectedCards = preselectedCards;
    }

    @Override
    public void update() {
        AbstractPlayer player = AbstractDungeon.player;
        if (duration == Settings.ACTION_DUR_FAST) {
            if (selectedCards == null) {
                selectedCards = Stream.of(
                            filterCards(player.hand, Source.HAND),
                            filterCards(player.drawPile, Source.DRAW_PILE),
                            filterCards(player.discardPile, Source.DISCARD_PILE))
                    .flatMap(g -> g)
                    .collect(Collectors.toList());
            }

            if (selectedCards.isEmpty()) {
                then.accept(new Source[0], new AbstractCard[0]);
                isDone = true;
                return;
            }

            CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            boolean shouldSort = false;
            for (AbstractCard card : selectedCards) {
                Source cardSource = getSource(player, card);
                group.addToTop(card);
                if (beforeShow != null) {
                    beforeShow.accept(cardSource, card);
                }
                card.stopGlowing();
                if (cardSource == Source.DRAW_PILE) {
                    shouldSort = true;
                }
            }

            if (shouldSort) {
                group.sortAlphabetically(true);
                group.sortByRarityPlusStatusCardType(false);
            }

            int size = Math.min(numCards, selectedCards.size());
            if (!anyNumber) {
                AbstractDungeon.gridSelectScreen.open(group, size, uiString, false);
            } else {
                AbstractDungeon.gridSelectScreen.open(group, size, anyNumber, uiString);
            }

            if (preselectedCards != null) {
                group.group.sort((a, b) -> (preselectedCards.contains(a) ? 1 : 0) - (preselectedCards.contains(b) ? 1 : 0));
                for (AbstractCard card : preselectedCards) {
                    AbstractDungeon.gridSelectScreen.selectedCards.add(card);
                    card.beginGlowing();
                }
                ReflectionHacks.setPrivate(AbstractDungeon.gridSelectScreen, GridCardSelectScreen.class, "cardSelectAmount", preselectedCards.size());
            }

        } else if (!complete && AbstractDungeon.screen == AbstractDungeon.CurrentScreen.NONE) {
            List<AbstractCard> cards = new ArrayList<>(AbstractDungeon.gridSelectScreen.selectedCards);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            cards.forEach(AbstractCard::stopGlowing);
            
            then.accept(cards.stream().map(card -> getSource(player, card)).toArray(Source[]::new), cards.toArray(new AbstractCard[0]));
            complete = true;

            for (AbstractCard selectedCard : selectedCards) {
                if (afterShow != null) {
                    afterShow.accept(getSource(player, selectedCard), selectedCard);
                }
            }
        }

        tickDuration();
    }

    private Source getSource(AbstractPlayer player, AbstractCard card) {
        return player.hand.contains(card) ? Source.HAND :
                player.drawPile.contains(card) ? Source.DRAW_PILE :
                player.discardPile.contains(card) ? Source.DISCARD_PILE :
                Source.NONE;
    }

    private Stream<AbstractCard> filterCards(CardGroup cardGroup, Source source) {
        return cardGroup.group.stream().filter(c -> cardSelector.test(source, c));
    }

    public enum Source {
        NONE,
        DRAW_PILE,
        DISCARD_PILE,
        HAND,
    }
}
