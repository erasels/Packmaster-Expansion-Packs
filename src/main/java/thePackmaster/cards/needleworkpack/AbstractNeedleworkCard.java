package thePackmaster.cards.needleworkpack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractNeedleworkCard extends AbstractPackmasterCard {
    public AbstractNeedleworkCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, (String) null, null);
    }

    public AbstractNeedleworkCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
    }
}
