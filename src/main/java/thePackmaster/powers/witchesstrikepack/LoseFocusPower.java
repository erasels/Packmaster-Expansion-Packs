package thePackmaster.powers.witchesstrikepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class LoseFocusPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(LoseFocusPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public LoseFocusPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.DEBUFF,false,owner,amount);
        canGoNegative = true;
        if (amount < 0){
            name = DESCRIPTIONS[2];
            type = PowerType.BUFF;
        }
    }
    public void atStartOfTurn() {
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, -this.amount), -this.amount));
        removeThis();
    }
    @Override
    public void updateDescription() {
        if (amount < 0){
            name = DESCRIPTIONS[2];
            description = DESCRIPTIONS[3] + (-amount) + DESCRIPTIONS[1];
        } else {
            name = NAME;
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }

    }
}
