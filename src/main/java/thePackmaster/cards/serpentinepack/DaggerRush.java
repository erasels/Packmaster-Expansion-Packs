package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DaggerRush extends AbstractSerpentineCard {

    private static final int COST = 1;
    private static final int MAGIC = 3;
    private static final int DAMAGE = 3;
    private static final int UPG_MAGIC = 1;
    public final static String ID = makeID("DaggerRush");


    public DaggerRush() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}
