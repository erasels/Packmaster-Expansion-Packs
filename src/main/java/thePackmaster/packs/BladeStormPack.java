package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.bladestormpack.*;

import java.util.ArrayList;

public class BladeStormPack  extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BladeStormPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BladeStormPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(4, 2, 4, 3, 3, PackSummary.Tags.Attacks, PackSummary.Tags.Strength));
        hatHidesHair = false;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(FlyingSwords.ID);
        cards.add(GeoDaRay.ID);
        cards.add(Overpressure.ID);
        cards.add(TradeWinds.ID);
        cards.add(TempestOfStrikes.ID);
        cards.add(FallOver.ID);
        cards.add(FeedingWinds.ID);
        cards.add(EscalatingBreeze.ID);
        cards.add(GaleForce.ID);
        cards.add(DownwindBlow.ID);
        return cards;
    }
}
