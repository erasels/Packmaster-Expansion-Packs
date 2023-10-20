package thePackmaster.cardmodifiers.dancepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.util.dancepack.JediUtil;

public class DamageDanceMod extends AbstractMadScienceModifier {

    public int amount;
    public DamageDanceMod(int amt)
    {
        amount = amt;
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new DamageDanceMod(amount);
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage+ JediUtil.getDanceSize();
    }
}
