package thePackmaster.actions.grandopening;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

public class StartupTriggerAction extends AbstractGameAction {

    public StartupTriggerAction() {

    }

    @Override
    public void update() {
        ArrayList<Iterator<AbstractCard>> vars = new ArrayList();
        vars.add(AbstractDungeon.player.hand.group.iterator());
        vars.add(AbstractDungeon.player.drawPile.group.iterator());
        vars.add(AbstractDungeon.player.discardPile.group.iterator());
        AbstractCard c;
        for(Iterator<AbstractCard> var : vars) {
            while (var.hasNext()) {
                c = var.next();
                if (c instanceof StartupCard) {
                    ((StartupCard) c).atBattleStartPreDraw();
                }
            }
        }
        isDone = true;
    }
}
