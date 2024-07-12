package thePackmaster.patches.intothebreachpack;

import basemod.abstracts.CustomMonster;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.Skeleton;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.Watcher;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import thePackmaster.powers.intothebreachpack.AcidPower;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class AcidVfxPatch {
    // Code shamelessly stolen from Minty Spire
    // Monsters
    @SpirePatch2(clz = AbstractMonster.class, method = "render")
    @SpirePatch2(clz = CustomMonster.class, method = "render")
    public static class RenderAtlasColorChanger {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(AbstractMonster __instance, SpriteBatch sb) {
            if(__instance.hasPower(AcidPower.POWER_ID)) {
                sb.setColor(oscillator(__instance.tint.color));
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SpriteBatch.class, "draw");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }

    @SpirePatch(clz = AbstractMonster.class, method = "render")
    @SpirePatch2(clz = CustomMonster.class, method = "render")
    public static class RenderSkeletonColorChanger {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(AbstractMonster __instance, SpriteBatch sb, Skeleton ___skeleton) {
            if(__instance.hasPower(AcidPower.POWER_ID)) {
                ___skeleton.setColor(oscillator(__instance.tint.color));
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Skeleton.class, "setFlip");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }

    // Players (should never happen, but I like maximising compatibility)
    @SpirePatch(clz = AbstractPlayer.class, method = "renderPlayerImage")
    @SpirePatch(clz = AbstractPlayer.class, method = "render")
    public static class PlayerRenderAtlasColorChanger1 {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(AbstractPlayer __instance, SpriteBatch sb) {
            if(__instance.hasPower(AcidPower.POWER_ID)) {
                sb.setColor(oscillator(Color.WHITE));
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SpriteBatch.class, "draw");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "renderPlayerImage")
    @SpirePatch2(clz = Watcher.class, method = "renderPlayerImage")
    public static class PlayerRenderSkeletonColorChanger {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(AbstractPlayer __instance, SpriteBatch sb, Skeleton ___skeleton) {
            if(__instance.hasPower(AcidPower.POWER_ID)) {
                ___skeleton.setColor(oscillator(__instance.tint.color));
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Skeleton.class, "setFlip");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
            }
        }
    }

    private static float oscillatingTimer = 0.0f;
    private static float oscillatingFader = 0.0f;

    public static Color oscillator(Color c) {
        oscillatingFader += Gdx.graphics.getRawDeltaTime();
        if (oscillatingFader > 0.66F) {
            oscillatingFader = 0.66F;
            oscillatingTimer += Gdx.graphics.getRawDeltaTime() * 2f;
        }
        Color col = c.cpy();
        // Reduce red and blue channels, results in green appearance
        col.b = col.r = (0.66F + (MathUtils.cos(oscillatingTimer) + 1.0F) / 3.0F) * oscillatingFader;
        return col;
    }
}
