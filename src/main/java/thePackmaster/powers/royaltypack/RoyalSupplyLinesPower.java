package thePackmaster.powers.royaltypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class RoyalSupplyLinesPower extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("RoyalSupplyLinesPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private final int BASE_CARDS_TO_DRAW = 2;
    private final int BASE_CARDS_TO_RETAIN = 2;

    public RoyalSupplyLinesPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, 1);

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount * BASE_CARDS_TO_DRAW + DESCRIPTIONS[1];
        this.description += DESCRIPTIONS[2] + this.amount * BASE_CARDS_TO_RETAIN + DESCRIPTIONS[3];
    }

    @Override
    public void atStartOfTurn() {
        atb(new DrawCardAction(this.amount * BASE_CARDS_TO_DRAW));
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            if (AbstractDungeon.player.hand.size() < this.amount * BASE_CARDS_TO_RETAIN) {
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    card.retain = true;
                    card.flash();
                }
            } else {
                Wiz.atb(new RetainCardsAction(AbstractDungeon.player, this.amount * BASE_CARDS_TO_RETAIN));
            }

        }
    }

}
