package thePackmaster.powers.witchesstrikepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlowoftheSwordPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("FlowoftheSwordPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static int activations = 0;

    public FlowoftheSwordPower(AbstractCreature owner, int amt) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amt);
        canGoNegative = false;
        activations = 0;
    }

    public void atStartOfTurnPostDraw() {
        activations = 0;
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.SKILL && activations < amount) {
            Wiz.atb(new DrawCardAction(1));
            activations++;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
        if (amount > 1) {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
