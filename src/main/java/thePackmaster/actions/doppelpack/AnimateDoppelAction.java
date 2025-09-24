package thePackmaster.actions.doppelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import thePackmaster.orbs.doppelpack.AbstractDoppel;

public class AnimateDoppelAction extends AbstractGameAction {
    private final AbstractDoppel doppel;

    public AnimateDoppelAction(AbstractDoppel doppel, float duration) {
        this.doppel = doppel;
        this.duration = duration;
    }

    @Override
    public void update() {
        this.doppel.animationTimer = this.duration;
        this.isDone = true;
    }
}
