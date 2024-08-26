package thePackmaster.cards.siegepack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSiegeCard extends AbstractPackmasterCard
{
    public AbstractSiegeCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "siege");

    }
    @Override
    public void upp() {
    }
}
