package thePackmaster.cards.grandopeningpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class PlotArmor extends AbstractGrandOpeningCard {
    public final static String ID = makeID("PlotArmor");
    public PlotArmor() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseBlock = block = 4;
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
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            int innateCount = 0;
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                if (c.isInnate || c instanceof StartupCard)
                    innateCount++;
            }
            int originalBaseBlock = this.baseBlock;
            int originalBlock = this.block;
            boolean originalIsBlockModified = this.isBlockModified;

            this.baseBlock = innateCount * magicNumber;
            super.applyPowers();
            int extraBlock = this.block;

            this.baseBlock = originalBaseBlock;
            this.block = originalBlock;
            this.isBlockModified = originalIsBlockModified;

            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[3] + extraBlock + cardStrings.EXTENDED_DESCRIPTION[4] + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + cardStrings.EXTENDED_DESCRIPTION[2];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[2]);
        }
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int innateCount = 0;
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
                if (c.isInnate || c instanceof StartupCard)
                    innateCount++;
            }
        }
        int originalBaseBlock = this.baseBlock;
        int originalBlock = this.block;
        boolean originalIsBlockModified = this.isBlockModified;

        this.baseBlock = innateCount * magicNumber;
        super.applyPowers();
        int extraBlock = this.block;

        this.baseBlock = originalBaseBlock;
        this.block = originalBlock;
        this.isBlockModified = originalIsBlockModified;

        atb(new GainBlockAction(p, block + extraBlock));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}