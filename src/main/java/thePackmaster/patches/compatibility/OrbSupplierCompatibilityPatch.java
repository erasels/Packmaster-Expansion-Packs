package thePackmaster.patches.compatibility;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.packs.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class OrbSupplierCompatibilityPatch {
    @SpirePatch2(clz = PackmasterOrb.class, method = "makePackSuppliers")
    public static class AddExpansionSuppliers {
        @SpirePostfixPatch
        public static Map<String, List<Supplier<AbstractOrb>>> patch(Map<String, List<Supplier<AbstractOrb>>> __result) {
            List<Supplier<AbstractOrb>> suppliers;

            suppliers = new ArrayList<>();
            suppliers.add(Arcane::new);
            __result.put(WitchesStrikePack.ID, suppliers);

            suppliers = new ArrayList<>();
            suppliers.add(thePackmaster.orbs.downfallpack.Ghostflame::new);
            __result.put(DownfallPack.ID, suppliers);

            suppliers = new ArrayList<>();
            suppliers.add(thePackmaster.orbs.spherespack.Blaze::new);
            suppliers.add(thePackmaster.orbs.spherespack.Polar::new);
            __result.put(SpheresPack.ID, suppliers);

            suppliers = new ArrayList<>();
            suppliers.add(thePackmaster.orbs.Oblivion::new);
            __result.put(EntropyPack.ID, suppliers);

            suppliers = new ArrayList<>();
            suppliers.add(thePackmaster.orbs.runicpack.Glyph::new);
            __result.put(RunicPack.ID, suppliers);

            return __result;
        }
    }
}
