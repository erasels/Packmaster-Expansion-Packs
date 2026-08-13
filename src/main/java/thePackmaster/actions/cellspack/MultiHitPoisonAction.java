package thePackmaster.actions.cellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class MultiHitPoisonAction extends AbstractGameAction {
    private DamageInfo info;

    public MultiHitPoisonAction(AbstractCreature target, DamageInfo info) {
        this.target = target;
        this.source = info.owner;
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            // Ensure the target is still valid before swinging
            if (this.target != null && !this.target.isDeadOrEscaped()) {

                // 1. Play the visual attack effect
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_DIAGONAL));

                // 2. Deal the damage. This updates target.lastDamageTaken instantly
                this.target.damage(this.info);

                // 3. Check unblocked damage from *this* specific hit
                int poisonAmount = this.target.lastDamageTaken;

                // 4. If any damage went through, immediately queue the poison application to the top
                if (poisonAmount > 0) {
                    this.addToTop(new ApplyPowerAction(this.target, this.source,
                            new PoisonPower(this.target, this.source, poisonAmount), poisonAmount, true));
                }
            }
        }

        this.tickDuration();
    }
}