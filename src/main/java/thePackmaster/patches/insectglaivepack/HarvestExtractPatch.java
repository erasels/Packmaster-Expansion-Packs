package thePackmaster.patches.insectglaivepack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.insectglaivepack.KinsectHarvestExtract;

public class HarvestExtractPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnPostDrawRelics")
    public static class AbstractPlayer_applyStartOfTurnPostDrawRelics {
        @SpirePostfixPatch
        public static void postfix(AbstractPlayer inst) {
            KinsectHarvestExtract.atTurnStartOwn();
        }
    }
}
