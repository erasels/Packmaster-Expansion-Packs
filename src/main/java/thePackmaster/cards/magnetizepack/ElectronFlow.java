package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.magnetizepack.PolarityPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

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

            ArrayList<AbstractCard> cards2 = new ArrayList<>(cards);
            Wiz.att(new AbstractGameAction() {
                @Override
                public void update() {
                    CardGroup group = AbstractDungeon.player.hand;
                    for (AbstractCard c : cards2) {
                        if (group.contains(c)) {
                            group.moveToDiscardPile(c);
                            GameActionManager.incrementDiscard(false);
                            c.triggerOnManualDiscard();
                        }
                    }

                    this.isDone = true;
                }
            });
        }));

        addToBot(new MakeTempCardInHandAction(cardsToPreview, 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }
}