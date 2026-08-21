package thePackmaster.cards.fullthrottlepack;


import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Downforce extends AbstractFullThrottleCard {
    public final static String ID = makeID("Downforce");

    public Downforce() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.baseSecondMagic = 1;
        this.secondMagic = this.baseSecondMagic;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AllEnemyApplyPowerAction(p, magicNumber, n -> new IgnitePower(n, magicNumber)));
        atb(new AllEnemyApplyPowerAction(p, secondMagic, n -> new VulnerablePower(n, secondMagic, false)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}


