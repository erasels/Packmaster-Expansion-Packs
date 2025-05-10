package thePackmaster.cards.bladestormpack;

import thePackmaster.cards.AbstractPackmasterCard;

/*REFS: AbstractGrandOpeningCard (grandopeningpack), AbstractEvenOddCard (evenoddpack),
AbstractPickThemAllCard (pickthemallpack),*/
public abstract class AbstractBladeStormCard extends AbstractPackmasterCard
{
    public AbstractBladeStormCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "bladestorm");

    }

    @Override
    public void upp() {

    }

    //Entry point for removing Startup tooltips: the patch only calls this.
    public void resetDescriptionForCombat() {
        //Override it because each card may use Cardstrings.json differently, unlike in PickThemAll pack.
    }
}
