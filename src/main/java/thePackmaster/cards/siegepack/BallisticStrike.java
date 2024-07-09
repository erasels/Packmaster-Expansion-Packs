package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.getEnemies;

//The single-target damage is stored in SecondDamage because "DmgAllEnemies" methods hardcodedly use Damage.
public class BallisticStrike extends AbstractSiegeCard {
    public final static String ID = makeID("BallisticStrike");
    private static final int COST = 3;
    private static final int DAMAGE = 21;
    private static final int DAMAGE_OTHERS = 6;
    private static final int UPGRADE_DAMAGE = 9;
    private static final int UPGRADE_DAMAGE_OTHERS = 5;

    public BallisticStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE_OTHERS;
        baseSecondDamage = DAMAGE;
        //isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Deal damage to target, double value if it's attacking. Then damage all Other enemies.
        this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.35F));

        if (isRetaliatory(m)) {
            //Wiz.doDmg(m, secondDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            altDmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        } else {
            altDmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }

        for (AbstractMonster mo : getEnemies()) {
            if (mo != m) {
                calculateTrueDamage(mo, false);
                dmg(m, AbstractGameAction.AttackEffect.FIRE);   //"isFast" ?.
            }
        }
    }

    //calculateCardDamage is Auto-called before every card is played.
    @Override
    public void calculateCardDamage(AbstractMonster mo){
        calculateTrueDamage(mo, true);
    }
    private void calculateTrueDamage(AbstractMonster mo, boolean isTarget) {
        if (mo == null) {return;}

        super.calculateCardDamage(mo);
        if (isTarget && isRetaliatory(mo)) {
            secondDamage *= 2;
            this.isSecondDamageModified = true;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public boolean isRetaliatory(AbstractMonster m) {return m != null && m.getIntentBaseDmg() >= 0;}

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE_OTHERS);
        upgradeSecondDamage(UPGRADE_DAMAGE);
    }
}
/*
REFS: Into The Breach's Smoldering Shell and much Discord channel code.

public void use(AbstractPlayer p, AbstractMonster m) {
    this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
    dmg(m, AbstractGameAction.AttackEffect.NONE);
    applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    for (AbstractMonster mo : getEnemies())
        if (mo != m)
            applyToEnemy(mo, new WeakPower(mo, secondMagic, false));
}

//What ENBEON Would do:
@Override
public void calculateCardDamage(AbstractMonster mo) {
    calculateTrueDamage(mo, true);
}

public void calculateTrueDamage(AbstractMonster mo, boolean isTarget) {
    super.calculateCardDamage(mo);

    if (isTarget && isRetaliatory) {
        // double secondDamage
    }
}

@Override
public void use(AbstractPlayer p, AbstractMonster m) {
    altDmg(AttackEffect.WHATEVER);
    for (AbstractMonster mo : getEnemies()) {
        calculateTrueDamage(mo, false);
        dmg(mo, AttackEffect.WHATEVER);
    }
}

*/


