package thePackmaster.patches._expansionpacks;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.relics.AbstractPackmasterRelic;

import java.util.HashMap;
import java.util.List;

public class RelicParentPackExpansionPatches {
    public static HashMap<String, List<String>> pmRelicParentExpansions = new HashMap<>();

    @SpirePatch2(clz = AbstractPackmasterRelic.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {String.class, AbstractRelic.RelicTier.class, AbstractRelic.LandingSound.class, boolean.class, String[].class})
    public static class ExpandParentPacks {
        @SpirePostfixPatch
        public static void patch(AbstractPackmasterRelic __instance) {
            if(pmRelicParentExpansions.containsKey(__instance.relicId)) {
                __instance.parentPacks.addAll(pmRelicParentExpansions.get(__instance.relicId));
            }
        }
    }
}
