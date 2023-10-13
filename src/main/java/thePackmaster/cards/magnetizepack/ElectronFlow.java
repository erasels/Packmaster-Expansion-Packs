package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.magnetizepack.PolarityPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ElectronFlow extends AbstractMagnetizeCard {
    public final static String ID = makeID(ElectronFlow.class.getSimpleName());

    public ElectronFlow() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 5;
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
        cardsToPreview = new Fuzz();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(999, cardStrings.EXTENDED_DESCRIPTION[0], true, true, c -> true, cards -> {

            Wiz.applyToSelfTop(new PolarityPower(p, cards.size() * magicNumber));

            for (AbstractCard c : cards)
                addToTop(new DiscardSpecificCardAction(c));
        }));

        addToBot(new MakeTempCardInHandAction(cardsToPreview, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }
}