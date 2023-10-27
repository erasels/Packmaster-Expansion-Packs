package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.magnetizepack.*;

import java.util.ArrayList;

public class MagnetizePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID(MagnetizePack.class.getSimpleName());
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public MagnetizePack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(3, 2, 4, 1, 5, PackSummary.Tags.Exhaust, PackSummary.Tags.Discard, PackSummary.Tags.Tokens));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Attraction.ID);
        cards.add(CoilBurst.ID);
        cards.add(ElectronFlow.ID);
        cards.add(FaradayField.ID);
        cards.add(Ferroshield.ID);
        cards.add(Fuzz.ID);
        cards.add(InduceCurrent.ID);
        cards.add(MagneticDomain.ID);
        cards.add(PerfectedAlignment.ID);
        cards.add(RepulsionField.ID);
        cards.add(Superconductance.ID);
        return cards;
    }
}
