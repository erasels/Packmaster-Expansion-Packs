package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.actions.bladestormpack.TradeWindsAction;
import thePackmaster.util.creativitypack.JediUtil;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

/*REFS: AttackVial (womaninbluepack), StrikingStrike (creativitypack), Mimicry & Paintbrush (creativitypack),
RazorWind (dragonwrathpack), DualHeal (summonerspellspack), Maddii (Discord) */
public class TradeWinds extends AbstractBladeStormCard {
    public final static String ID = makeID("TradeWinds");
    private static final int COST = 0;
    private static final int HEAL_IF_COMMON = 4;
    private static final int GOLD_LOSS_IF_RARE = 5;


    public TradeWinds() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = HEAL_IF_COMMON;
        baseSecondMagic = secondMagic = GOLD_LOSS_IF_RARE;
        exhaust = true;

        tags.add(CardTags.HEALING);

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Based on StrikingStrike (strikepack).
        CardGroup cards = JediUtil.filterCardsForDiscovery((c) -> c.type == CardType.ATTACK && c.rarity != CardRarity.SPECIAL && c.rarity != CardRarity.BASIC);
        if (upgraded) {
            this.addToBot(new FlexibleDiscoveryAction(
                    (ArrayList<AbstractCard>) JediUtil.createCardsForDiscovery(cards).stream().peek(AbstractCard::upgrade).collect(Collectors.toList()),
                    (selectedCard) -> addToTop(new TradeWindsAction(p, magicNumber, secondMagic, selectedCard)), false
            ));
        } else {
            this.addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(cards), (selectedCard) -> addToTop(new TradeWindsAction(p, magicNumber, secondMagic, selectedCard)), false));
        }
    }

    //Upgrade is handled in use() by if(upgraded) before FlexibleDiscoveryAction().
}
