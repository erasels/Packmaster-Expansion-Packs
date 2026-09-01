package thePackmaster.cards.stancedancepack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractStanceDanceCard extends AbstractPackmasterCard
{
    public AbstractStanceDanceCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "stancedance");
    }

    @Override
    public void upp() {
    }
}
