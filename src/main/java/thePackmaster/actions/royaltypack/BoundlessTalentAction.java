package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;

public class BoundlessTalentAction extends AbstractGameAction {

    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(
            SpireAnniversary5Mod.makeID("BoundlessTalentAction")).TEXT;

    public BoundlessTalentAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.isEmpty()) {
                this.isDone = true;
            } else if (AbstractDungeon.player.hand.size() == 1) {
                doActionWithOneCardAtHand();
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false);
                this.tickDuration();
            }
        } else {
            doAction();
        }
    }

    private void doAction(){
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (AbstractDungeon.handCardSelectScreen.selectedCards.size() != 0){
                AbstractCard tmpCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard().
                        makeStatEquivalentCopy();
                AbstractDungeon.player.hand.addToHand(AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard());
                AbstractDungeon.player.hand.addToHand(tmpCard);
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = false;
                AbstractDungeon.handCardSelectScreen.selectedCards.clear();
                this.isDone = true;
            }
        }
    }

    private void doActionWithOneCardAtHand(){
        AbstractCard tmpCard = AbstractDungeon.player.hand.getBottomCard().makeStatEquivalentCopy();
        AbstractDungeon.player.hand.addToHand(AbstractDungeon.player.hand.getBottomCard());
        AbstractDungeon.player.hand.addToHand(tmpCard);
        AbstractDungeon.handCardSelectScreen.selectedCards.clear();
        AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        this.isDone = true;
    }
}
