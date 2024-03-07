package thePackmaster.cardmodifiers.cosmoscommand;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PurgeModifier extends AbstractCardModifier {
    public static String ID = makeID("PurgeModifier");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("PurgeModifier"));
    public static final String[] TEXT = uiStrings.TEXT;

    public PurgeModifier() {}

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + TEXT[0];
    }

    public boolean shouldApply(AbstractCard card) {
        return !PurgeField.purge.get(card);
    }

    public void onInitialApplication(AbstractCard card) {
        PurgeField.purge.set(card, true);
    }

    public void onRemove(AbstractCard card) {
        PurgeField.purge.set(card, false);
    }

    public AbstractCardModifier makeCopy() {
        return new PurgeModifier();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
