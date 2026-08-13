package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.cellspack.*;

import java.util.ArrayList;

public class CellsPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("CellsPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public CellsPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new PackSummary(4, 2, 2, 1, 4, PackSummary.Tags.Debuffs));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(HolyWater.ID);
        cards.add(SwiftSword.ID);
        cards.add(CorrosiveCloud.ID);
        cards.add(AssaultShield.ID);
        cards.add(IceArmor.ID);
        cards.add(GrapplingHook.ID);
        cards.add(SmokeBomb.ID);
        cards.add(Hokuto.ID);
        cards.add(SnakeFangs.ID);
        cards.add(FireGrenade.ID);
        cards.add(OilGrenade.ID);
        return cards;
    }
}
