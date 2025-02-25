package thePackmaster.patches.witchesstrikepack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import javassist.CtBehavior;
import thePackmaster.orbs.WitchesStrike.Arcane;

import java.util.ArrayList;

@SpirePatch(clz = UseCardAction.class, method = "update")
public class ArcaneOrbPatch {
    @SpireInsertPatch(
            locator = ArcaneOrbPatch.Locator.class,
            localvars = {"targetCard"}
    )
    public static void Insert(UseCardAction __instance, AbstractCard targetCard) {
        if (targetCard.type == AbstractCard.CardType.SKILL) {
            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof Arcane) {
                    ((Arcane) orb).passiveEffect();
                }
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getMonsters");
            return LineFinder.findInOrder(ctBehavior, new ArrayList<>(), finalMatcher);
        }
    }
}
