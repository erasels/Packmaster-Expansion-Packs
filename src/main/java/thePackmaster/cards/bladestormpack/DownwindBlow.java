package thePackmaster.cards.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.instadeathpack.CloudPower;
import thePackmaster.powers.instadeathpack.Precision;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DownwindBlow extends AbstractBladeStormCard {
    public final static String ID = makeID("DownwindBlow");
    private static final int COST = 3;
    private static final int DAMAGE = 20;
    private static final int UPG_DAMAGE = 5;
    private static final int PRECISION = 9;
    private static final int UPG_PRECISION = 3;
    private static final int DAMAGE_PROTECTION_PERCENTS = 50;

    public DownwindBlow() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = PRECISION;
        baseSecondMagic = secondMagic = DAMAGE_PROTECTION_PERCENTS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new ApplyPowerAction(p, p, new Precision(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new CloudPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_PRECISION);
    }
}
