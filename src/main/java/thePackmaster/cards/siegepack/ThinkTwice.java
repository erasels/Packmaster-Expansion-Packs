package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.siegepack.NextTurnGainShellPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

public class ThinkTwice extends AbstractSiegeCard {
    public final static String ID = makeID("ThinkTwice");
    private static final int COST = 1;
    private static final int BLOCK = 16;
    private static final int BLOCK_REDUCTION = 8;
    private static final int UPG_BLOCK_REDUCTION = -4;
    private static final int NEXT_TURN_SHELLS_GAIN = 1;

    public ThinkTwice() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = BLOCK_REDUCTION;
        baseSecondMagic = secondMagic = NEXT_TURN_SHELLS_GAIN;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        reduceBlock();
        Wiz.applyToSelf(new NextTurnGainShellPower(p, secondMagic));
    }

    //Base game's ModifyBlockAction seems bugged at 0 block.
    public void reduceBlock () {
        if (magicNumber <= 0) { return; }

        baseBlock -= magicNumber;
        if (baseBlock < 0) { baseBlock = 0; }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_BLOCK_REDUCTION);
    }
}