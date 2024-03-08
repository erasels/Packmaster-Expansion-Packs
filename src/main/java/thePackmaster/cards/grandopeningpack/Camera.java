package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.actions.grandopening.CameraAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardColor.COLORLESS;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.evenoddpack.AbstractEvenOddCard.makeCardTextGray;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class Camera extends AbstractPackmasterCard {
    public final static String ID = makeID("Camera");

    public Camera() {
        super(ID, -1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, COLORLESS);
        baseBlock = block = 5;
        cardsToPreview = new Action();
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
        atb(new ReinforcedBodyAction(p, block, freeToPlayOnce, energyOnUse));
        Wiz.atb(new EasyXCostAction(this,(effect, params) -> {
            atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DrawCardNextTurnPower(AbstractDungeon.player, effect)));
            return true;
        }));
        atb(new AbstractGameAction() {
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
        upgradeBlock(3);
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }
}