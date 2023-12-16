package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class TributeOrAusterityAction extends AbstractGameAction {
    private ArrayList<AbstractCard> choices;

    public TributeOrAusterityAction(AbstractCard tribute, AbstractCard austerity) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.choices = new ArrayList<AbstractCard>();
        choices.add(tribute);
        choices.add(austerity);
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            Wiz.atb(new ChooseOneAction(this.choices));
            tickDuration();
            return;
        }
        tickDuration();
    }
}
