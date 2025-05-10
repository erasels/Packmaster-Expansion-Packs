package thePackmaster.cards.intothebreachpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.intothebreachpack.FlamethrowerAction;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.atb;

public class Flamethrower extends IntoTheBreachCard {
    public final static String ID = makeID("Flamethrower");

    public Flamethrower() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new IgnitePower(m, magicNumber));
        atb(new FlamethrowerAction());
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}