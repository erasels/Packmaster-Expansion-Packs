package thePackmaster.cards.bladestormpack;

import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.EvenOddPack;

//REFS: AbstractGrandOpeningCard (grandopening)
public abstract class AbstractBladeStormCard extends AbstractPackmasterCard
{
    public AbstractBladeStormCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "bladestorm");

    }

    @Override
    public void upp() {
    }

    public static String makeCardTextGray(String input) {
        // This skips the replacement of "anniv5:" with "" that the AbstractEvenOddCard version does to avoid issues
        // with text formatting when using custom dynamic variables (e.g. !anniv5:m2!)
        return input.replaceAll("(\\s)((?!!|\\[E]|NL))", " " + EvenOddPack.GRAY + "$2");
    }
}
