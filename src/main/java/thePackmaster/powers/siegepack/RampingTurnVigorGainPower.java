package thePackmaster.powers.siegepack;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.siegepack.ThinkTwice;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.Objects;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//REFS: EventHorizon (marisapack), GladiatorForm (Downfall)
public class RampingTurnVigorGainPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(RampingTurnVigorGainPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int increaseFix;

    public RampingTurnVigorGainPower(AbstractCreature owner, int amount, int increase) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        this.amount = amount;
        this.amount2 = increase;
        this.increaseFix = increase;

        this.priority = 8000; // EventHorizonPower priority should be 5 by default.
        // This should trigger after it, but the increase from Horizon triggering only benefits the next turn.

        updateDescription();
    }

    public void atStartOfTurn(){
        Wiz.applyToSelf(new VigorPower(player, amount));
        this.flash();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (Objects.equals(power.ID, RampingTurnVigorGainPower.POWER_ID)) {
            amount2 += increaseFix;
        }
        if (!Objects.equals(power.ID, VigorPower.POWER_ID)) { return; }

        amount += amount2;
        updateDescription();
    }

    //Based on GladiatorFormPower (Downfall)
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        //There are no Attacks in PM or base game that don't consume Vigor.
        if ( (card.type != AbstractCard.CardType.ATTACK && !(card instanceof ThinkTwice))
                || !owner.hasPower(VigorPower.POWER_ID)) {
            return;
        }
        amount += amount2;
        updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]
            + this.amount + DESCRIPTIONS[1]
            + this.amount2 + DESCRIPTIONS[2];
    }
}
