package thePackmaster.cards.tf2pack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class MiniSentry extends AbstractTF2Card implements ResupplyCardInterface {
    public final static String ID = makeID("MiniSentry");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;

    private static final int COST = 0;

    private static final int DAMAGE = 2;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;
    private static final int SECOND_MAGIC = 1;

    public MiniSentry() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC;
        this.baseSecondMagic = this.secondMagic = SECOND_MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            this.dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_MAGIC);
    }

    @Override
    public void triggerOnSelfResupply() {
        this.addToBot(new AllEnemyApplyPowerAction(AbstractDungeon.player, secondMagic, (q) -> new VulnerablePower(q, secondMagic, false)));
    }
}
