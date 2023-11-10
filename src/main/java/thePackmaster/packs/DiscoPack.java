package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.discopack.*;

import java.util.ArrayList;

public class DiscoPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DiscoPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DiscoPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(4, 3, 2, 3, 3, PackSummary.Tags.Discard));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(TheBump.ID);
        cards.add(DanceTransition.ID);
        cards.add(ElectricSlide.ID);
        cards.add(FastMovements.ID);
        cards.add(FunkyChicken.ID);
        cards.add(InTheRhythm.ID);
        cards.add(PointMove.ID);
        cards.add(TheBusStop.ID);
        cards.add(TheRobot.ID);
        cards.add(YMCA.ID);
        return cards;
    }
}
