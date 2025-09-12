package thePackmaster.powers.tf2pack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ResupplyRefundPower extends AbstractPackmasterPower implements ResupplyPowerInterface {
    public static final String POWER_ID = makeID(ResupplyRefundPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public int triggersThisTurn = 0;

    public ResupplyRefundPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }


    public void updateDescription() {
        if (amount == 1)
            this.description = DESCRIPTIONS[0];
        else
            this.description = String.format(DESCRIPTIONS[1], amount);
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        triggersThisTurn = 0;
    }

    @Override
    public void triggerOnAnyResupply(AbstractCard card) {
        if (triggersThisTurn < this.amount) {
            int realCost = card.cost;
            if (realCost == -2)
                realCost = 0;
            if (realCost == -1)
                realCost = EnergyPanel.totalCount;

            // Don't use up a trigger on statuses or zero cost cards. especially since this might trigger when
            // adding things like shivs to hand
            if (realCost == 0)
                return;

            this.flash();
            triggersThisTurn++;
            this.addToBot(new GainEnergyAction(realCost));
        }
    }
}
