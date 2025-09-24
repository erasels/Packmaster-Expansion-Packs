package thePackmaster.cards.tf2pack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DirectHit extends AbstractTF2Card {
    public final static String ID = makeID("DirectHit");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_DAMAGE = 3;

    private static boolean directHitThisTurn = false;

    public DirectHit() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        directHitThisTurn = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        directHitThisTurn = true;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (directHitThisTurn) {
            this.damage = this.damage * 2;
            this.isDamageModified = true;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (directHitThisTurn) {
            this.damage = this.damage * 2;
            this.isDamageModified = true;
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = directHitThisTurn ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}