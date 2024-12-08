package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.powers.royaltypack.ForTheHistoryBooksPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ForTheHistoryBooks extends AbstractRoyaltyCard {

    public final static String ID = makeID("ForTheHistoryBooks");

    public final static int AMOUNT_OF_TEMP_STR_AND_DEX = 3;
    public final static int INCREASE_ON_UPGRADE = 2;

    public ForTheHistoryBooks(){
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = AMOUNT_OF_TEMP_STR_AND_DEX;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(INCREASE_ON_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
        int currentMultiplier = EnergyPanel.getCurrentEnergy();

        if (abstractPlayer.hasRelic("Chemical X")) {
            currentMultiplier += 2;
            abstractPlayer.getRelic("Chemical X").flash();
        }

        if (!this.freeToPlayOnce) {
            abstractPlayer.energy.use(EnergyPanel.totalCount);
        }
        Wiz.atb(new ApplyPowerAction(abstractPlayer,
                abstractPlayer,
                new ForTheHistoryBooksPower(abstractPlayer,
                        magicNumber * currentMultiplier)));
    }
}
