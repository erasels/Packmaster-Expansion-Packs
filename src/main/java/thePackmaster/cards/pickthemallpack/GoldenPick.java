package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class GoldenPick extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GoldenPick");
    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int GOLD = 75;
    private static final int GOLD_PER_CARD = 2;;

    public GoldenPick() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = GOLD;
        this.secondMagic = this.baseSecondMagic = GOLD_PER_CARD;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void onObtainCard() {
        int gold = GOLD + AbstractDungeon.player.masterDeck.size() * GOLD_PER_CARD;
        AbstractDungeon.player.gainGold(gold);
    }
}
