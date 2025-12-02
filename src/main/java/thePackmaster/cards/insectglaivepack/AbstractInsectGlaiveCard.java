package thePackmaster.cards.insectglaivepack;

import basemod.helpers.CardModifierManager;
import thePackmaster.ThePackmaster;
import thePackmaster.cardmodifiers.insectglaivepack.InsectGlaiveTooltipsGlow;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractInsectGlaiveCard extends AbstractPackmasterCard {
    public AbstractInsectGlaiveCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    public AbstractInsectGlaiveCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "insectglaive");

        CardModifierManager.addModifier(this, new InsectGlaiveTooltipsGlow());
    }
}
