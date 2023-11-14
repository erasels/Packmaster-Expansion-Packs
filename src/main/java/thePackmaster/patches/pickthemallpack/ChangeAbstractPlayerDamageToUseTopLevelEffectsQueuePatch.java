package thePackmaster.patches.pickthemallpack;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

// AbstractPlayer.damage using topLevelEffects causes concurrent modification crashes when it's called from within
// a game effect, as is the case for Burning Fists. It should really use topLevelEffectsQueue to avoid this.
public class ChangeAbstractPlayerDamageToUseTopLevelEffectsQueuePatch {
    public static boolean active = false;

    @SpirePatch(clz = AbstractPlayer.class, method = "damage", paramtypez = { DamageInfo.class })
    public static class PatchExprEditor extends ExprEditor {
        @Override
        public void edit(FieldAccess fieldAccess) throws CannotCompileException {
            if (fieldAccess.getClassName().equals(AbstractDungeon.class.getName()) && fieldAccess.getFieldName().equals("topLevelEffects")) {
                fieldAccess.replace(String.format("{ if (%1$s.active) { $_ = %2$s.topLevelEffectsQueue; } else { $_ = $proceed($$); } }", ChangeAbstractPlayerDamageToUseTopLevelEffectsQueuePatch.class.getName(), AbstractDungeon.class.getName()));
            }
        }

        @SpireInstrumentPatch
        public static ExprEditor patch() {
            return new PatchExprEditor();
        }
    }
}
