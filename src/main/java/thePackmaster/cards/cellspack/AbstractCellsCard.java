package thePackmaster.cards.cellspack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractCellsCard extends AbstractPackmasterCard
{
    public AbstractCellsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "cells");
    }

    @Override
    public void upp() {
    }
}
