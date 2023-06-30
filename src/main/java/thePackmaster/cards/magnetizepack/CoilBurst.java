package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.cards.alchemistpack.AbstractAlchemistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CoilBurst extends AbstractMagnetizeCard {
    public final static String ID = makeID(CoilBurst.class.getSimpleName());

    public CoilBurst() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        baseDamage = 9;
        cardsToPreview = new Fuzz();
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        addToBot(new MakeTempCardInHandAction(cardsToPreview, 2));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}