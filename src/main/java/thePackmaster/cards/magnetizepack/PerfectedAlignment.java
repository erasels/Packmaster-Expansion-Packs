package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PerfectedAlignment extends AbstractMagnetizeCard {
    public final static String ID = makeID(PerfectedAlignment.class.getSimpleName());

    public PerfectedAlignment() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 14;
        baseMagicNumber = magicNumber = 2;
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
    }

    public static int countCards() {
        int count = 0;

        count += Wiz.adp().hand       .group.stream().filter(card -> CardModifierManager.hasModifier(card, MagnetizedModifier.ID)).count();
        count += Wiz.adp().drawPile   .group.stream().filter(card -> CardModifierManager.hasModifier(card, MagnetizedModifier.ID)).count();
        count += Wiz.adp().discardPile.group.stream().filter(card -> CardModifierManager.hasModifier(card, MagnetizedModifier.ID)).count();

        return count;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * countCards();
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * countCards();
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}