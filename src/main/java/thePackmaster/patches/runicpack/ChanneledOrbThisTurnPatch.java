package thePackmaster.patches.runicpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import thePackmaster.packs.RunicPack;

public class ChanneledOrbThisTurnPatch {
    @SpirePatch(
            clz = ChannelAction.class,
            method = "update"
    )
    public static class OnChannelOrb {
        @SpirePrefixPatch
        public static void Prefix(ChannelAction __instance) {
            RunicPack.channeledOrbThisTurn = true;
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnPostDrawRelics"
    )
    public static class ResetChanneledOrb {
        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance) {
            RunicPack.channeledOrbThisTurn = false;
        }
    }
}