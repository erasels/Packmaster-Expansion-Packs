package thePackmaster.powers.fullthrottlepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fullthrottlepack.ApplyRandomNitroAction;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class AfterburnerPower extends AbstractPackmasterPower implements AddToHandPatches.AddToHandInterface {

    public static final String POWER_ID = makeID("AfterburnerPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public AfterburnerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onAddCard(CardGroup group, AbstractCard card) {
        atb(new ApplyRandomNitroAction(amount, AbstractDungeon.player.drawPile));
        this.flash();
    }

    @Override
    public void updateDescription() {
        String plural = "";
        if (amount > 1) plural = DESCRIPTIONS[1];
        description = String.format(DESCRIPTIONS[0], amount, plural);
    }
}
