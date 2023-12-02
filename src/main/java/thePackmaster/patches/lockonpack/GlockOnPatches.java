package thePackmaster.patches.lockonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import javassist.CtBehavior;
import thePackmaster.powers.lockonpack.GlockOnPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;


public class GlockOnPatches {
    @SpirePatch2(clz = AbstractCard.class, method = "calculateCardDamage")
    public static class SingleTargetPatch {
        @SpireInsertPatch(locator = Locator.class, localvars = "tmp")
        public static void patch(AbstractMonster mo, @ByRef int[] tmp) {
            GlockOnPower pow = (GlockOnPower) Wiz.p().getPower(GlockOnPower.POWER_ID);
            if (pow != null) {
                tmp[0] = (int) pow.atDamageGiveAgainstSpecificFoe(tmp[0], mo);
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractStance.class, "atDamageGive");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }

    @SpirePatch2(clz = AbstractCard.class, method = "calculateCardDamage")
    public static class AoeTargetPatch {
        @SpireInsertPatch(locator = Locator.class, localvars = {"tmp", "m", "i"})
        public static void patch(float[] tmp, ArrayList<AbstractMonster> m, int i) {
            GlockOnPower pow = (GlockOnPower) Wiz.p().getPower(GlockOnPower.POWER_ID);
            if (pow != null) {
                tmp[i] = (int) pow.atDamageGiveAgainstSpecificFoe(tmp[i], m.get(i));
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractStance.class, "atDamageGive");
                ArrayList<Matcher> matchers = new ArrayList<>();
                matchers.add(finalMatcher);
                return LineFinder.findInOrder(ctMethodToPatch, matchers, finalMatcher);
            }
        }
    }
}