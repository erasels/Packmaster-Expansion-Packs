package thePackmaster.patches.maridebuffpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.powers.maridebuffpack.DenouementPower;

// Set vulnerable damage amp to 100% when the player has Denouement power
public class DenouementPowerPatch {
    @SpirePatch(clz = VulnerablePower.class, method = "atDamageReceive")
    public static class VulnerableDamageIncrease {
        @SpirePrefixPatch
        public static SpireReturn<Float> PreFix(VulnerablePower __instance, float damage, DamageInfo.DamageType type) {
            if(type == DamageInfo.DamageType.NORMAL && AbstractDungeon.player.hasPower(DenouementPower.POWER_ID)){
                return SpireReturn.Return(damage*2);
            }
            return SpireReturn.Continue();
        }
    }
}
