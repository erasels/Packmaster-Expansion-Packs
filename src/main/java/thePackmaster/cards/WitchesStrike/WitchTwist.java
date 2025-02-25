package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.cards.blue.Leap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.witchesstrikepack.WitchTwistPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WitchTwist extends AbstractWitchStrikeCard {
    public final static String ID = makeID("WitchTwist");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public WitchTwist() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new WitchTwistPower(p,magicNumber));
    }
    public void upp() {
        upgradeMagicNumber(1);
        exhaust = false;
    }
    @Override
    public String cardArtCopy() {
        return Leap.ID;
    }
}

