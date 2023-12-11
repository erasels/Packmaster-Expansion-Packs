package thePackmaster.cards.magnetizepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.magnetizepack.MagnetizeAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Attraction extends AbstractMagnetizeCard {
    public final static String ID = makeID(Attraction.class.getSimpleName());

    public Attraction() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Fuzz();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : DrawCardAction.drawnCards)
                    addToTop(new MagnetizeAction(c));
                isDone = true;
            }
        }));
        addToBot(new MakeTempCardInHandAction(cardsToPreview, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}