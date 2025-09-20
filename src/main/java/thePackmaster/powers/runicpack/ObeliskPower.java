package thePackmaster.powers.runicpack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.stances.AbstractStance;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

public class ObeliskPower extends AbstractPackmasterPower {

    public static final String POWER_ID = SpireAnniversary5Mod.makeID("ObeliskPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ObeliskPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        if (!oldStance.ID.equals(newStance.ID)){
            for (int i = 0; i<this.amount; i++) {
                addToBot(new ChannelAction(getRandomOrb()));
            }
        }
    }

    private AbstractOrb getRandomOrb() {
        return PackmasterOrb.getPackLimitedOrb(true);
    }

}
