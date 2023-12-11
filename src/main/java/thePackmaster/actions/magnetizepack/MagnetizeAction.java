package thePackmaster.actions.magnetizepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.util.Wiz;

public class MagnetizeAction extends AbstractGameAction {
    private final AbstractCard card;

    public MagnetizeAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        AbstractCardModifier newMagnetize = new MagnetizedModifier(false);
        if (newMagnetize.shouldApply(card)) {
            card.superFlash();
            CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
            CardModifierManager.addModifier(card, newMagnetize);
            for (AbstractPower pow: AbstractDungeon.player.powers) {
                if (pow instanceof MagnetizeListener)
                    ((MagnetizeListener) pow).onMagnetize(card);
            }
        }

        isDone = true;
    }

    public interface MagnetizeListener {
        public void onMagnetize(AbstractCard c);
    }
}
