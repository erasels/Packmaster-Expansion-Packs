package thePackmaster.powers.bladestormpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class GaleForcePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GaleForcePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private final int EXTRA_WINDRUSH;
    private final int CARDS_TO_DRAW;
    private final int DRAW_COST_THRESHOLD;

    public GaleForcePower(AbstractCreature owner, int amount, int extraWindrush, int draw, int threshold) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        EXTRA_WINDRUSH = extraWindrush;
        CARDS_TO_DRAW = draw;
        DRAW_COST_THRESHOLD = threshold;
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (amount <= 0 || c.type != AbstractCard.CardType.ATTACK) { return; }

        this.flashWithoutSound();
        atb(new ApplyPowerAction(player, player, new WindrushPower(player, (c.cost + EXTRA_WINDRUSH) * amount)));
        if (c.cost >= DRAW_COST_THRESHOLD) {
            addToBot(new DrawCardAction(CARDS_TO_DRAW * amount));
        }
    }

    @Override
    public void updateDescription() {
        if (CARDS_TO_DRAW == 1) {
            this.description = DESCRIPTIONS[0]
            + this.amount * EXTRA_WINDRUSH + DESCRIPTIONS[1]
            + this.amount * CARDS_TO_DRAW + DESCRIPTIONS[2]
            + DRAW_COST_THRESHOLD + DESCRIPTIONS[4]
            + DESCRIPTIONS[5];
        } else {
            this.description = DESCRIPTIONS[0]
            + this.amount * EXTRA_WINDRUSH + DESCRIPTIONS[1]
            + this.amount * CARDS_TO_DRAW + DESCRIPTIONS[3]
            + DRAW_COST_THRESHOLD + DESCRIPTIONS[4]
            + DESCRIPTIONS[5];
        }
    }
}
