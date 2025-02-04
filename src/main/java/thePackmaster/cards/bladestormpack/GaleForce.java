package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bladestormpack.GaleForcePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

//REFS: EntropyPower (entropypack)
public class GaleForce extends AbstractBladeStormCard {
    public final static String ID = makeID("GaleForce");
    private static final int COST = 2;
    private static final int EXTRA_WINDRUSH = 1;
    private static final int CARDS_TO_DRAW = 1;
    private static final int DRAW_COST_THRESHOLD = 1;

    public GaleForce() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = EXTRA_WINDRUSH;
        baseSecondMagic = secondMagic = CARDS_TO_DRAW;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new GaleForcePower(p, 1, magicNumber, secondMagic, DRAW_COST_THRESHOLD)));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}
