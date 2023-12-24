package thePackmaster.powers.stancedancepack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.stances.aggressionpack.AggressionStance;
import thePackmaster.util.Wiz;

public class NextTurnAggression extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("NextTurnAggression");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NextTurnAggression(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        this.flashWithoutSound();
        Wiz.atb(new ChangeStanceAction(new AggressionStance()));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
