package thePackmaster.powers.intriguepack;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class ApexPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ApexPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ApexPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, -1);
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return (card.rarity == AbstractCard.CardRarity.RARE || card.rarity == AbstractCard.CardRarity.UNCOMMON);
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer)
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
