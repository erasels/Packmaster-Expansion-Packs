package thePackmaster.cards.runicpack;

import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractRunicCard extends AbstractPackmasterCard
{
    public AbstractRunicCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor col) {
        super(cardID, cost, type, rarity, target, col, "sentinel");

    }

    public AbstractRunicCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

}
