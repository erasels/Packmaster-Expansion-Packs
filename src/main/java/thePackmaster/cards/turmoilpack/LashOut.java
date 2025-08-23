package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.actions.turmoilpack.AbandonAction;

public class LashOut extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("LashOut");
    private static final int COST = -1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int DRAW = 1;

    public LashOut() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.isMultiDamage = true;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EasyXCostAction(this, (amount, params) -> {
           for (int i = 0; i < amount; i++) {
               this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
           }
           return true;
        }));
        this.addToBot(new AbandonAction(c -> c.type == CardType.SKILL, l -> {
            if (l.isEmpty()) { return; }
            this.addToTop(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber * l.size())));
        }));
    }
}
