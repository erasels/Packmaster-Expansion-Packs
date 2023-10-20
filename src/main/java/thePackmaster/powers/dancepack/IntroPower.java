package thePackmaster.powers.dancepack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.actions.dancepack.IntroAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IntroPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(IntroPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public IntroPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("draw");
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[amount == 1 ? 0 : 1], amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new IntroAction(amount));
    }
}
