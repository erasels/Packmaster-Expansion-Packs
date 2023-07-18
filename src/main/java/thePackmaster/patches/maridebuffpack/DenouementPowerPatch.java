package thePackmaster.patches.maridebuffpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import javassist.*;
import javassist.bytecode.DuplicateMemberException;
import thePackmaster.powers.maridebuffpack.DenouementPower;

// [Denouement power: your Vulnerable also increases the damage of your attacks.]
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