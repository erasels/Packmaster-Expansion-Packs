package thePackmaster.actions.discopack;


import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class SpecificToHandFromDiscardAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard card;

    public SpecificToHandFromDiscardAction( AbstractCard card) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
    }

    public void update() {
        if (this.p.hand.size() >= BaseMod.MAX_HAND_SIZE) {
            this.isDone = true;
        }
        else{
            this.p.hand.addToHand(card);
            this.p.discardPile.removeCard(card);
            card.lighten(false);
            this.p.hand.refreshHandLayout();
            this.isDone = true;
        }

    }
}

