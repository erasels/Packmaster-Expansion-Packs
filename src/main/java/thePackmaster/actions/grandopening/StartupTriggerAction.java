package thePackmaster.actions.grandopening;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.util.Wiz.atb;

public class StartupTriggerAction extends AbstractGameAction {

    public StartupTriggerAction() {

    }

    @Override
    public void update() {
        Iterator<AbstractCard> var = Wiz.getAllCardsInCardGroups(true, false).iterator();
        AbstractCard c;
        while (var.hasNext()) {
            c = var.next();
            if (c instanceof StartupCard) {
                ((StartupCard) c).atBattleStartPreDraw();
                atb(new ShowCardAction(c));
            }
        }
        isDone = true;
    }
}
