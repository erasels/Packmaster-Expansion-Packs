package thePackmaster.cards.dancepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.dancepack.BreakdancePower;
import thePackmaster.util.Wiz;

public class Breakdance extends AbstractDanceCard {

    public final static String ID = makeID(Breakdance.class.getSimpleName());

    public Breakdance() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        setMN(1);
        isEthereal = true;
    }

    @Override
    public void upp() {
        isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new BreakdancePower(p, magicNumber));
    }
}
