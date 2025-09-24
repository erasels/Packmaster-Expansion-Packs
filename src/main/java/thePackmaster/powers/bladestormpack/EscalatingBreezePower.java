package thePackmaster.powers.bladestormpack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.bladestormpack.EscalatingBreezeAction;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;

//REFS: RummageDrawAction (packmaster), RunedMithrilRod (warlockpack), RetainCardsAction (base game)
public class EscalatingBreezePower  extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("EscalatingBreezePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private ArrayList<AbstractCard> CARDS_TO_REDUCE = null;

    public EscalatingBreezePower(AbstractCreature owner, int amount, int costReduction) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        this.amount2 = costReduction;
        updateDescription();
        //If ever needed, set priority to avoid bugs, like "this.priority = -42068".
    }

    //Based on RetainCardsAction (base game)
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        //Choose and retain the Attacks that will have their cost reduced (at the start of next turn).
        addToBot(new EscalatingBreezeAction(player, amount, this)); //Makes the list of cards.
    }

    @Override
    public void atStartOfTurnPostDraw() {
        //Do the cost reductions on cards previously chosen at end of turn.
        if (CARDS_TO_REDUCE == null || CARDS_TO_REDUCE.isEmpty()) {     //No cards were chosen.
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            return;
        }

        this.flashWithoutSound();
        for (AbstractCard c : CARDS_TO_REDUCE) {
            if (c == null || !AbstractDungeon.player.hand.group.contains(c) || c.type != AbstractCard.CardType.ATTACK) {
                continue;
            }

            //Based on RunedMithrilRod (warlockpack)
            if (c.cost >= 0 && c.costForTurn > 0) {
                c.setCostForTurn(Math.max(0, c.costForTurn - this.amount2));
                c.isCostModifiedForTurn = true;
                c.superFlash();
            }
        }
        AbstractDungeon.player.hand.refreshHandLayout();    //Purpose unknown, recommended by Enbeon.
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    public void retrieveChosenCards (ArrayList<AbstractCard> cardsArray) {
        CARDS_TO_REDUCE = cardsArray;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (amount == 1) {
            this.description += this.amount + DESCRIPTIONS[1];
        } else {
            this.description += this.amount + DESCRIPTIONS[2];
        }
        this.description += DESCRIPTIONS[3];
    }
}
