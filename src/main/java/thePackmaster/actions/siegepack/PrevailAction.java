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

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class PrevailAction extends AbstractGameAction {

    private static int damageThreshold;
    private final int DAMAGE;
    private final int POWER_STACKS;

    public PrevailAction(AbstractCreature target, AbstractCreature source, int amount, DamageInfo.DamageType type, int stacks, int damageThreshold, AbstractGameAction.AttackEffect effect)
    {
        setValues(target, source, amount);  //Shorthand. Also sets duration = 0.5F
        duration = 0.1F;
        DAMAGE = amount;
        actionType = AbstractGameAction.ActionType.DAMAGE;
        damageType = type;
        attackEffect = effect;
        POWER_STACKS = stacks;
        PrevailAction.damageThreshold = damageThreshold;
    }

    @Override
    public void update() {
        //In any Action, must set "isDone" to "true" or call tickDuration(), else Crash.
        isDone = true;
        if (damageType != DamageInfo.DamageType.NORMAL || DAMAGE < PrevailAction.damageThreshold)
        { return; }

        applyDebuffs();
        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.1f));
    }

    private void applyDebuffs (){
        addToBot(new ApplyPowerAction(target, player, new StrengthPower(target, -POWER_STACKS), -POWER_STACKS)); //Strength power has no "isFast".
        if (target != null && !target.hasPower(ArtifactPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(target, player, new GainStrengthPower(target, POWER_STACKS), POWER_STACKS));
        }
    }
}
