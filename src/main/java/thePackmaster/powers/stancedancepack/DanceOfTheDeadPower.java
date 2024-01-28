package thePackmaster.powers.stancedancepack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.Objects;

public class DanceOfTheDeadPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("DanceOfTheDeadPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DanceOfTheDeadPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF,false, owner, amount);
    }

    @Override
    public void onSpecificTrigger() {
        Wiz.atb(new LoseHPAction(this.owner, AbstractDungeon.player, amount));
        flash();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
