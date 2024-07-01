package thePackmaster.patches._expansionpacks;

import basemod.patches.whatmod.WhatMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import thePackmaster.ExpansionPacks;

@SpirePatch2(clz = WhatMod.class, method = "findModName")
public class WhatModShortenNamePatch {
    @SpirePostfixPatch
    public static String patch(String __result) {
        if (ExpansionPacks.FULL_MOD_NAME.equals(__result)) {
            return ExpansionPacks.SHORTENED_MOD_NAME;
        }
        return __result;
    }
}
