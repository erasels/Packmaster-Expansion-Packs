package thePackmaster.patches.lockonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbPassiveAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.powers.lockonpack.GlockOnPower;
import thePackmaster.powers.lockonpack.LightningRodPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.stream.Collectors;

@SpirePatch2(clz = AbstractCard.class, method = "calculateCardDamage")
public class GlockOnPatch {
    @SpireInsertPatch(locator = Locator.class, localvars = "tmp")
    public static void patch(AbstractCard __instance, AbstractMonster mo, @ByRef int[] tmp) {
        if (AbstractDungeon.player.hasPower(GlockOnPower.POWER_ID)) {
            tmp[0] = (int) ((GlockOnPower)AbstractDungeon.player.getPower(GlockOnPower.POWER_ID)).atDamageGiveAgainstSpecificFoe(tmp[0], mo);
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