package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static thePackmaster.util.Wiz.atb;

public class ForcefieldAction extends AbstractGameAction {

    private boolean freeToPlayOnce = false;
    private final AbstractPlayer p;
    private int energyOnUse = -1;
    private int extraUses = 0;

    public ForcefieldAction(AbstractPlayer p, int amount, boolean freeToPlayOnce, int energyOnUse, int extraUses) {
        this.amount = amount;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.extraUses = extraUses;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic(ChemicalX.ID)) {
            effect += 2;
            this.p.getRelic(ChemicalX.ID).flash();
        }

        if (effect + extraUses > 0) {
            for (int i = 0; i < effect + extraUses; ++i) {
                this.addToBot(new GainBlockAction(this.p, this.p, this.amount));
            }

            if (effect >= 3) {
                atb(new ApplyPowerAction(p, p, new BlurPower(p, 1)));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;


    }
}
