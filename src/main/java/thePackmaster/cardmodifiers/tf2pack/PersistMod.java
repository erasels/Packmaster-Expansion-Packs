package thePackmaster.cardmodifiers.tf2pack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.tf2pack.PersistHelper;

public class PersistMod extends AbstractCardModifier {
    public static final String ID = SpireAnniversary5Mod.makeID("PersistModifier");
    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(ID);

    public PersistMod() {
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (rawDescription.contains(strings.TEXT[0]))
            return rawDescription;
        return rawDescription + strings.TEXT[0];
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        PersistHelper.IncrementPersist(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PersistMod();
    }
}
