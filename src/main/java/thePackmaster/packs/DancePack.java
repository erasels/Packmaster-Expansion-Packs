package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.dancepack.*;

import java.util.ArrayList;

public class DancePack extends AbstractCardPack {

    public static final String ID = SpireAnniversary5Mod.makeID("DancePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DancePack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(1, 1, 1, 1, 1));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Airflare.ID);
        cards.add(Breakdance.ID);
        cards.add(Chasse.ID);
        cards.add(Enhappe.ID);
        cards.add(Floorwork.ID);
        cards.add(Glisser.ID);
        cards.add(Intro.ID);
        cards.add(Moonwalk.ID);
        cards.add(Toprock.ID);;
        cards.add(Updraft.ID);
        cards.add(Waltz.ID);
        return cards;
    }
}
