package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RetainCardPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class WellWrittenScript extends AbstractGrandOpeningCard {
    public final static String ID = makeID("WellWrittenScript");

    public WellWrittenScript() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 0) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + cardStrings.EXTENDED_DESCRIPTION[2];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[2]);
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        atb(new ApplyPowerAction(p, p, new RetainCardPower(p, magicNumber)));
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
            atb(new DrawCardAction(secondMagic));
        }
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}