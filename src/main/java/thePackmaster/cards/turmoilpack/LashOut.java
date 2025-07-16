package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.actions.turmoilpack.AbandonAction;
import thePackmaster.cards.spherespack.AbstractSpheresCard;

public class LashOut extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("LashOut");
    private static final int COST = -1;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_DAMAGE = 2;
    private static final int DRAW = 1;

    public LashOut() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = DRAW;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new EasyXCostAction(this, (amount, params) -> {
           for (int i = 0 ; i < amount + 1; i++) {
               this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
           }
           return true;
        });
        this.addToBot(new AbandonAction(c -> SpireAnniversary5Mod.cardParentMap.containsKey(c.cardID), l -> {
            this.addToTop(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber * l.size())));
        }));
    }
}
