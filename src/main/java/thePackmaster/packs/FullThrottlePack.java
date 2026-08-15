package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.fullthrottlepack.*;

import java.util.ArrayList;

public class FullThrottlePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID(FullThrottlePack.class.getSimpleName());
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public FullThrottlePack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(5, 1, 4, 3, 3));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Downforce.ID);
        cards.add(NitrousBash.ID);
        cards.add(SparkPlugs.ID);
        cards.add(TurnAndBurn.ID);
        cards.add(TwinTurbo.ID);
        cards.add(Overtuned.ID);
        cards.add(Afterburner.ID);
        cards.add(V16.ID);
        cards.add(Dragster.ID);
        cards.add(HighOctane.ID);
        return cards;
    }
}
