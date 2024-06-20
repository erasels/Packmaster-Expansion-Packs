package thePackmaster.cards.infestpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractInfestCard extends AbstractPackmasterCard {
    public AbstractInfestCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "infest");

    }
}
