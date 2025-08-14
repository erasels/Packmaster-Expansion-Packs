package thePackmaster.powers.siegepack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class ShellForgeEffectUpPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(ShellForgeEffectUpPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static int shellEffectBoost;
    public static int DAMAGE_BOOST_INCREASE_PER_TURN = 2;

    public ShellForgeEffectUpPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        shellEffectBoost = this.amount = amount;
        amount2 = DAMAGE_BOOST_INCREASE_PER_TURN;
        updateDescription();
    }

    public void atStartOfTurn(){
        increaseDamageBoost();
        this.flash();
    }

    private void increaseDamageBoost() {
        amount += amount2;
        updateDescription();
        //Shell buff auto-updates its damage boost.
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]
            + this.amount + DESCRIPTIONS[1]
            + this.amount2 + DESCRIPTIONS[2];
    }
}
