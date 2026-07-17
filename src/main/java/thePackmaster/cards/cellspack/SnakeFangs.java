package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.cellspack.MultiHitPoisonAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SnakeFangs extends AbstractCellsCard {

    private static final int COST = 1;
    private static final int DAMAGE = 3;
    public final static String ID = makeID("SnakeFangs");


    public SnakeFangs() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Queue Hit 1 + its poison check
        this.addToBot(new MultiHitPoisonAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));

        // Queue Hit 2 + its poison check
        this.addToBot(new MultiHitPoisonAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }
}
