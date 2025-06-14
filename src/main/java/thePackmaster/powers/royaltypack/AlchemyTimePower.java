package thePackmaster.powers.royaltypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AlchemyTimePower extends AbstractPackmasterPower implements OnPotionUsePower {

    public static final String POWER_ID = makeID("AlchemyTimePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public AlchemyTimePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void onPotionUse() {
        Wiz.atb(new DrawCardAction(amount));
    }

    public void updateDescription() {
        if (amount == 1) this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        else this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
    }

}
