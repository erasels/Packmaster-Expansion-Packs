package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.royaltypack.LoseGoldTextEffect;

public class NeedsGoldToBePartiallyDoneAction extends AbstractGameAction {

    private final int amountOfGoldToPay;
    private final AbstractGameAction actionToUseIfPaid;
    private final AbstractGameAction[] actionsToUseAfterPaidOne;

    private final String talkMessage;

    public NeedsGoldToBePartiallyDoneAction(int amountOfGoldToPay, AbstractGameAction actionToUseIfPaid,
                                            AbstractGameAction[] actionsToUseAfterPaidOne,
                                            String talkMessage) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
        this.amountOfGoldToPay = amountOfGoldToPay;
        this.actionToUseIfPaid = actionToUseIfPaid;
        this.actionsToUseAfterPaidOne = actionsToUseAfterPaidOne;
        this.talkMessage = talkMessage;
    }

    @Override
    public void update() {
        int currentPlayerGold = AbstractDungeon.player.gold;

        if (currentPlayerGold >= amountOfGoldToPay) {
            AbstractDungeon.effectList.add(new LoseGoldTextEffect(-amountOfGoldToPay));
            CardCrawlGame.sound.play("GOLD_GAIN", 0.3F);
            AbstractDungeon.player.loseGold(amountOfGoldToPay);
            Wiz.att(actionToUseIfPaid);

            for (int i = actionsToUseAfterPaidOne.length - 1; i >= 0; i--) {
                Wiz.att(actionsToUseAfterPaidOne[i]);
            }
        } else {
            for (int i = actionsToUseAfterPaidOne.length - 1; i >= 0; i--) {
                Wiz.att(actionsToUseAfterPaidOne[i]);
            }

            AbstractDungeon.actionManager.addToBottom(
                    new TalkAction(
                            true,
                            talkMessage,
                            1.0F, 2.0F));
        }

        this.isDone = true;
    }
}
