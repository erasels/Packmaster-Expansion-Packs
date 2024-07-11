package thePackmaster.powers.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.siegepack.ShrapnelAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.util.Wiz.atb;

public class ShrapnelPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(ShrapnelPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int DAMAGE_THRESHOLD;

    public ShrapnelPower(AbstractCreature owner, int amount, int threshold) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
        this.amount = amount;
        DAMAGE_THRESHOLD = threshold;
        updateDescription();
    }

    //Start the action to apply debuffs for each 10 damage (full & unblocked) dealt by the attack.
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        //Adds "unblocked" detection. REF : LifeDrain & LifeDrainAction (bardinspirepack).
        //if (target == player || damageAmount < DAMAGE_THRESHOLD) {    //Could be bad with Rend etc.
        if (target == player || info.type != DamageInfo.DamageType.NORMAL) {
            return;
        }
        atb(new ShrapnelAction(target, this.owner, damageAmount, DAMAGE_THRESHOLD, info.type, this.amount, AbstractGameAction.AttackEffect.NONE));
        this.flashWithoutSound();
    }

    public void updateDescription() {
        int tmp = amount * 2;
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + tmp + DESCRIPTIONS[2];
    }
}
