package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.maridebuffpack.*;

import java.util.ArrayList;

public class MariDebuffPack extends AbstractCardPack {

    public static final String ID = SpireAnniversary5Mod.makeID("MariDebuffPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public MariDebuffPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(5,3,1,3,4, PackSummary.Tags.Debuffs));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(OldCostume.ID);
        cards.add(PentupAnger.ID);
        cards.add(ToughFront.ID);
        cards.add(FacetiousFacade.ID);
        cards.add(RecurringTheme.ID);
        cards.add(Offguard.ID);
        cards.add(CharacterDevelopment.ID);
        cards.add(TheFLYINGCAR.ID);
        cards.add(Denouement.ID);
        cards.add(TheMANSION.ID);
        return cards;
    }
}
