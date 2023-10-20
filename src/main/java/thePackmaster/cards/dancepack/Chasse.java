package thePackmaster.cards.dancepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.dancepack.DefaultDanceMod;
import thePackmaster.util.dancepack.JediUtil;

public class Chasse extends AbstractDanceCard {

    public final static String ID = makeID(Chasse.class.getSimpleName());

    public Chasse() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        CardModifierManager.addModifier(this, new DefaultDanceMod());
        setDamage(10);
        setMN(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void applyPowers() {
        int tmp = baseDamage;
        baseDamage += JediUtil.getDanceSize() * magicNumber;
        super.applyPowers();
        baseDamage = tmp;
        isDamageModified = baseDamage != damage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = baseDamage;
        baseDamage += JediUtil.getDanceSize() * magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = tmp;
        isDamageModified = baseDamage != damage;
    }
}
