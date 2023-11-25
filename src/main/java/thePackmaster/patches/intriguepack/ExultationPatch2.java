package thePackmaster.patches.intriguepack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.cards.intriguepack.Exultation;

@SpirePatch2(clz = CardLibrary.class, method = "getCopy", paramtypez = { String.class, int.class, int.class })
public class ExultationPatch2 {

    @SpirePostfixPatch
    public static void fixDescription(AbstractCard __result, String key, int upgradeTime, int misc) {
        if (__result instanceof Exultation) {
            Exultation c = (Exultation)__result;
            c.baseBlock = c.BLOCK + misc;
            c.initializeDescription();
        }
    }
}
