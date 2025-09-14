package thePackmaster.cards.tf2pack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LooseCannon extends AbstractTF2Card implements ResupplyCardInterface {
    public final static String ID = makeID("LooseCannon");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 2;

    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE = 2;

    public LooseCannon() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.allDmg(AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void triggerOnSelfResupply() {
        this.modifyCostForCombat(-1);
    }
}