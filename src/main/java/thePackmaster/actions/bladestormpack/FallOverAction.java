package thePackmaster.actions.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.ChangePlayedCardExhaustAction;
import thePackmaster.powers.needlework.BindPower;

/*REFS: GremlinLanceFollowupAction (monsterhunterpack), KillThirstAction (rimworldpack), Knot (needleworkpack),
ArrowStormAction (pinnaclepack)*/
public class FallOverAction extends AbstractGameAction {
    //private DamageInfo info;
    private final AbstractCreature t;
    private final AbstractCreature p;
    private final int BIND_ON_KILL;
    AbstractCard cardToExhaust;

    public FallOverAction(AbstractCreature target, AbstractCreature source, int bindOnKill, AbstractCard cardToExhaust) {
        this.t = target;
        this.p = source;
        this.BIND_ON_KILL = bindOnKill;
        this.cardToExhaust = cardToExhaust;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        if (this.t.isDead || this.t.isDying || this.t.halfDead) {
            AbstractMonster target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (target != null) {
                addToBot(new ApplyPowerAction(target, p, new BindPower(target, this.BIND_ON_KILL), BIND_ON_KILL, true));
            }
            //Now Exhaust.
            addToTop(new ChangePlayedCardExhaustAction(cardToExhaust, true));
        }
        this.isDone = true;
    }
}
