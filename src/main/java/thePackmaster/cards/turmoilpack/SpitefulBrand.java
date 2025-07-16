package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.turmoilpack.AbandonAction;
import thePackmaster.cards.spherespack.AbstractSpheresCard;

public class SpitefulBrand extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("SpitefulBrand");
    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int DEBUFFS = 1;

    public SpitefulBrand() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = DEBUFFS;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new AbandonAction(c -> c.costForTurn == 1, l -> {
            this.addToTop(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber * l.size(), false)));
            this.addToTop(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber * l.size(), false)));
        }));
    }
}
