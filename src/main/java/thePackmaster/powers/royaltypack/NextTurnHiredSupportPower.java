package thePackmaster.powers.royaltypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NextTurnHiredSupportPower  extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("NextTurnHiredSupportPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public NextTurnHiredSupportPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, 1);
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner,
                new HiredSupportPower(this.owner, 1)));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }


    @Override
    public void updateDescription() { description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]; }

}
