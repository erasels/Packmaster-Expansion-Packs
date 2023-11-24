package thePackmaster.patches.intriguepack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.cards.intriguepack.Exultation;

public class ExultationPatch {
    @SpirePatch(
            clz = FastCardObtainEffect.class,
            method = "update"
    )
    public static class OnPickupCardDoStuffPatch2 {
        public static void Postfix(FastCardObtainEffect __instance) {
            AbstractCard q = (AbstractCard) ReflectionHacks.getPrivate(__instance, FastCardObtainEffect.class, "card");
            if (__instance.isDone) {
                // If card is rare, go through deck and pump up misc on every copy of Exultation.
                if (q.rarity == AbstractCard.CardRarity.RARE)
                {
                    for (AbstractCard card : AbstractDungeon.player.masterDeck.group)
                    {
                        if (card instanceof Exultation) ((Exultation)card).pumpUp();
                    }
                }
            }
        }
    }

    @SpirePatch(
            clz = ShowCardAndObtainEffect.class,
            method = "update"
    )
    public static class OnPickupCardDoStuffPatch {
        public static void Postfix(ShowCardAndObtainEffect __instance) {
            AbstractCard q = (AbstractCard)ReflectionHacks.getPrivate(__instance, ShowCardAndObtainEffect.class, "card");
            if (__instance.isDone) {
                // If card is rare, go through deck and pump up misc on every copy of Exultation.
                if (q.rarity == AbstractCard.CardRarity.RARE)
                {
                    for (AbstractCard card : AbstractDungeon.player.masterDeck.group)
                    {
                        if (card instanceof Exultation) ((Exultation)card).pumpUp();
                    }
                }
            }
        }
    }
}