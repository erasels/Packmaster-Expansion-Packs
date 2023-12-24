package thePackmaster.powers.stancedancepack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.stances.AbstractStance;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.Objects;

public class GainEnergyOnStanceEnter extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("GainEnergyOnStanceEnter");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GainEnergyOnStanceEnter(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        if (!Objects.equals(oldStance.ID, newStance.ID)){
            Wiz.atb(new GainEnergyAction(1));
            removeThis();
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + " [E] " + LocalizedStrings.PERIOD;
    }

}
