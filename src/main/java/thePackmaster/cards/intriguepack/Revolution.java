package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.intriguepack.RevolutionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.forAllMonstersLiving;

public class Revolution extends AbstractIntrigueCard {
    public final static String ID = makeID("Revolution");

    private static final int DAMAGE = 12;
    private static final int UPGRADE_PLUS_DMG = 3;

    public Revolution() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 2;
        isMultiDamage = true;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        forAllMonstersLiving(
                (mo)->{
                    ApplyPowerAction revolution = new ApplyPowerAction(mo, p, new RevolutionPower(mo,magicNumber), magicNumber);
                    atb(revolution);
                }
        );
    }

    @Override
    public void upp() {
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(1);
    }
}