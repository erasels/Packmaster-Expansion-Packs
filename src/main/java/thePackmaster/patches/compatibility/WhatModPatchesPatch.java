package thePackmaster.patches.compatibility;

import com.evacipated.cardcrawl.modthespire.lib.*;
import thePackmaster.ExpansionPacks;

import java.util.Objects;

public class WhatModPatchesPatch {
    @SpirePatch2(
            clz = WhatModPatches.Cards.class,
            method = "shouldAdd"
    )
    public static class Cards {
        @SpirePostfixPatch
        public static boolean AddPackName(boolean __result, String modId) {
            return __result || Objects.equals(ExpansionPacks.modID, modId);
        }
    }
}
