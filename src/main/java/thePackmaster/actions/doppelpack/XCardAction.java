package thePackmaster.actions.doppelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class XCardAction extends AbstractGameAction {
    private final AbstractPlayer player;
    private final boolean freeToPlayOnce;
    private final int energyOnUse;
    private final Consumer<Integer> onUpdate;
    private final boolean addToTop;

    public XCardAction(ActionType actionType, AbstractPlayer player, boolean freeToPlayOnce, int energyOnUse, Consumer<Integer> onUpdate, boolean addToTop) {
        this.player = player;
        this.freeToPlayOnce = freeToPlayOnce;
        this.energyOnUse = energyOnUse;
        this.onUpdate = onUpdate;
        this.actionType = actionType;
        this.addToTop = addToTop;
    }

    @Override
    public void update() {
        int amount = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            amount = this.energyOnUse;
        }

        AbstractPlayer p = player;
        AbstractRelic relic = p.getRelic(ChemicalX.ID);
        if (relic != null) {
            relic.flash();
            amount += 2;
        }

        if (addToTop) {
            List<AbstractGameAction> actions = new ArrayList<>(AbstractDungeon.actionManager.actions);
            AbstractDungeon.actionManager.actions.clear();
            onUpdate.accept(amount);
            AbstractDungeon.actionManager.actions.addAll(actions);
        } else {
            onUpdate.accept(amount);
        }

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }

        isDone = true;
    }
}
