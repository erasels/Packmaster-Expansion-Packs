package thePackmaster.actions.maridebuffpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class TheMANSIONAction extends AbstractGameAction {

    public TheMANSIONAction() {
        startDuration = duration = Settings.ACTION_DUR_FAST;
    }

    // (set Vuln and Frail to 1)
    //A bit complex for its function, but is hopefully more compatible for any edge cases
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            AbstractPlayer p = AbstractDungeon.player;
            AbstractPower playerVuln = p.getPower(VulnerablePower.POWER_ID);
            if(playerVuln == null){
                addToTop(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false)));
            }else if(playerVuln.amount <= 0) { //this too
                int increaseAmount = 1 - playerVuln.amount;
                addToTop(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), increaseAmount));
            }else if(playerVuln.amount >= 2){
                int decreaseAmount = playerVuln.amount - 1;
                addToTop(new ReducePowerAction(p, p, playerVuln, decreaseAmount));
            }

            AbstractPower playerFrail = p.getPower(FrailPower.POWER_ID);
            if(playerFrail == null){
                addToTop(new ApplyPowerAction(p, p, new FrailPower(p, 1, false)));
            }else if(playerFrail.amount <= 0) { // really don't know when this will be non-positive but who knows if some madperson's gonna patch frail to work like that
                int increaseAmount = 1 - playerFrail.amount;
                addToTop(new ApplyPowerAction(p, p, new FrailPower(p, 1, false), increaseAmount));
            }else if(playerFrail.amount >= 2){
                int decreaseAmount = playerFrail.amount - 1;
                addToTop(new ReducePowerAction(p, p, playerFrail, decreaseAmount));
            }
        }
        this.tickDuration();
    }
}
