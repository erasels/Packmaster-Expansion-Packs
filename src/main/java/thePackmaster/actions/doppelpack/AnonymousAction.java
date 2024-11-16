package thePackmaster.actions.doppelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class AnonymousAction extends AbstractGameAction {

    private final Runnable callback;

    public AnonymousAction(Runnable callback) {
        this.callback = callback;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        this.callback.run();
        this.isDone = true;
    }
}
