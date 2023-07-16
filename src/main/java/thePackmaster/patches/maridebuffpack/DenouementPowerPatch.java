package thePackmaster.patches.maridebuffpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import javassist.*;
import javassist.bytecode.DuplicateMemberException;
import thePackmaster.ExpansionPacks;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.maridebuffpack.DenouementPower;

// Denouement power:
// adds atDamageGive to Vulnerable power if needed
// adds atDamageReceive to atDamageGive if Denouement power is active on player
// (format sampled from Kio)
@SpirePatch(clz = VulnerablePower.class, method = SpirePatch.CONSTRUCTOR)
public class DenouementPowerPatch
{
    public static void Raw(CtBehavior ctMethodToPatch) throws NotFoundException, CannotCompileException
    {
        CtClass ctClass = ctMethodToPatch.getDeclaringClass();
        CtClass superClass = ctClass.getSuperclass();
        CtMethod superMethod = null;

        do{
            try{
                superMethod = superClass.getDeclaredMethod("atDamageGive");
            }catch(NotFoundException e){
                superClass = superClass.getSuperclass();
            }
        }while(superMethod == null);

        CtMethod method = CtNewMethod.delegator(superMethod, ctClass);

        try {
            ctClass.addMethod(method);
        } catch (DuplicateMemberException e) {
            method = ctClass.getDeclaredMethod("atDamageGive");
        }

        method.insertBefore(
        "if(" + AbstractDungeon.class.getName() + ".player.hasPower(" + DenouementPower.class.getName() + ".POWER_ID) && this.owner == " + AbstractDungeon.class.getName() + ".player){" +
                "return atDamageReceive($1, $2);" +
            "}"
        );
    }
}

// Old version: Set vulnerable damage amp to 100% when the player has Denouement power
//public class DenouementPowerPatch {
//    @SpirePatch(clz = VulnerablePower.class, method = "atDamageReceive")
//    public static class VulnerableDamageIncrease {
//        @SpirePrefixPatch
//        public static SpireReturn<Float> PreFix(VulnerablePower __instance, float damage, DamageInfo.DamageType type) {
//            if(type == DamageInfo.DamageType.NORMAL && AbstractDungeon.player.hasPower(DenouementPower.POWER_ID)){
//                return SpireReturn.Return(damage*2);
//            }
//            return SpireReturn.Continue();
//        }
//    }
//}