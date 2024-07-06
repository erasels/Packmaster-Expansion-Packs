package thePackmaster.powers.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.siegepack.ShrapnelAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.atb;

public class ShrapnelPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(ShrapnelPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ShrapnelPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    //Start the action to apply debuffs for each 10 damage (full & unblocked) dealt by the attack.
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        //Adds "unblocked" detection. REF : LifeDrain & LifeDrainAction (bardinspirepack).
        atb(new ShrapnelAction(target, this.owner, damageAmount, info.type, this.amount, AbstractGameAction.AttackEffect.NONE));
    }
}
