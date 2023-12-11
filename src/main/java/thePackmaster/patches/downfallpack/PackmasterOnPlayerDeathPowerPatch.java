package thePackmaster.patches.downfallpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

// This patch is based on OnPlayerDeathPatch from StsLib, but targets a different location so that it triggers before
// Fairy in a Bottle and Lizard Tail. See the comment for the PackmasterOnPlayerDeathPower for details on why.
// Note that this patch location assumes that this hook is being used for death prevention and that death prevention
// powers count as healing (and should be blocked by having Mark of the Bloom). It's possible that there's some other
// use case that would want to be treated differently, but that's not something we need to worry about for Packmaster.
// See the StsLib patch at https://github.com/kiooeht/StSLib/blob/master/src/main/java/com/evacipated/cardcrawl/mod/stslib/patches/bothInterfaces/OnPlayerDeathPatch.java
@SpirePatch(
        clz= AbstractPlayer.class,
        method="damage",
        paramtypez={DamageInfo.class}
)
public class PackmasterOnPlayerDeathPowerPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static SpireReturn<Void> Insert(AbstractPlayer __instance, DamageInfo info) {
        for (AbstractPower power : __instance.powers) {
            if (power instanceof PackmasterOnPlayerDeathPower) {
                if (!((PackmasterOnPlayerDeathPower) power).onPlayerDeath(__instance, info)) {
                    return SpireReturn.Return(null);
                }
            }
        }

        return SpireReturn.Continue();
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "hasPotion");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }
}
