package thePackmaster.actions.witchesstrikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.util.Wiz;

import java.util.Collections;

public class MysticFlourishAction extends AbstractGameAction
{
    private AbstractPlayer p;
    public MysticFlourishAction(int count)
    {
        amount = count;
        actionType = ActionType.SPECIAL;
        duration = Settings.ACTION_DUR_FAST;
        p = AbstractDungeon.player;
    }

    public void update()
    {
        if (!AbstractDungeon.player.orbs.isEmpty()) {
            for (AbstractOrb o : Wiz.p().orbs){
                if (o instanceof PackmasterOrb) {
                    for (int i = 0; i < this.amount; i++) {
                        ((PackmasterOrb) o).passiveEffect();
                    }
                    isDone = true;
                } else {
                    for (int i = 0; i < this.amount; i++) {
                        o.onStartOfTurn();
                        o.onEndOfTurn();
                    }
                    isDone = true;
                }
            }
        } else {
            isDone = true;
        }
    }
}
