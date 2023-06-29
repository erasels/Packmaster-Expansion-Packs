package thePackmaster.patches._expansionpacks;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CtBehavior;
import thePackmaster.patches.secretlevelpack.SpecialCardGlowCheckPatch;

@SpirePatch(
        clz = GameActionManager.class,
        method = "getNextAction"
)
public class GlobalTurnStartCompatibilityPatches {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(GameActionManager __instance) {
        SpecialCardGlowCheckPatch.playedGlowingCardThisTurn = false;
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnRelics");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}