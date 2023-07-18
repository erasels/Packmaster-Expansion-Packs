package thePackmaster.powers.maridebuffpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.maridebuffpack.OnDebuffLossPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CharacterDevelopmentPower extends AbstractPackmasterPower implements OnDebuffLossPower {
    public static final String POWER_ID = makeID("CharacterDevelopmentPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public boolean upgraded;

    public CharacterDevelopmentPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,amount);
    }

    @Override
    public void onDebuffLoss() {
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
