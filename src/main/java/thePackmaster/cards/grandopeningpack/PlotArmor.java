package thePackmaster.cards.grandopeningpack;

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
        baseMagicNumber = magicNumber = 3;
        baseBlock = block = 6;
        baseSecondMagic = secondMagic = 0;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int innateCount = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.isInnate)
                innateCount++;
        }
        atb(new GainBlockAction(p, block+magicNumber*innateCount));
    }

    public void applyPowers() {
        int realBaseBlock = baseBlock;
        isDamageModified = damage != baseDamage;
        int innateCount = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.isInnate)
                innateCount++;
        }
        if (innateCount > 0) {
            baseBlock += innateCount * magicNumber;
            super.applyPowers();
            initializeDescription();
        }
        baseDamage = realBaseBlock;
    }

    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}