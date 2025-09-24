package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.tf2pack.*;

import java.util.ArrayList;

public class TF2Pack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("TF2Pack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public TF2Pack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(2, 3, 3, 3, 3, PackSummary.Tags.Attacks, PackSummary.Tags.Exhaust));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(CozyCamper.ID);
        cards.add(BonkAtomicPunch.ID);
        cards.add(BigEarner.ID);
        cards.add(Minigun.ID);
        cards.add(MiniSentry.ID);
        cards.add(Dispenser.ID);
        cards.add(InvisibilityWatch.ID);
        cards.add(DirectHit.ID);
        cards.add(AmmoBox.ID);
        cards.add(LooseCannon.ID);
        cards.add(Ammo.ID);
        return cards;
    }
}
