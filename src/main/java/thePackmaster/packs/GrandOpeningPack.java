package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.grandopeningpack.*;

import java.util.ArrayList;

public class GrandOpeningPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("GrandOpeningPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public GrandOpeningPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(2, 4, 4, 3, 2));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(WellWrittenScript.ID);
        cards.add(PlotArmor.ID);
        cards.add(Lights.ID);
        cards.add(Starring.ID);
        cards.add(Cross.ID);
        cards.add(Foreshadow.ID);
        cards.add(StickySituation.ID);
        cards.add(GrandOpening.ID);
        cards.add(BreakALeg.ID);
        cards.add(StartOver.ID);
        cards.add(Camera.ID);
        cards.add(Action.ID);
        return cards;
    }
}
