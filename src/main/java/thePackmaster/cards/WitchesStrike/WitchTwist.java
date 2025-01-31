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
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new WitchTwistPower(p,magicNumber));
    }
    public void upp() {
        upgradeMagicNumber(3);
    }
    @Override
    public String cardArtCopy() {
        return Leap.ID;
    }
}

