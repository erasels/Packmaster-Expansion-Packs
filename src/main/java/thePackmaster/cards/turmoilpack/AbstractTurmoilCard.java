package thePackmaster.cards.turmoilpack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractTurmoilCard extends AbstractPackmasterCard
{
    public AbstractTurmoilCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color)
    {
        super(cardID, cost, type, rarity, target, color, "spheres");
    }

    public AbstractTurmoilCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
