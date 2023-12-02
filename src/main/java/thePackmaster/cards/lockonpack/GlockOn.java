package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.powers.lockonpack.GlockOnPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GlockOn extends AbstractLockonCard {

    public final static String ID = makeID(GlockOn.class.getSimpleName());

    public GlockOn() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new LockOnPower(m, magicNumber));
        Wiz.applyToSelf(new GlockOnPower(p, 1));
    }
}
