package thePackmaster.actions.dancepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.dancepack.IntroPower;
import thePackmaster.util.Wiz;

public class IntroAction extends AbstractGameAction {

    public IntroAction(int amt)
    {
        amount = amt;
    }

    @Override
    public void update() {
        isDone = true;
        AbstractPower intro = Wiz.p().getPower(IntroPower.POWER_ID);
        if (Wiz.p().hand.group.stream().noneMatch(c -> c.type == AbstractCard.CardType.SKILL)) {
            AbstractDungeon.actionManager.addToTop(new DrawCallbackShuffleAction(amount, c -> c.type == AbstractCard.CardType.SKILL));
            if (intro != null) intro.flash();
        }
        if (Wiz.p().hand.group.stream().noneMatch(c -> c.type == AbstractCard.CardType.ATTACK)) {
            AbstractDungeon.actionManager.addToTop(new DrawCallbackShuffleAction(amount, c -> c.type == AbstractCard.CardType.ATTACK));
            if (intro != null) intro.flash();
        }
    }
}
