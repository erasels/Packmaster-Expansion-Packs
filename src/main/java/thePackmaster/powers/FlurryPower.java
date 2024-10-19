package thePackmaster.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;
import thePackmaster.util.Wiz;

public abstract class FlurryPower extends AbstractPackmasterPower {
    public FlurryPower(String ID, String NAME, AbstractCreature owner, int amount) {
        super(ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public abstract void onFlurry(AbstractCard played, AbstractCreature c);

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        onFlurry(card, action.target);
        AbstractDungeon.effectList.add(new FlashPowerEffect(this));
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            Wiz.removePower(this);
        }
    }
}
