package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;

//Yes, this is a modified version of Mayhem's WorkersStrikeAction :p
public class DrawSpecificCardTypeAction extends AbstractGameAction {

    private AbstractCard.CardType cardType;

    public DrawSpecificCardTypeAction(AbstractCard.CardType cardType) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.DRAW;
        this.cardType = cardType;
    }

    public void update() {
        ArrayList<AbstractCard> potentialTargets = new ArrayList<>(AbstractDungeon.player.drawPile.group);
        ArrayList<AbstractCard> validTargets = new ArrayList<>();

        boolean hasStrike = false;

        Collections.shuffle(potentialTargets, AbstractDungeon.cardRandomRng.random);

        for (AbstractCard c: potentialTargets) {
            if (c.type == cardType){
                validTargets.add(c);

                if (validTargets.size() >= 1){
                    break;
                }
            }
        }

        for (AbstractCard c: validTargets){
            if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                AbstractDungeon.player.createHandIsFullDialog();
            } else {
                c.unhover();
                c.lighten(true);
                c.setAngle(0.0F);
                c.drawScale = 0.12F;
                c.targetDrawScale = 0.75F;
                c.current_x = CardGroup.DRAW_PILE_X;
                c.current_y = CardGroup.DRAW_PILE_Y;
                AbstractDungeon.player.drawPile.removeCard(c);
                AbstractDungeon.player.hand.addToTop(c);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
            }
        }

        this.isDone = true;
    }

}
