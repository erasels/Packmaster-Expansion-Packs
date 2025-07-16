package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.turmoilpack.*;

import java.util.ArrayList;

public class TurmoilPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("TurmoilPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public TurmoilPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new PackSummary(1, 1, 1, 1, 1));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(DarkPath.ID);
        cards.add(SpitefulBrand.ID);
        cards.add(LonelyVigil.ID);
        cards.add(LashOut.ID);
        cards.add(BurntOffering.ID);
        cards.add(Burden.ID);
        return cards;
    }
}
