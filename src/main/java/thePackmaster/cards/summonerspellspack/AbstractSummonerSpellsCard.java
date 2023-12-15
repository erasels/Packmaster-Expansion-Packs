package thePackmaster.cards.summonerspellspack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSummonerSpellsCard extends AbstractPackmasterCard
{
    public AbstractSummonerSpellsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "summonerspells");
    }
    public AbstractSummonerSpellsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, "summonerspells");
    }

    @Override
    public void upp() {
    }
}
