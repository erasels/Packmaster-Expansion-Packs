package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.royaltypack.*;

import java.util.ArrayList;

public class RoyaltyPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("RoyaltyPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final String CREDITS = UI_STRINGS.TEXT[3];

    public RoyaltyPack() {
        super(ID, NAME, DESC, AUTHOR, CREDITS, new PackSummary(4,2,5,3,3, PackSummary.Tags.Discard));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(RetainerStrike.ID);
        cards.add(Soulstone.ID);
        cards.add(WindShield.ID);
        cards.add(Forcefield.ID);
        cards.add(MiningStrike.ID);
        cards.add(BoundlessTalent.ID);
        cards.add(Willpower.ID);
        cards.add(MajesticBloodline.ID);
        cards.add(MoreSupplies.ID);
        cards.add(NobleForm.ID);
        return cards;
    }
}
