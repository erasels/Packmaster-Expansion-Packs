package thePackmaster.patches.insectglaivepack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.insectglaivepack.KinsectHarvestExtract;

public class DrawCountAndHarvestExtractPatch {
    public static int drawCount = 0;

    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfCombatLogic")
    public static class AbstractPlayer_applyStartOfCombatLogic {
        @SpirePostfixPatch
        public static void postfix(AbstractPlayer inst) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    drawCount = 0;
                    this.isDone = true;
                }
            });
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnPostDrawRelics")
    public static class AbstractPlayer_applyStartOfTurnPostDrawRelics {
        @SpirePostfixPatch
        public static void postfix(AbstractPlayer inst) {

            KinsectHarvestExtract.atTurnStartOwn();

            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    drawCount = 0;
                    this.isDone = true;
                }
            });
        }
    }

    @SpirePatch(clz = DrawCardAction.class, method = "endActionWithFollowUp")
    public static class AbstractPlayer_endActionWithFollowUp {
        @SpirePrefixPatch
        public static void prefix(DrawCardAction inst) {
            drawCount += DrawCardAction.drawnCards.size();
        }
    }
}
