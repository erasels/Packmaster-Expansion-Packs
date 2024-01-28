package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.purple.EmptyFist;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.stancedancepack.*;

import java.util.ArrayList;

public class StanceDancePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("StanceDancePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public StanceDancePack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(4, 3, 2, 3, 2, PackSummary.Tags.Stances));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(PivotStrike.ID);
        cards.add(Pitch.ID);
        cards.add(Choreography.ID);
        cards.add(PullBack.ID);
        cards.add(FanKick.ID);
        cards.add(TimeStep.ID);
        cards.add(StagLeap.ID);
        cards.add(Pirouette.ID);
        cards.add(FinalPerformance.ID);
        cards.add(DanceOfTheDead.ID);
        return cards;
    }
}
