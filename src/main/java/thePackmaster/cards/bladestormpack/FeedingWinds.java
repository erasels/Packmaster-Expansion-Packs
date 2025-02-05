package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.bladestormpack.GainStrengthModifier;
import thePackmaster.powers.bladestormpack.WindrushPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

/*REFS: Transmogrifier (pickthemall), Master's Strike (upgradespack),
Winterize & FrostOrbModifier & FindCardForAddModifierAction (madsciencepack)*/
public class FeedingWinds extends AbstractBladeStormCard implements StartupCard {
    public final static String ID = makeID("FeedingWinds");
    private static final int COST = 1;
    private static final int WINDRUSH_PER_ATTACK = 1;
    private static final int STRENGTH_GAIN = 1;
    private static final int STRENGTH_CARDS = 2;
    private static final int UPG_STRENGTH_CARDS = 1;

    public FeedingWinds() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = STRENGTH_CARDS;
        baseSecondMagic = secondMagic = WINDRUSH_PER_ATTACK;

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
        int attacksInHand = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.ATTACK) {
                attacksInHand += secondMagic;
            }
        }
        if (attacksInHand > 0) {
            atb(new ApplyPowerAction(p, p, new WindrushPower(p, attacksInHand)));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_STRENGTH_CARDS);
    }
}
