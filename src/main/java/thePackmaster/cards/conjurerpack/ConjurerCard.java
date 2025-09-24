package thePackmaster.cards.conjurerpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class ConjurerCard extends AbstractPackmasterCard {
    public ConjurerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "conjurer");
    }

    public static String makeID(Class<?> className) {
        return SpireAnniversary5Mod.makeID(className.getSimpleName());
    }
}
