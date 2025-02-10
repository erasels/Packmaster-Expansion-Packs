package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.bladestormpack.FeedingWindsAction;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.bladestormpack.GainStrengthModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

/*REFS: Transmogrifier (pickthemall), Master's Strike (upgradespack), Fuel & ConsumeToDoAction (fueledpack)
Winterize & FrostOrbModifier & FindCardForAddModifierAction (madsciencepack)*/
public class FeedingWinds extends AbstractBladeStormCard implements StartupCard {
    public final static String ID = makeID("FeedingWinds");
    private static final int COST = 0;
    private static final int CARDS_TO_EXHAUST = 1;
    private static final int ENERGY_GAIN = 1;
    private static final int WINDRUSH = 3;
    private static final int UPG_WINDRUSH = 1;
    private static final int STRENGTH_GAIN = 1;
    private static final int STRENGTH_CARDS = 2;
    private static final int UPG_STRENGTH_CARDS = 1;

    public FeedingWinds() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = STRENGTH_CARDS;
        baseSecondMagic = secondMagic = WINDRUSH;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        this.addToBot(
            new FindCardForAddModifierAction(
                new GainStrengthModifier(STRENGTH_GAIN), this.magicNumber, true,
                AbstractDungeon.player.drawPile, (card) -> card.type == CardType.ATTACK
            )
        );
        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new FeedingWindsAction(CARDS_TO_EXHAUST, secondMagic,
                new AbstractGameAction() {
                @Override
                public void update() {
                    att(new GainEnergyAction(ENERGY_GAIN));
                    isDone = true;
                }
        }
        ));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_STRENGTH_CARDS);
        upgradeSecondMagic(UPG_WINDRUSH);
    }
}
