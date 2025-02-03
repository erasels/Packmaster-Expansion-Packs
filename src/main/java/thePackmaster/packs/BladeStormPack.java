package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.bladestormpack.*;

import java.util.ArrayList;

/*TODO: test FallOver exhaust-on-kill.
   Do rest of the cards.
    fix dutchman only having the first string of its description, and zero functionality, when a save is loaded.
    Test pack's description length.
    Test flavor colors.
*/
public class BladeStormPack  extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("BladeStormPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public BladeStormPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(4, 2, 4, 3, 4, PackSummary.Tags.Attacks, PackSummary.Tags.Strength));
        hatHidesHair = false;
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(FlyingSwords.ID);
        cards.add(GeoDaRay.ID);
        cards.add(Overpressure.ID);
//        cards.add(DefensePlanning.ID);
        cards.add(TempestOfStrikes.ID);
        cards.add(FallOver.ID);
//        cards.add(ShellForge.ID);
        cards.add(EscalatingBreeze.ID);
//        cards.add(DigIn.ID);
        cards.add(DownwindBlow.ID);
        return cards;
    }
}
