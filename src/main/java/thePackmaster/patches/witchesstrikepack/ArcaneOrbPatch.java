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
    public ArcaneOrbPatch() {
    }

    @SpireInsertPatch(
            locator = ArcaneOrbPatch.Locator.class,
            localvars = {"targetCard"}
    )
    public static void Insert(UseCardAction __instance, AbstractCard targetCard) {

        for (AbstractOrb abstractOrb : AbstractDungeon.player.orbs) {
            AbstractOrb Orb = abstractOrb;
            if (Orb instanceof Arcane && targetCard.type == AbstractCard.CardType.SKILL) {
                ((Arcane) Orb).passiveEffect();
            }
        }

    }

    private static class Locator extends SpireInsertLocator {
        private Locator() {
        }

        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getMonsters");
            return LineFinder.findInOrder(ctBehavior, new ArrayList(), finalMatcher);
        }
    }
}
