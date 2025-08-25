package thePackmaster.actions.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.bladestormpack.EscalatingBreezePower;

//REFS: RetainCardsAction (base game), Preparation (replicatorspack), ConsumeToDoAction (fueledpack)
public class EscalatingBreezeAction extends AbstractGameAction {
    //Does not reduce the costs, the Power does.
    private static final String UI_KEY = SpireAnniversary5Mod.makeID("EscalatingBreezeUiText");
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final EscalatingBreezePower sourcePower;

    public EscalatingBreezeAction(AbstractCreature source, int amount, EscalatingBreezePower sourcePower) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.sourcePower = sourcePower;
    }

    //Based on RetainCardsAction (base game)
    public void update() {
        if (AbstractDungeon.player.hand.group.isEmpty()) {
            this.isDone = true;
            return;
        }
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
            this.addToBot(new WaitAction(0.25F));
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                for(AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    if (!c.isEthereal) {
                        c.retain = true;
                    }
                    AbstractDungeon.player.hand.addToTop(c);
                }
                sourcePower.retrieveChosenCards(AbstractDungeon.handCardSelectScreen.selectedCards.group);
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                this.isDone = true;
            }
            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(UI_KEY);
        TEXT = uiStrings.TEXT;
    }
}
