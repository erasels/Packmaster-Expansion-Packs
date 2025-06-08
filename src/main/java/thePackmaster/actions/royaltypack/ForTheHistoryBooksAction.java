package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.cards.royaltypack.ForTheHistoryBooks;
import thePackmaster.powers.royaltypack.ForTheHistoryBooksPower;
import thePackmaster.powers.royaltypack.NextTurnHiredSupportPower;
import thePackmaster.util.Wiz;

public class ForTheHistoryBooksAction extends AbstractGameAction {

    private AbstractPlayer abstractPlayer;
    private AbstractMonster abstractMonster;

    private boolean isFreeToPlayOnce;

    private int magicNumber;

    public ForTheHistoryBooksAction(AbstractPlayer abstractPlayer,
                                    boolean isFreeToPlayOnce, int magicNumber){
        this.abstractPlayer = abstractPlayer;
        this.isFreeToPlayOnce = isFreeToPlayOnce;
        this.magicNumber = magicNumber;
    }

    @Override
    public void update() {
        int currentMultiplier = EnergyPanel.getCurrentEnergy();

        if (abstractPlayer.hasRelic(ChemicalX.ID)) {
            currentMultiplier += 2;
            abstractPlayer.getRelic(ChemicalX.ID).flash();
        }

        if (!isFreeToPlayOnce) {
            abstractPlayer.energy.use(EnergyPanel.totalCount);
        }

        if (currentMultiplier > 0) {
            Wiz.atb(new ApplyPowerAction(abstractPlayer,
                    abstractPlayer,
                    new ForTheHistoryBooksPower(abstractPlayer,
                            magicNumber * currentMultiplier)));
        }

        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                new NextTurnHiredSupportPower(abstractPlayer, 1)));

        this.isDone = true;
    }
}
