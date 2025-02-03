package thePackmaster.cards.bladestormpack;

import thePackmaster.cards.AbstractPackmasterCard;

//REFS: AbstractGrandOpeningCard (grandopeningpack), AbstractEvenOddCard (evenoddpack)
public abstract class AbstractBladeStormCard extends AbstractPackmasterCard
{
    public AbstractBladeStormCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "bladestorm");

    }

    @Override
    public void upp() {
    }
}
