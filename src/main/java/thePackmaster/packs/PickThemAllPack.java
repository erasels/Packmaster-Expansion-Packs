package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.pickthemallpack.*;

import java.util.ArrayList;

public class PickThemAllPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("PickThemAllPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public PickThemAllPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new PackSummary(3, 3, 4, 3, 4));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BulkUp.ID);
        cards.add(GoldenPick.ID);
        cards.add(DeckCheck.ID);
        cards.add(BigBoots.ID);
        cards.add(BurningFists.ID);
        cards.add(PackGear.ID);
        cards.add(TouchedByFlame.ID);
        cards.add(DarkBargain.ID);
        cards.add(GrabAndGo.ID);
        cards.add(Transmogrifier.ID);
        return cards;
    }
}
