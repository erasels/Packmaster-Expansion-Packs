package thePackmaster.patches.royaltypack;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
        System.out.println("Foi");
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
