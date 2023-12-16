package thePackmaster.powers.royaltypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NobleFormPower extends AbstractPackmasterPower implements DiscardHookPatch.OnDiscardThing {

    public static final String POWER_ID = makeID("NobleFormPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private final int BASE_CARDS_TO_DRAW = 1;
    private final int BASE_GOLD_TO_GAIN = 5;

    public NobleFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,1);

    }

    public void updateDescription() {
        if (amount == 1){
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + DESCRIPTIONS[3] + BASE_GOLD_TO_GAIN + DESCRIPTIONS[4];
        }
        else {
            this.description = DESCRIPTIONS[0] + amount * BASE_CARDS_TO_DRAW + DESCRIPTIONS[2] +
                    DESCRIPTIONS[3] + amount * BASE_GOLD_TO_GAIN + DESCRIPTIONS[4];
        }
    }

    @Override
    public void onManualDiscardThing() {
        Wiz.atb(new DrawCardAction(amount * BASE_CARDS_TO_DRAW));
    }

    @Override
    public int onLoseHp(int damageAmount) {
        Wiz.atb(new GainGoldAction(amount * BASE_GOLD_TO_GAIN));
        return damageAmount;
    }

}
