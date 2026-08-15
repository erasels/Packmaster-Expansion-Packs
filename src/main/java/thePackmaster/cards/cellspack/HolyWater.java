package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.powers.summonspack.JinxPower;

public class HolyWater extends AbstractCellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("HolyWater");
    private static final int COST = 2;
    private static final int IGNITE = 3;
    private static final int JINX = 5;

    public HolyWater() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = IGNITE;
        this.secondMagic = this.baseSecondMagic = JINX;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new JinxPower(m, this.secondMagic)));
        this.addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber)));

    }
}