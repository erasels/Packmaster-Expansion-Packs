package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class PrevailAction extends AbstractGameAction {

    private static final int DAMAGE_THRESHOLD = 10;
    private final int DAMAGE;
    private final int POWER_STACKS;

    public PrevailAction(AbstractCreature target, AbstractCreature source, int amount, DamageInfo.DamageType type, int stacks, AbstractGameAction.AttackEffect effect)
    {
        setValues(target, source, amount);  //Shorthand. Also sets duration = 0.5F
        DAMAGE = amount;
        actionType = AbstractGameAction.ActionType.DAMAGE;
        damageType = type;
        attackEffect = effect;
        POWER_STACKS = stacks;

        //In any Action, must set "isDone" to "true" or call tickDuration(), ELSE CRASH.
        if (damageType != DamageInfo.DamageType.NORMAL
                || DAMAGE < DAMAGE_THRESHOLD) {isDone = true;}
    }

    @Override
    public void update() {
        if (duration == 0.5f) {AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, attackEffect));}
        tickDuration(); //Sets isDone at the end of the Action

        if (isDone) {
            applyDebuffs(DAMAGE);

            //If this Action itself somehow does damage, remove this:
            target.damage(new DamageInfo(source, DAMAGE, damageType));

            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }

            AbstractDungeon.actionManager.addToTop(new WaitAction(0.1f));
        }
    }

    private void applyDebuffs (int damageAmount){
       int triggerCount = damageAmount / DAMAGE_THRESHOLD;
        for (int i = 0; i < triggerCount; i++) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, player, new VulnerablePower(target, POWER_STACKS, false), POWER_STACKS, true));
            addToBot(new ApplyPowerAction(target, player, new StrengthPower(target, -POWER_STACKS), -POWER_STACKS)); //Strength power has no "isFast".
            if (target != null && !target.hasPower(ArtifactPower.POWER_ID)) {
                addToBot(new ApplyPowerAction(target, player, new GainStrengthPower(target, POWER_STACKS), POWER_STACKS));
            }
        }
    }
}
