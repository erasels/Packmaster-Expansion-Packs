package thePackmaster.powers.intriguepack;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.actions.intriguepack.FastLoseHPAction;
import thePackmaster.cards.intriguepack.AbstractIntrigueCard;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RevolutionPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("RevolutionPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public RevolutionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, amount);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {

        if (AbstractIntrigueCard.isMundane(card))
        {
            this.flash();
            this.addToBot(new FastLoseHPAction(owner, (AbstractCreature) null, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] ;
    }
}