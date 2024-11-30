package thePackmaster.cards.doppelpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.doppelpack.TurbulencePower;

public class Turbulence extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Turbulence.class.getSimpleName());

    public Turbulence() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new TurbulencePower(p, magicNumber), magicNumber));
    }
}
