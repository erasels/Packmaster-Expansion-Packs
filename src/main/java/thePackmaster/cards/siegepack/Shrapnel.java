package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.powers.siegepack.ShrapnelPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

//ShrapnelPower and ShrapnelAction classes do the heavy lifting.
public class Shrapnel extends AbstractSiegeCard {
    public final static String ID = makeID("Shrapnel");
    private static final int COST = 1;
    private static final int DAMAGE_THRESHOLD = 10;

    public Shrapnel() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 5;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction (p, p, new ShrapnelPower(p, magicNumber, DAMAGE_THRESHOLD), magicNumber));
        if (upgraded) {
            this.addToBot(new ApplyPowerAction (p, p, new VigorPower(p, secondMagic), secondMagic));
        }

    }

    @Override
    public void upp() {
        //in use().
    }
}
