package thePackmaster.cards.tf2pack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class MiniSentry extends AbstractTF2Card implements ResupplyCardInterface {
    public final static String ID = makeID("MiniSentry");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

    private static final int COST = 0;

    private static final int DAMAGE = 5;
    private static final int SECOND_DAMAGE = 2;
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;

    public MiniSentry() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
        this.baseSecondDamage = this.secondDamage = SECOND_DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_MAGIC);
    }

    @Override
    public void triggerOnSelfResupply() {
        for (int i = 0; i < this.magicNumber; i++) {
            this.addToTop(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.secondDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }
}
