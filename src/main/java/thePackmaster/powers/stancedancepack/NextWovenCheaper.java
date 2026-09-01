package thePackmaster.powers.stancedancepack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.powers.AbstractPackmasterPower;

public class NextWovenCheaper extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("NextWovenCheaper");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NextWovenCheaper(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void onSpecificTrigger() {
        reducePower(1);
        if (amount <= 0) {
            removeThis();
        }
    }

    @Override
    public void updateDescription() {

        this.description = DESCRIPTIONS[0] + amount;
        if (amount > 1){
            this.description = this.description + DESCRIPTIONS[1];
        } else {
            this.description = this.description + DESCRIPTIONS[2];
        }
        this.description = this.description + DESCRIPTIONS[3];
       }

}
