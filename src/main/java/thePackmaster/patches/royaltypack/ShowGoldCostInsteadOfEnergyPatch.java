package thePackmaster.patches.royaltypack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import javassist.CtBehavior;

@SpirePatch(
        clz = AbstractCard.class,
        method = "renderEnergy"
)

public class ShowGoldCostInsteadOfEnergyPatch {

    @SpireInsertPatch(
            locator= Locator.class
    )

    public static void ShowGoldCostInsteadOfEnergy(AbstractCard __instance){
        if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(__instance) &&
                AbstractDungeon.player.hasPower("HiredSupportPower")) {
            Color costColor = (Color) ReflectionHacks.getPrivate(__instance, Color.class, "costColor");
            String text = (String) ReflectionHacks.getPrivate(__instance, String.class, "text");
            costColor = Color.YELLOW;
            if (__instance.cost == -1){
                text = "10 X";
            } else {
                text = Integer.toString(__instance.costForTurn*10);
            }
            ReflectionHacks.setPrivate(__instance, Color.class, "costColor", costColor);
            ReflectionHacks.setPrivate(__instance, String.class, "text", text);
            System.out.println("Huh");
        }
    }


    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "getEnergyFont");
            int[] matches = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{matches[0] + 1};
        }
    }
}
