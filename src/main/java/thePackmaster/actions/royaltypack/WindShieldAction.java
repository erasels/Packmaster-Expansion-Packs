package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class WindShieldAction extends AbstractGameAction {

    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(
            SpireAnniversary5Mod.makeID("WindShieldAction")).TEXT;

    private int blockPerDiscard;

    public WindShieldAction(int blockPerDiscard) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.blockPerDiscard = blockPerDiscard;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hand.isEmpty()) {
                this.isDone = true;
            } else {
                this.tickDuration();
                AbstractDungeon.handCardSelectScreen.open(TEXT[0],
                        AbstractDungeon.player.hand.size(), true, true);
            }
        } else {
            doAction();
        }
    }

    private void doAction(){
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (AbstractDungeon.handCardSelectScreen.selectedCards.size() != 0){
                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                    Wiz.atb(new GainBlockAction(AbstractDungeon.player, blockPerDiscard));
                }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = false;
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
    }
}
