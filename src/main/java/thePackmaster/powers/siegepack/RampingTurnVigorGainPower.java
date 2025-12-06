package thePackmaster.powers.siegepack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//REFS: EventHorizon (marisapack), ControlledBurnPower (fueledpack), PolarityPower (magnetizepack).
public class RampingTurnVigorGainPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(RampingTurnVigorGainPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int stackAmountRampingPerTurn;

    public RampingTurnVigorGainPower(AbstractCreature owner, int amount, int amountRampingPerTurn) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.amount = amount;
        this.amount2 = amountRampingPerTurn;
        this.stackAmountRampingPerTurn = amountRampingPerTurn;

        this.priority = 8000; // EventHorizonPower priority should be 5 by default.
        // This should trigger after it. Could be useful.

        updateDescription();
    }

    public void atStartOfTurn(){
        Wiz.applyToSelf(new VigorPower(player, amount));
        this.flash();

        amount += amount2;
        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        amount2 += stackAmountRampingPerTurn;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]
            + this.amount + DESCRIPTIONS[1]
            + this.amount2 + DESCRIPTIONS[2];
    }
}
