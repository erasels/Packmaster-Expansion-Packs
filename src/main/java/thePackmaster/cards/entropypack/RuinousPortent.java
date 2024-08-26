package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.entropypack.EntropyPower;
import thePackmaster.powers.entropypack.RuinousPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class RuinousPortent extends AbstractEntropyCard {
    public final static String ID = makeID("RuinousPortent");

    public RuinousPortent() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EntropyPower(p, this.magicNumber));
        applyToSelf(new RuinousPower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}