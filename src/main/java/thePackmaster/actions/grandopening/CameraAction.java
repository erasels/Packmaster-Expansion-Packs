package thePackmaster.actions.grandopening;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static thePackmaster.util.Wiz.*;

public class CameraAction extends AbstractGameAction {
    private final boolean free;
    private final int energyOnUse;
    public CameraAction(boolean freeToPlayOnce, int energyOnUse) {
        duration = startDuration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.DAMAGE;
        free = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse != -1)
            effect = energyOnUse;

        if (adp().hasRelic(ChemicalX.ID)) {
            effect += 2;
            adp().getRelic("Chemical X").flash();
        }
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawCardNextTurnPower(AbstractDungeon.player, effect)));
        if (!free)
            adp().energy.use(EnergyPanel.totalCount);

        isDone = true;
    }
}
