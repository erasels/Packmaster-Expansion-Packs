package thePackmaster.packs;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.doppelpack.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class DoppelPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DoppelPack");
    public static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];
    public static final Pattern DRAW_CARD_PATTERN = Pattern.compile(UI_STRINGS.TEXT[3], Pattern.CASE_INSENSITIVE);
    public static final Pattern GAIN_ENERGY_PATTERN = Pattern.compile(UI_STRINGS.TEXT[4], Pattern.CASE_INSENSITIVE);
    public static FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Settings.WIDTH, Settings.HEIGHT, false, false);

    public static final HashMap<String, CardProperty> cachedCardProperties = new HashMap<>();

    public DoppelPack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(3, 3, 5, 4, 1, PackSummary.Tags.Orbs));

        if (frameBuffer == null) {
            frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Settings.WIDTH, Settings.HEIGHT, false, false);
        }
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(Arrive.ID);
        cards.add(Concatenation.ID);
        cards.add(EmergencyContact.ID);
        cards.add(GrandfatherParadox.ID);
        cards.add(Resistance.ID);
        cards.add(Summon.ID);
        cards.add(Tides.ID);
        cards.add(Turbulence.ID);
        cards.add(Vortex.ID);
        cards.add(WormHole.ID);
        return cards;
    }

    public static CardProperty getCardProperty(AbstractCard card) {
        CardProperty cached = cachedCardProperties.get(card.cardID);
        if (cached != null) {
            return cached;
        }

        CardProperty cardProperty = new CardProperty();
        String desc = card.rawDescription;
        cardProperty.hasDraw = DRAW_CARD_PATTERN.matcher(desc).find();
        cardProperty.hasEnergy = GAIN_ENERGY_PATTERN.matcher(desc).find();
        cardProperty.attackEffect = null;
        cardProperty.noBlock = !desc.contains("!B!");

        cachedCardProperties.put(card.cardID, cardProperty);
        return cardProperty;
    }

    public static class CardProperty {
        public boolean hasDraw;
        public boolean hasEnergy;
        public String attackEffect;
        public boolean noBlock;

        public AbstractGameAction.AttackEffect getAttackEffect() {
            if (attackEffect == null) {
                return null;
            }
            try {
                return AbstractGameAction.AttackEffect.valueOf(attackEffect);
            } catch (IllegalArgumentException ignored) {
                return null;
            }
        }
    }
}
