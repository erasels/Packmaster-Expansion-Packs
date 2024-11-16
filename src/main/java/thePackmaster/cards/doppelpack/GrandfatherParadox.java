package thePackmaster.cards.doppelpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.GrandfatherParadoxAction;

public class GrandfatherParadox extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(GrandfatherParadox.class.getSimpleName());

    public GrandfatherParadox() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        String[] extendedDescription = getCardStrings(ID).EXTENDED_DESCRIPTION;
        addToBot(new GrandfatherParadoxAction(magicNumber, magicNumber == 1 ? extendedDescription[0] : String.format(extendedDescription[1], magicNumber)));
    }
}
