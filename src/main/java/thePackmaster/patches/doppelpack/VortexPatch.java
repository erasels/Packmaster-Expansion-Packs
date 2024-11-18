package thePackmaster.patches.doppelpack;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.cards.doppelpack.Vortex;

@SpirePatch(clz = UseCardAction.class, method = "update")
public class VortexPatch {
    @SpireInstrumentPatch
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getMethodName().equals("moveToDiscardPile")) {
                    m.replace(String.format("{ if (%s.onUsedCard($1)) { $_ = $proceed($$); } }", Vortex.class.getName()));
                }
            }
        };
    }
}
