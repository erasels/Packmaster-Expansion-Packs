package thePackmaster.actions.royaltypack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.util.List;

import static thePackmaster.util.Wiz.adp;

public class RetainerStrikeAction extends AbstractGameAction {

    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(
            SpireAnniversary5Mod.makeID("RetainerStrikeAction")).TEXT;

    private static AbstractCard usedCard;

    public RetainerStrikeAction(AbstractCard card) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
        this.usedCard = card;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hand.isEmpty()) {
            this.isDone = true;
        } else{
            CardGroup hand = Wiz.p().hand;
            CardGroup handWithoutRetain = new CardGroup(CardGroup.CardGroupType.HAND);
            for (AbstractCard c: hand.group){
                if (!c.retain && c != usedCard) handWithoutRetain.addToHand(c);
            }
            if (handWithoutRetain.size() == 0){
                this.isDone = true;
            }
            else if (handWithoutRetain.size() == 1){
                doActionWithOneCardAtHand();
            } else {
                Wiz.atb(new SelectCardsAction(handWithoutRetain.group, TEXT[0],
                        (List<AbstractCard> cards) -> {
                            AbstractCard card = cards.get(0);
                            card.retain = true;
                            adp().hand.addToBottom(card);
                            card.flash();
                            this.isDone = true;
                        }
                ));
            }
        }
    }

    private void doActionWithOneCardAtHand(){
        AbstractCard card = AbstractDungeon.player.hand.getBottomCard();
        card.retain = true;
        adp().hand.addToTop(card);
        card.flash();
        this.isDone = true;
    }
}
