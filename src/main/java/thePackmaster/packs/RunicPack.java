package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.runicpack.*;
import thePackmaster.cards.serpentinepack.*;

import java.util.ArrayList;

public class RunicPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("RunicPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    //public static final String CREDITS = UI_STRINGS.TEXT[3];

    public static boolean channeledOrbThisTurn = false;

    public RunicPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(4, 1, 3, 2, 4, PackSummary.Tags.Stances, PackSummary.Tags.Orbs));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(ReadRunes.ID);
        cards.add(AncientBlade.ID);
        cards.add(MeditationChamber.ID);
        cards.add(AncientTexts.ID);
        cards.add(AncientObelisk.ID);
        cards.add(Chakram.ID);
        cards.add(RunicSpear.ID);
        cards.add(Secrets.ID);
        cards.add(Journal.ID);
        cards.add(DaringEscape.ID);

        return cards;
    }
}
