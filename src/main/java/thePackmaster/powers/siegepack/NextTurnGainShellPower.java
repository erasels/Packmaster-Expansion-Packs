package thePackmaster.powers.siegepack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

//REFS: NextTurnGainSlimeCrushPower (downfallpack), SchemeNextTurnPower (dimensiongatepack)
public class NextTurnGainShellPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(NextTurnGainShellPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NextTurnGainShellPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        Wiz.applyToSelf(new ShellPower(owner, amount));
        removeThisInvisibly();
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}
