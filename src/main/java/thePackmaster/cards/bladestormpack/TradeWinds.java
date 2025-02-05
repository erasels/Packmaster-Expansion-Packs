package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.actions.bladestormpack.TradeWindsAction;
import thePackmaster.util.creativitypack.JediUtil;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

/*REFS: AttackVial (womaninbluepack), StrikingStrike (strikepack), Mimicry & Paintbrush (creativitypack),
RazorWind (dragonwrathpack), DualHeal (summonerspellspack) */
public class TradeWinds extends AbstractBladeStormCard {
    public final static String ID = makeID("TradeWinds");
    private static final int COST = 0;
    private static final int COMMON_HEAL = 2;
    private static final int GOLD_LOSS_IF_RARE = 5;

    public TradeWinds() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = COMMON_HEAL;
        baseSecondMagic = secondMagic = GOLD_LOSS_IF_RARE;
        exhaust = true;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Based on StrikingStrike (strikepack)
        CardGroup cards = JediUtil.filterCardsForDiscovery((c) -> c.type == CardType.ATTACK && c.rarity != CardRarity.SPECIAL && c.rarity != CardRarity.BASIC);
        if (upgraded) {
            this.addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(cards), (selectedCard) -> addToTop(new TradeWindsAction(p, magicNumber, secondMagic, selectedCard, true)), false));
        } else {
            this.addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(cards), (selectedCard) -> addToTop(new TradeWindsAction(p, magicNumber, secondMagic, selectedCard, false)), false));
        }
    }

    @Override
    public void upp() {
        //handled by if(upgraded) before FlexibleDiscoveryAction().

        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += magicNumber;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += secondMagic;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
    }
}
