package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractIntrigueCard extends AbstractPackmasterCard {
    public AbstractIntrigueCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "intrigue");

    }

    public AbstractIntrigueCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "intrigue");

    }

    // Not rare, nor uncommon.
    public static boolean isMundane(AbstractCard c) {
        if (c.rarity != CardRarity.RARE && c.rarity != CardRarity.UNCOMMON)
            return true;
        return false;
    }

    @Override
    public void upp() {
    }
}
