package thePackmaster.patches.pickthemallpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import thePackmaster.cards.pickthemallpack.ResetDescriptionForCombat;

@SpirePatch2(clz = CardGroup.class, method = "initializeDeck")
public class ResetDescriptionForCombatPatch {
    @SpirePostfixPatch
    public static void resetDescriptionForCombat(CardGroup __instance) {
        for (AbstractCard c : __instance.group) {
            if (c instanceof ResetDescriptionForCombat) {
                ((ResetDescriptionForCombat)c).resetDescriptionForCombat();
            }
        }
    }
}
