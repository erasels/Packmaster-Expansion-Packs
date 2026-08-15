package thePackmaster.cards.fullthrottlepack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractFullThrottleCard extends AbstractPackmasterCard
{
    public AbstractFullThrottleCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "fullthrottle");

    }

    public AbstractFullThrottleCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }
}
