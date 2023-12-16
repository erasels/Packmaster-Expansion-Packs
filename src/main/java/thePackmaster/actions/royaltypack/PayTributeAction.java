package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.royaltypack.LoseGoldTextEffect;

public class PayTributeAction extends AbstractGameAction {

    private int amountToPay;

    public PayTributeAction(int amountToPay) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
        this.amountToPay = amountToPay;
    }

    @Override
    public void update() {
        int currentPlayerGold = AbstractDungeon.player.gold;
        if (currentPlayerGold >= amountToPay){
            AbstractDungeon.effectList.add(new LoseGoldTextEffect(-amountToPay));
            CardCrawlGame.sound.play("GOLD_GAIN", 0.3F);
            AbstractDungeon.player.loseGold(amountToPay);
        }
        else {
            int trueGoldAmountToLose = currentPlayerGold;
            AbstractDungeon.effectList.add(new LoseGoldTextEffect(-trueGoldAmountToLose));
            AbstractDungeon.player.loseGold(trueGoldAmountToLose);
            CardCrawlGame.sound.play("GOLD_GAIN", 0.3F);
            int HPToLose = amountToPay - trueGoldAmountToLose;
            if (HPToLose > 0){
                Wiz.atb(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, HPToLose));
            }
        }

        this.isDone = true;
    }
}
