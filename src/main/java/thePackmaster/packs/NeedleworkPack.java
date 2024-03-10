package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.needleworkpack.*;

import java.util.ArrayList;

import static thePackmaster.packs.PackPreviewCard.getCardTextureString;

public class NeedleworkPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("NeedleworkPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public NeedleworkPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(2, 4, 2, 2, 4, PackSummary.Tags.None));
    }

    @Override
    public PackPreviewCard makePreviewCard() {
        return new PackPreviewCard(this.packID, getCardTextureString("Rethread", AbstractCard.CardType.SKILL), this);
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Needlework.ID);
        cards.add(Backstitch.ID);
        cards.add(CrossStitch.ID);
        cards.add(Reading.ID);
        cards.add(Read.ID);
        cards.add(Cleanup.ID);
        cards.add(Knot.ID);
        cards.add(Pincushion.ID);
        cards.add(Rethread.ID);
        cards.add(CopyAndPaste.ID);
        cards.add(Patchwork.ID);
        return cards;
    }
}