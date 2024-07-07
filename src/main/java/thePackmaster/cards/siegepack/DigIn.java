package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import thePackmaster.powers.siegepack.DigInPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class DigIn extends AbstractSiegeCard {
    public final static String ID = makeID("DigIn");
    private static final int COST = 2;
    private static final int BLUR_GAIN = 3;
    private static final int ENERGY_GAIN = 2;
    private static final int UPGRADE_ENERGY_GAIN = 1;

    public DigIn() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = ENERGY_GAIN;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Adds 3 Blur and the conditional energy gain power.
        Wiz.applyToSelf(new BlurPower(p, BLUR_GAIN));
        applyToSelf(new DigInPower(p, magicNumber));
    }
    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_ENERGY_GAIN);
    }
}
