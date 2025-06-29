package thePackmaster.cards.royaltypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractRoyaltyCard extends AbstractPackmasterCard {

    public AbstractRoyaltyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "royalty");

    }

    public AbstractRoyaltyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "royalty");
    }

    public AbstractRoyaltyCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color, final String textureString) {
        super(cardID, cost, type, rarity, target, color, textureString, "royalty", null);
    }

}
