package thePackmaster.patches.stancedancepack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import thePackmaster.powers.stancedancepack.DanceOfTheDeadPower;
import thePackmaster.util.Wiz;

@SpirePatch(clz = AbstractStance.class, method = "onEnterStance")
public class DanceOfTheDeadTriggerPatch {
    @SpirePrefixPatch
    public static void Prefix(AbstractStance __instance) {
        if (!(__instance instanceof NeutralStance)) {
            Wiz.forAllMonstersLiving(DanceOfTheDeadTriggerPatch::apply);
        }
    }

    private static void apply(AbstractMonster m) {
        if (m.hasPower(DanceOfTheDeadPower.POWER_ID)) {
            m.getPower(DanceOfTheDeadPower.POWER_ID).onSpecificTrigger();
        }
    }
}
