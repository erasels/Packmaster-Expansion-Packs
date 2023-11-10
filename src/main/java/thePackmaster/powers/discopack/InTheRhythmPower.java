package thePackmaster.powers.discopack;


import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class InTheRhythmPower extends AbstractPackmasterPower implements DiscardHookPatch.OnDiscardThing {
    public static final String POWER_ID = makeID("InTheRhythmPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public InTheRhythmPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.isTwoAmount = true;
        this.amount2 = 4;
        this.updateDescription();
    }

    public void onManualDiscardThing() {
        amount2 -= 1;
        if (amount2 <= 0){
            this.flash();
            CardCrawlGame.sound.play("TINGSHA");
            applyToSelf(new StrengthPower(owner, amount));
            atb(new AddTemporaryHPAction(owner, owner, amount * 4));
            amount2 = 4;
        }
        updateDescription();
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + 4 * this.amount + DESCRIPTIONS[3] + amount2 + DESCRIPTIONS[4];
    }


}

