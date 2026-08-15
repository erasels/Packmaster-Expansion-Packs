package thePackmaster.cards.fullthrottlepack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.fullthrottlepack.DragsterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Dragster extends AbstractFullThrottleCard{
    public final static String ID = makeID("Dragster");

    public Dragster() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new DragsterPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}