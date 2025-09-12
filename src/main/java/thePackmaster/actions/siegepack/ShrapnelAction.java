package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.powers.shamanpack.IgnitePower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//REFS : LifeDrainAction (bardinspirepack), Venemous stance (serpentinepack).
public class ShrapnelAction extends AbstractGameAction {
    private final int DAMAGE_THRESHOLD;
    private final int DAMAGE;
    private final int POWER_STACKS;
    private final AbstractPower sourcePower;

    public ShrapnelAction(AbstractCreature target, AbstractCreature source, int amount, int threshold, DamageInfo.DamageType type, int stacks, AbstractPower sourcePower)
    {
        setValues(target, source, amount);  //Shorthand. Also sets duration = 0.5F
        duration = 0.1F;
        DAMAGE = amount;
        actionType = AbstractGameAction.ActionType.DAMAGE;
        damageType = type;
        POWER_STACKS = stacks;
        DAMAGE_THRESHOLD = threshold;
        this.sourcePower = sourcePower;
    }

    @Override
    public void update()
    {
        //In any Action, must set "isDone" to "true" or call tickDuration(), else Crash.
        isDone = true;
        if (damageType != DamageInfo.DamageType.NORMAL || DAMAGE < DAMAGE_THRESHOLD)
        { return; }

        sourcePower.flashWithoutSound();

        applyDebuffs(DAMAGE);
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.1f));
    }

    private void applyDebuffs (int damageAmount) {
        //Apply debuffs for each 10 unblocked damage dealt by the attack.
        int triggerCount = damageAmount / DAMAGE_THRESHOLD;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, player, new IgnitePower(target, 2 * POWER_STACKS * triggerCount), 2 * POWER_STACKS * triggerCount, true));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, player, new VulnerablePower(target, POWER_STACKS * triggerCount, false), POWER_STACKS * triggerCount, true));
    }
}


