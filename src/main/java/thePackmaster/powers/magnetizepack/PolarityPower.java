package thePackmaster.powers.magnetizepack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PolarityPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID(PolarityPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public static final int STACKS_PER_ENERGY = 10;

    public PolarityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.convertPolarityToEnergy();
    }

    @Override
    public void onInitialApplication() {
        this.convertPolarityToEnergy();
    }

    private void convertPolarityToEnergy() {
        if (amount >= STACKS_PER_ENERGY) {
            int energy = amount / STACKS_PER_ENERGY;
            addToTop(new GainEnergyAction(energy));
            amount -= (energy * STACKS_PER_ENERGY);
            if (amount <= 0) {
                addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], 10);
    }

    @Override
    public AbstractPower makeCopy() {
        return new PolarityPower(owner, amount);
    }
}




