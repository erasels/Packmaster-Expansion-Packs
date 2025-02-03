package thePackmaster.cards.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.bladestormpack.FallOverAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

//REFS: DummyStrikeJr (strikepack), KillThirst (rimworldpack)
public class FallOver extends AbstractBladeStormCard {
    public final static String ID = makeID("FallOver");
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;
    private static final int STRENGTH_GAIN = 1;
    private static final int BIND_ON_KILL = 10;
    private static final int UPG_BIND_ON_KILL = 5;

    public FallOver() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = BIND_ON_KILL;
        baseSecondMagic = secondMagic = STRENGTH_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.applyToSelf(new StrengthPower(p, secondMagic));
        atb(new FallOverAction(m, p, magicNumber, this));
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_BIND_ON_KILL);
    }
}
