package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Suppress extends AbstractSiegeCard {
    public final static String ID = makeID("Suppress");
    private static final int COST = 2;
    private static final int DAMAGE = 11;
    private static final int WEAK = 1;
    private static final int CONDITIONAL_STRENGTH_DOWN = 2;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int UPGRADE_WEAK = 1;

    public Suppress() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = WEAK;
        baseSecondMagic = secondMagic = CONDITIONAL_STRENGTH_DOWN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        //Applies Weak
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));

        //Adds temporary Strength reduction to enemy.
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -secondMagic), -secondMagic));
        if (m != null && !m.hasPower(ArtifactPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, secondMagic), secondMagic));
        }
        /*if (isRetaliatory(m)) {
            addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -secondMagic), -secondMagic));
            if (m != null && !m.hasPower(ArtifactPower.POWER_ID)) {
                addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, secondMagic), secondMagic));
            }
        }*/
    }

    //If enemy is attacking
    /*public boolean isRetaliatory(AbstractMonster m)
    {
        return m != null && m.getIntentBaseDmg() >= 0;
    }*/

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_WEAK);
    }
}