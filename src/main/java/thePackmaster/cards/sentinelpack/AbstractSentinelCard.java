package thePackmaster.cards.sentinelpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSentinelCard extends AbstractPackmasterCard {
    public AbstractSentinelCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "sentinel");
    }


    @Override
    public void upp() {
    }
}
