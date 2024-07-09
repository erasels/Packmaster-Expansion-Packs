package thePackmaster.powers.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.siegepack.PrevailAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.atb;

public class PrevailPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(PrevailPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final int DAMAGE_THRESHOLD = 9;

    public PrevailPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF,false, owner, amount);
        //this.amount = amount;   //If bug "amounts are doubled", remove this.
        updateDescription();
    }

    //Start the action to apply debuffs for each (full) 10 damage dealt by the attack.
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        //As an action for possible timing reasons.
        atb(new PrevailAction(target, this.owner, damageAmount, info.type, this.amount, DAMAGE_THRESHOLD, AbstractGameAction.AttackEffect.NONE));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DAMAGE_THRESHOLD + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
