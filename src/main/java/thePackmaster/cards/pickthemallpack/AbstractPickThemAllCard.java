package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractPickThemAllCard extends AbstractPackmasterCard implements OnCreateCardInterface
{
    public boolean combatVersion;

    public AbstractPickThemAllCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "utility");
        this.combatVersion = false;
        this.rawDescription = cardStrings.DESCRIPTION + this.getPickupDescription();
        this.initializeDescription();
    }

    @Override
    public void upp() {
    }

    @Override
    public final void uDesc() {
        this.rawDescription = this.combatVersion ? this.getDescriptionForCombatUpgraded() : cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    @Override
    public void onCreateCard(AbstractCard c) {
        if(c == this)
            resetDescriptionForCombat();
    }

    public String getPickupDescription() {
        return cardStrings.EXTENDED_DESCRIPTION[0];
    }

    public final void resetDescriptionForCombat() {
        this.combatVersion = true;
        this.rawDescription = this.upgraded ? this.getDescriptionForCombatUpgraded() : this.getDescriptionForCombat();
        this.initializeDescription();
        this.cardsToPreview = null; //Because all card previews in this pack are for pickup effects
    }

    protected String getDescriptionForCombat() {
        return cardStrings.DESCRIPTION;
    }

    protected String getDescriptionForCombatUpgraded() {
        return cardStrings.UPGRADE_DESCRIPTION != null ? cardStrings.UPGRADE_DESCRIPTION : getDescriptionForCombat();
    }
}
