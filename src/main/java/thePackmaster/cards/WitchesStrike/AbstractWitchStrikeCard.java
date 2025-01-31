package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.ExpansionPacks;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractWitchStrikeCard extends AbstractPackmasterCard {
    public AbstractWitchStrikeCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "witchstrike");
    }

}
