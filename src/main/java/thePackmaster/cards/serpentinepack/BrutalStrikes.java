package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BrutalStrikes extends AbstractSerpentineCard {

    private static final int COST = 2;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int DAMAGE = 10;
    public final static String ID = makeID("BrutalStrikes");


    public BrutalStrikes() {
        super(ID, COST, AbstractCard.CardType.ATTACK, CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int debuffs = 0;
        for (AbstractPower p : mo.powers){
            if (p.type.equals(AbstractPower.PowerType.DEBUFF)){
                debuffs++;
            }
        }
        if (debuffs != 0){
            int realBaseDamage = this.baseDamage;
            this.baseDamage += (this.magicNumber * debuffs);
            super.calculateCardDamage(mo);
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
        }
        else {
            super.calculateCardDamage(mo);
        }
    }
    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}
