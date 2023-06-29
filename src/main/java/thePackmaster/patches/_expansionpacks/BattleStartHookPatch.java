package thePackmaster.patches._expansionpacks;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import thePackmaster.cards.colorlesspack.GolfBall;
import thePackmaster.patches.secretlevelpack.EnoughTalkPatch;
import thePackmaster.patches.secretlevelpack.SpecialCardGlowCheckPatch;

@SpirePatch2(clz= AbstractPlayer.class, method = "applyStartOfCombatPreDrawLogic")
public class BattleStartHookPatch {
    @SpirePrefixPatch
    public static void patch(AbstractPlayer __instance) {
        EnoughTalkPatch.spokeLastTurn = false;
        SpecialCardGlowCheckPatch.playedGlowingCardThisTurn = false;
        GolfBall.BLOCK_AMT_LOST = 0;
    }
}
