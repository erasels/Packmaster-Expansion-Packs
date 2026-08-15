package thePackmaster.cards.fullthrottlepack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.fullthrottlepack.AfterburnerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Afterburner extends AbstractFullThrottleCard{
    public final static String ID = makeID("Afterburner");

    public Afterburner() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new AfterburnerPower(p, magicNumber)));
    }

    @Override
    public void upp() {
        isInnate = true;
    }
}