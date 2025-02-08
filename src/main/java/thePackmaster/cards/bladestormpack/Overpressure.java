package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import thePackmaster.packs.EvenOddPack;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

//REFS: PlotArmor (grandopening), QuickReflex (evenodd), TheMANSION (maridebuff)
public class Overpressure extends AbstractBladeStormCard {
    public final static String ID = makeID("Overpressure");
    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 2;
    private static final int FRAIL = 1;

    public Overpressure() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = FRAIL;
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0]
                + cardStrings.EXTENDED_DESCRIPTION[1]
                + cardStrings.EXTENDED_DESCRIPTION[2]
                + cardStrings.DESCRIPTION;
        initializeDescription();

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0]
                + cardStrings.EXTENDED_DESCRIPTION[1]
                + cardStrings.EXTENDED_DESCRIPTION[2]
                + cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = this.createEvenOddText();
        initializeDescription();
    }

    public String createEvenOddText() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty())
        {
            return cardStrings.EXTENDED_DESCRIPTION[0]
                    + cardStrings.EXTENDED_DESCRIPTION[1]
                    + cardStrings.EXTENDED_DESCRIPTION[2]
                    + cardStrings.DESCRIPTION;
        }
        else
        {
            return cardStrings.EXTENDED_DESCRIPTION[0]
                    + cardStrings.EXTENDED_DESCRIPTION[1]
                    + makeCardTextGray(cardStrings.EXTENDED_DESCRIPTION[2])
                    + cardStrings.DESCRIPTION;
        }
    }

    public static String makeCardTextGray(String input)
    {
        // This skips the replacement of "anniv5:" with "" that the AbstractEvenOddCard version does to avoid issues
        // with text formatting when using custom dynamic variables (e.g. !anniv5:m2!)
        return input.replaceAll("(\\s)((?!!|\\[E]|NL))"," " + EvenOddPack.GRAY + "$2");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                //Check "First"
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1) {
                    this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
                }

                this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
                this.addToTop(new ApplyPowerAction(p, p, new FrailPower(p, magicNumber, false)));
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}
