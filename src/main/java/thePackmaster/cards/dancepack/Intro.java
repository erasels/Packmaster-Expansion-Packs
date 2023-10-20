package thePackmaster.cards.dancepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.dancepack.IntroPower;
import thePackmaster.util.Wiz;

public class Intro extends AbstractDanceCard {

    public final static String ID = makeID(Intro.class.getSimpleName());

    public Intro() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        setMN(1);
    }

    @Override
    public void upp() {
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new IntroPower(p, magicNumber));
    }
}
