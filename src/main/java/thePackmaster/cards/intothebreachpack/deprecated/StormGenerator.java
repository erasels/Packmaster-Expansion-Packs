package thePackmaster.cards.intothebreachpack.deprecated;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.intothebreachpack.IntoTheBreachCard;
import thePackmaster.powers.intothebreachpack.deprecated.StormGenPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

@NoCompendium
public class StormGenerator extends IntoTheBreachCard {
    public final static String ID = makeID("StormGenerator");

    public StormGenerator() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StormGenPower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
