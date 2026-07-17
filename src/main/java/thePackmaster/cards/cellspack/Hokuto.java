package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.cellspack.HokutoPower;

public class Hokuto extends AbstractCellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Hokuto");
    private static final int COST = 1;
    private static final int AMOUNT = 1;
    private static final int UPGRADE_AMOUNT = 1;

    public Hokuto() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_AMOUNT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HokutoPower(p, this.magicNumber)));
    }
}

