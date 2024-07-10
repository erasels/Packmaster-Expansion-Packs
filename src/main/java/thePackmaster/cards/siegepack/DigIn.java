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
    private static final int BLOCK_GAIN = 9;
    private static final int UPGRADE_BLOCK_GAIN = 5;
    private static final int BLUR_GAIN = 3;
    private static final int UPGRADE_BLUR_GAIN = 1;
    private static final int ENERGY_GAIN = 2;
    //private static final int UPGRADE_ENERGY_GAIN = 1;

    public DigIn() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseBlock = block = BLOCK_GAIN;
        baseMagicNumber = magicNumber = BLUR_GAIN;
        baseSecondMagic = secondMagic = ENERGY_GAIN;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new BlurPower(p, BLUR_GAIN));
        applyToSelf(new DigInPower(p, secondMagic));
    }
    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK_GAIN);
        upgradeMagicNumber(UPGRADE_BLUR_GAIN);
    }
}
