package thePackmaster.powers.witchesstrikepack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.cards.WitchesStrike.Bullet;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightFlightPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("MoonlightFlightPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public MoonlightFlightPower(AbstractCreature owner, int amt) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,amt);
        canGoNegative = false;
    }
    @Override
    public void atStartOfTurnPostDraw() {
        Wiz.atb(new MakeTempCardInHandAction(new Bullet(),amount));
    }
    @Override
    public void updateDescription() {
        if (amount < 2){
            description = DESCRIPTIONS[0];
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}

