package thePackmaster.powers.showmanpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.green.AfterImage;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class HallOfMirrorsPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("HallOfMirrorsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int TRIGGER_TIME = 2;

    public HallOfMirrorsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.amount2 = TRIGGER_TIME;
        isTwoAmount = true;
    }

    @Override
    public void onInitialApplication() {
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (this.amount2 <= 1) {
            addToBot(new MakeTempCardInHandAction(new AfterImage(), amount));
            this.amount2 = TRIGGER_TIME;
            flash();
        } else {
            reducePower2(1);
            updateDescription();
        }
    }

    public void reducePower2(int reduceAmount) {
        if (this.amount2 - reduceAmount <= 0) {
            this.fontScale = 8.0F;
            this.amount2 = 0;
        } else {
            this.fontScale = 8.0F;
            this.amount2 -= reduceAmount;
        }

    }
}
