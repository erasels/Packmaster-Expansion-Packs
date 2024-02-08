package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.blue.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class IndomitablePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("IndomitablePack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public IndomitablePack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(1, 5, 1, 4, 4, PackSummary.Tags.Exhaust));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(BodySlam.ID);
        cards.add(Leap.ID);
        cards.add(PiercingWail.ID);
        cards.add(Blur.ID);
        cards.add(Caltrops.ID);
        cards.add(FeelNoPain.ID);
        cards.add(GeneticAlgorithm.ID);
        cards.add(WaveOfTheHand.ID);
        cards.add(Buffer.ID);
        cards.add(Impervious.ID);
        return cards;
    }
}
