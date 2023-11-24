package thePackmaster.cards.intriguepack;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.util.creativitypack.JediUtil;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Favor extends AbstractIntrigueCard {
    public final static String ID = makeID("Favor");

    public Favor() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // Wow, so much easier once I found this lmao.
        CardGroup cards = JediUtil.filterCardsForDiscovery(c -> !c.hasTag(CardTags.HEALING) && c.rarity == CardRarity.RARE);

        addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(cards), selectedCard -> {
            CardModifierManager.addModifier(selectedCard, new EtherealMod());
            CardModifierManager.addModifier(selectedCard, new ExhaustMod());
        },
                true));
    }

    public void upp() {
        this.exhaust = false;
    }
}
