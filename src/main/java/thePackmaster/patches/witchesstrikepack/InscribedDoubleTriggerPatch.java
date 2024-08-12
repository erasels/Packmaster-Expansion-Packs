package thePackmaster.patches.witchesstrikepack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.cardmodifiers.InscribedMod;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.util.Wiz;

@SpirePatch2(clz = InscribedMod.class, method = "patchHook")
public class InscribedDoubleTriggerPatch {
    @SpirePostfixPatch
    public static void patch() {
        for(AbstractOrb orb : Wiz.p().orbs) {
            if(orb instanceof FullMoon || orb instanceof CrescentMoon) {
                ((PackmasterOrb)orb).passiveEffect();
            }
        }
    }
}
