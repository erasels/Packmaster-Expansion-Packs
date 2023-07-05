package thePackmaster.actions.maridebuffpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GiveBlockAction extends AbstractGameAction {
    public int originalBlock;
    public int maxBlockGiven;

    // removes all block gained over the player's starting block from the player, but not more than the intended block applied
    public GiveBlockAction(int originalBlock, int maxBlockGiven, AbstractCreature target) {
        this.originalBlock = originalBlock;
        this.maxBlockGiven = maxBlockGiven;
        this.target = target;
        startDuration = duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if(target == AbstractDungeon.player){
                this.isDone = true;
            }
            int currentBlock = AbstractDungeon.player.currentBlock;
            int giveAmount = Math.min(Math.max(0, currentBlock - this.originalBlock), this.maxBlockGiven);
            if(giveAmount > 0){
                AbstractDungeon.player.loseBlock(giveAmount, false);
                target.addBlock(giveAmount);
            }

            // refresh is also performed by base game block action
            for(AbstractCard c: AbstractDungeon.player.hand.group){
                c.applyPowers();
            }
        }
        this.tickDuration();
    }
}
