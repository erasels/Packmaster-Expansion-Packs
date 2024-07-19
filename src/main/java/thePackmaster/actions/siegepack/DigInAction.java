package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DigInAction extends AbstractGameAction {
    private final int ENERGY_GAIN;
    AbstractPower flashingBuff;

    public DigInAction(int gain, AbstractPower source) {
        ENERGY_GAIN = gain;
        flashingBuff = source;
    }

    @Override
    public void update() {
        //In any Action, must set "isDone" to "true" or call tickDuration(), else Crash.
        isDone = true;
        //Block relics have triggered by now.
        if (player.currentBlock > 0) {
            Wiz.atb(new GainEnergyAction(ENERGY_GAIN));
            if (flashingBuff != null) {
                flashingBuff.flash();
            }
        }
    }
}
