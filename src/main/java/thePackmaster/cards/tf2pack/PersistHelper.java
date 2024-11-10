package thePackmaster.cards.tf2pack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class PersistHelper {
    public static void IncrementPersist(AbstractCard card) {
        int callerBasePersist = PersistFields.basePersist.get(card);
        if (callerBasePersist <= 0) {
            // Persist x means you can play the card x times this turn, so persist 1 does nothing
            PersistFields.setBaseValue(card, 2);
            return;
        }
        PersistFields.basePersist.set(card, callerBasePersist + 1);
        int callerPersist = PersistFields.persist.get(card);
        PersistFields.persist.set(card, callerPersist + 1);
        card.initializeDescription();
    }
}
