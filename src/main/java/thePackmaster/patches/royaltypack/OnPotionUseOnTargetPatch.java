package thePackmaster.patches.royaltypack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;
import thePackmaster.powers.royaltypack.OnPotionUsePower;

@SpirePatch(
        clz = PotionPopUp.class,
        method = "updateTargetMode"
)
public class OnPotionUseOnTargetPatch {

    @SpireInsertPatch(
            locator= Locator.class
    )

    public static void onPotionUse(PotionPopUp __instance) {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof OnPotionUsePower) {
                ((OnPotionUsePower) power).onPotionUse();
                power.flash();
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(TopPanel.class, "destroyPotion");
            int[] matches = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{matches[0]};
        }
    }

}
