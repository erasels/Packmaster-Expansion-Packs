package thePackmaster.cards.grandopeningpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.evenoddpack.AbstractEvenOddCard.makeCardTextGray;
import static thePackmaster.util.Wiz.atb;

public class Lights extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Lights");

    public Lights() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Camera();
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[5];
        initializeDescription();
        exhaust = true;
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[4];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[5];
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + cardStrings.EXTENDED_DESCRIPTION[2] + cardStrings.EXTENDED_DESCRIPTION[3] + cardStrings.EXTENDED_DESCRIPTION[4] +  cardStrings.EXTENDED_DESCRIPTION[5];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[2] + cardStrings.EXTENDED_DESCRIPTION[4]) + cardStrings.EXTENDED_DESCRIPTION[5];
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new EnergizedPower(p, magicNumber), magicNumber));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
                    atb(new MakeTempCardInDrawPileAction(cardsToPreview.makeCopy(), 1, true, true));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}