package thePackmaster.cards.intothebreachpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class IntoTheBreachCard extends AbstractPackmasterCard {
    public IntoTheBreachCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "intothebreach");

    }
}
