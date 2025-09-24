package thePackmaster.orbs.doppelpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import thePackmaster.SpireAnniversary5Mod;

public class StatusDoppel extends AbstractDoppel {
    public static String ORB_ID = SpireAnniversary5Mod.makeID(StatusDoppel.class.getSimpleName());
    public static final OrbStrings ORB_STRING = CardCrawlGame.languagePack.getOrbString(ORB_ID);

    public StatusDoppel(AbstractCard card) {
        super(card);
        this.ID = ORB_ID;
        this.img = ImageMaster.INTENT_DEBUFF;
        this.name = ORB_STRING.NAME;
        this.updateDescription();
    }

    @Override
    protected void applyPowers(int numFocus, int numWormHole) {
    }

    @Override
    public void updateDescription() {
        applyFocus();
        this.description = String.format(ORB_STRING.DESCRIPTION[card.cost >= 0 ? 0 : 1], this.getCardName());
    }

    @Override
    protected AbstractDoppel makeDoppelCopy() {
        return new StatusDoppel(this.card);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        int size = this.img.getWidth();
        sb.draw(this.img,
                this.cX - size / 2f,
                this.cY - size / 2f + this.bobEffect.y,
                size / 2f, size / 2f,
                size, size,
                this.scale, this.scale,
                angle,
                0, 0,
                size, size,
                false, false);

        this.hb.render(sb);
    }
}
