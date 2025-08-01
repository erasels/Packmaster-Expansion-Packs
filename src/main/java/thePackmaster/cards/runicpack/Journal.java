package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.runicpack.JournalPower;
import thePackmaster.stances.runicpack.RunicStance;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Journal extends AbstractRunicCard {

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    public final static String ID = makeID("Journal");


    public Journal() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new ApplyPowerAction(abstractPlayer, abstractPlayer, new JournalPower(abstractPlayer, 1)));
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}
