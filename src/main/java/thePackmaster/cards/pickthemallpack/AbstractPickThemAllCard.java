package thePackmaster.cards.pickthemallpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractPickThemAllCard extends AbstractPackmasterCard
{
    public AbstractPickThemAllCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "utility");
    }

    @Override
    public void upp() {
    }
}
