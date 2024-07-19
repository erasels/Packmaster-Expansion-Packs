package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.siegepack.Shelling;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Collections;

import static thePackmaster.util.Wiz.*;

//REF: ReboundVolleyAction (intothebreachpack).
public class ShellingAction extends AbstractGameAction {
    private final Shelling card;
    private AbstractMonster targetEnemy;
    private int targets;
    private ArrayList<AbstractMonster> canTarget;
    private ArrayList<AbstractGameAction> actionsHoldingQueue;

    public ShellingAction(Shelling card, AbstractMonster specifiedTarget, int randomTargets) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.card = card;
        this.targetEnemy = specifiedTarget;
        this.targets = randomTargets+1;
        this.canTarget = getEnemies();
        this.actionsHoldingQueue = new ArrayList<>();
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            boolean firstHit = true;
            for (int i=0; i<targets; i++) {
                if (canTarget.isEmpty()) {
                    tickDuration();
                    return;
                }
                if (!canTarget.contains(targetEnemy)) {
                    targetEnemy = Wiz.getRandomItem(canTarget);
                }
                canTarget.remove(targetEnemy);
                if (targetEnemy != null) {
                    //VFX would go here.
                    card.superCalculateCardDamage(targetEnemy);
                    actionsHoldingQueue.add(new DamageAction(targetEnemy,
                            new DamageInfo(adp(), firstHit ? this.card.damage : this.card.secondDamage, this.card.damageTypeForTurn),
                            firstHit ? AttackEffect.BLUNT_HEAVY : AttackEffect.BLUNT_LIGHT));
                    firstHit = false;
                }
            }
            tickDuration();
        } else {
            //Must be a reason for this
            Collections.reverse(actionsHoldingQueue);
            for (AbstractGameAction action : actionsHoldingQueue) {
                att(action);
            }
            this.isDone = true;
        }
    }
}
