package thePackmaster.cards.doppelpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.EmergencyContactAction;

public class EmergencyContact extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(EmergencyContact.class.getSimpleName());

    public EmergencyContact() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        String[] extendedDescription = getCardStrings(ID).EXTENDED_DESCRIPTION;
        addToBot(new EmergencyContactAction(magicNumber, magicNumber == 1 ? extendedDescription[0] : String.format(extendedDescription[1], magicNumber)));
    }
}
