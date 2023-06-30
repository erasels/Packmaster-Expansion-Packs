package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.magnetizepack.MagnetizeAction;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FaradayField extends AbstractMagnetizeCard {
    public final static String ID = makeID(FaradayField.class.getSimpleName());

    public FaradayField() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL);
        baseDamage = 17;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : p.hand.group)
                    addToTop(new MagnetizeAction(c));
                isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }
}