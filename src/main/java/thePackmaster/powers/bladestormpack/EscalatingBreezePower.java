package thePackmaster.powers.bladestormpack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

//REFS: RetainCardsAction (base game)
public class EscalatingBreezePower  extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("EscalatingBreezePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public EscalatingBreezePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        updateDescription();
        //If ever needed, set priority to avoid bugs, like "this.priority = -42068".
    }

    //Based on RetainCardsAction (base game)
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        //Retain all Attacks.
        if (AbstractDungeon.player.hand.group.isEmpty()) { return; }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                if (!c.isEthereal) {
                    c.retain = true;
                }
                //AbstractDungeon.player.hand.addToTop(c);  //Is this still useful?
            }
        }
        this.flashWithoutSound();

        if (amount <= 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (amount == 1) {
            this.description += this.amount + DESCRIPTIONS[1];
        } else {
            this.description += this.amount + DESCRIPTIONS[2];
        }
    }
}
