package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class BladeStormPack  extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BladeStormPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BladeStormPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(4, 2, 3, 3, 3, PackSummary.Tags.Attacks, PackSummary.Tags.Strength));
        hatHidesHair = true;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
//        cards.add(Shelling.ID);
//        cards.add(Suppress.ID);
//        cards.add(BallisticStrike.ID);
//        cards.add(DefensePlanning.ID);
//        cards.add(ThinkTwice.ID);
//        cards.add(Logistics.ID);
//        cards.add(ShellForge.ID);
//        cards.add(Shrapnel.ID);
//        cards.add(DigIn.ID);
//        cards.add(Prevail.ID);
        return cards;
    }
}
