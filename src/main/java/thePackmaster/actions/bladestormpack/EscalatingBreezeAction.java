package thePackmaster.actions.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.EnergizedPower;

//REFS: RetainCardsAction (base game),
// OpeningStrike (overwhelmingpack), ShellingAction (siegepack), IcyFloe (gowiththeflowpack)
public class EscalatingBreezeAction extends AbstractGameAction {

    public EscalatingBreezeAction(AbstractCreature source) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public void update() {
        this.isDone = true;

        if (AbstractDungeon.player.hand.group.isEmpty()) {
            return;
        }

        int generatedEnergy = 0;

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                generatedEnergy += 1;
            }
        }

        //Energy gain
        if (generatedEnergy > 0) {
            if (!Settings.FAST_MODE) {
                addToBot(new WaitAction(0.2F));
            }
            addToBot(new ApplyPowerAction(source, source, new EnergizedPower(source, generatedEnergy), generatedEnergy));
        }

        if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
    }
}
