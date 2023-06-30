package thePackmaster.cards.magnetizepack;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.magnetizepack.MagnetizeAction;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Attraction extends AbstractMagnetizeCard {
    public final static String ID = makeID(Attraction.class.getSimpleName());

    public Attraction() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Fuzz();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DrawCardAction(1));
            if (p.hand.size() + i <= BaseMod.MAX_HAND_SIZE)
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (!p.hand.isEmpty())
                            addToTop(new MagnetizeAction(p.hand.getTopCard()));
                        isDone = true;
                    }
                });
        }
        addToBot(new MakeTempCardInHandAction(cardsToPreview, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}