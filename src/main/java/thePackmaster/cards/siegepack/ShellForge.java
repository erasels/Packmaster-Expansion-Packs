package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.siegepack.RampingTurnVigorGainPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

//REFS: EventHorizon (marisapack), GladiatorForm (Downfall)
public class ShellForge extends AbstractSiegeCard {
    public final static String ID = makeID("ShellForge");
    private static final int COST = 1;
    private static final int BASE_VIGOR_PER_TURN = 3;
    private static final int UPG_BASE_VIGOR_PER_TURN = 1;
    private static final int INCREASE_FROM_GAIN_OR_LOSS = 1;

    public ShellForge() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = BASE_VIGOR_PER_TURN;
        baseSecondMagic = secondMagic = INCREASE_FROM_GAIN_OR_LOSS;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new RampingTurnVigorGainPower(p, magicNumber, secondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_BASE_VIGOR_PER_TURN);
    }
}
