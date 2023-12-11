package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.MedicalKit;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Fuzz extends AbstractMagnetizeCard {
    public final static String ID = makeID(Fuzz.class.getSimpleName());

    public Fuzz() {
        super(ID, 0, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        exhaust = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!CardModifierManager.hasModifier(this, MagnetizedModifier.ID) && !p.hasRelic(MedicalKit.ID)) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        else return super.canUse(p, m);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upp() {
    }
}


