package thePackmaster.actions.needlework;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cardmodifiers.needlework.StitchedMod;
import thePackmaster.patches.needlework.StitchPatches;
import thePackmaster.powers.needlework.OnStitchPower;

import java.util.ArrayList;
import java.util.List;

public class StitchAction extends AbstractGameAction {
    private final boolean triggerOnStitch;
    private AbstractCard toStitch, target;

    public StitchAction(AbstractCard toStitch)
    {
        this(toStitch, null);
    }
    public StitchAction(AbstractCard toStitch, AbstractCard target)
    {
        this(toStitch, target, true);
    }

    public StitchAction(AbstractCard toStitch, AbstractCard target, boolean triggerOnStitch) {
        this.toStitch = toStitch;
        this.target = target;
        this.triggerOnStitch = triggerOnStitch;
    }

    @Override
    public void update() {
        StitchedMod stitch = StitchPatches.StitchedField.stitch.get(toStitch);
        if (stitch != null) { //Already attached to a card
            this.isDone = true;
            return;
        }

        if (target == null) {
            if (AbstractDungeon.player.hand.isEmpty()) {
                this.isDone = true;
                return;
            }

            List<AbstractCard> goodOptions = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type != AbstractCard.CardType.STATUS && c.type != AbstractCard.CardType.CURSE && c.color != AbstractCard.CardColor.CURSE)
                    goodOptions.add(c);
            }

            if (goodOptions.isEmpty()) {
                target = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
            }
            else {
                target = goodOptions.get(AbstractDungeon.cardRandomRng.random(goodOptions.size() - 1));
            }
        }

        if (triggerOnStitch) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnStitchPower) {
                    ((OnStitchPower) p).onStitch(toStitch, target);
                }
            }
        }

        CardCrawlGame.sound.playA("MAP_SELECT_3", MathUtils.random(0.66f, 0.7f));
        CardModifierManager.addModifier(target, new StitchedMod(toStitch));
        toStitch.flashVfx = null;
        toStitch.stopGlowing();
        //StitchesGoNowherePatch.GoNowhereField.goNowhere.set(toStitch, true);
        this.isDone = true;
    }
}
