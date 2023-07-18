package thePackmaster.powers.maridebuffpack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.maridebuffpack.RecurringTheme;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.maridebuffpack.OnDebuffLossPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RecurringThemePower extends AbstractPackmasterPower implements OnDebuffLossPower, NonStackablePower {
    public static final String POWER_ID = makeID("RecurringThemePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public boolean upgraded;

    public RecurringThemePower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,amount);
        this.upgraded = upgraded;
        updateDescription();
    }

    @Override
    public void onDebuffLoss() {
        if(this.amount > 0){
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new RecurringTheme(this.upgraded), this.amount));
            this.amount = 0;
        }
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public void updateDescription() {
        if(this.upgraded){
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }else{
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }

    @Override
    public boolean isStackable(AbstractPower power) {
        if(power.ID.equals(this.ID)){
            return ((RecurringThemePower) power).upgraded == this.upgraded;
        }
        return false;
    }
}
