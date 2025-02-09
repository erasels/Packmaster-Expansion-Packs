package thePackmaster.actions.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.bladestormpack.WindrushPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.util.Wiz.*;

//REFS: ConsumeToDoAction (fueledpack), DifferentStrikes (strikespack), FalseGritAction (cosmoscommandpack)
public class FeedingWindsAction extends AbstractGameAction {
    //Based on ConsumeToDoAction (fueledpack).
    private static final String UI_KEY = SpireAnniversary5Mod.makeID("FeedWindsUiText");
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final AbstractGameAction action;
    private final int cardsToExhaust;
    private final int windrush;

    public FeedingWindsAction(int cardsToExhaust, int windrush, AbstractGameAction action) {
        this.action = action;
        duration = startDuration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.DAMAGE;
        this.cardsToExhaust = cardsToExhaust;
        this.windrush = windrush;
    }

    public void update() {
        if (duration == startDuration) {

            if (adp().hand.isEmpty()) {
                isDone = true;
                finish(null);
                return;
            }

            if (adp().hand.size() == 1) {
                AbstractCard c = adp().hand.getTopCard();
                adp().releaseCard();
                adp().hand.stopGlowing();
                finish(c);
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(TEXT[0], cardsToExhaust, false, false);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                AbstractCard c = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);
                finish(c);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }

    private void finish(AbstractCard card) {
        adp().hand.refreshHandLayout();
        if (card != null) {
            att(action);
            atb(new ApplyPowerAction(player, player, new WindrushPower(player, windrush)));
            player.hand.moveToExhaustPile(card);
        }
        isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(UI_KEY);
        TEXT = uiStrings.TEXT;
    }
}