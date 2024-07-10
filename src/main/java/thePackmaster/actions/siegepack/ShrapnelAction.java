package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.powers.shamanpack.IgnitePower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

/*REFS :
LifeGainAction (in base PackMaster).
ShowstopperAction.
 */
public class ShrapnelAction extends AbstractGameAction {

    private final int DAMAGE_THRESHOLD = 10;
    private final int DAMAGE;
    private final int POWER_STACKS;

    public ShrapnelAction(AbstractCreature target, AbstractCreature source, int amount, DamageInfo.DamageType type, int stacks, AbstractGameAction.AttackEffect effect)
    {
        setValues(target, source, amount);  //Shorthand. Also sets duration = 0.5F
        DAMAGE = amount;
        actionType = AbstractGameAction.ActionType.DAMAGE;
        damageType = type;
        attackEffect = effect;
        POWER_STACKS = stacks;

        if (damageType != DamageInfo.DamageType.NORMAL
            || DAMAGE < DAMAGE_THRESHOLD) {isDone = true;}
    }

    @Override
    public void update()
    {
//Adds VFX from LifeDrainAction at the start of the Action
        if (duration == 0.5f) {AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect));}
//In any Action, must set "isDone" to "true" or call tickDuration(), ELSE CRASH.
        tickDuration(); //Sets isDone at the end of the Action

        if (isDone) {
            applyDebuffs(DAMAGE);

            //If this Action itself somehow does damage, remove this:
            //target.damage(new DamageInfo(source, DAMAGE, damageType));

            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1f));
        }
    }

//Finally apply Strength loss for each 10 unblocked damage dealt by the attack
//REF : bardinspirepack's LifeDrain.
    private void applyDebuffs (int damageAmount){
        int triggerCount = damageAmount / DAMAGE_THRESHOLD;
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, player, new IgnitePower(target, 2 * POWER_STACKS * triggerCount), 2 * POWER_STACKS * triggerCount, true));
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, player, new VulnerablePower(target, POWER_STACKS * triggerCount, false), POWER_STACKS * triggerCount, true));

        //Works, more satisfying, but slower:
        /*for (int i = 0; i < triggerCount; i++) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, player, new VulnerablePower(target, POWER_STACKS, false), POWER_STACKS, true));
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, player, new IgnitePower(target, 2* POWER_STACKS), 2* POWER_STACKS, true));
        }*/
//REF: VenEmous (no typo) stance. See also : LifeDrainAction (bardinspirepack)
    }
}


