package thePackmaster.orbs.doppelpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.AnimateDoppelAction;

public class DrawCardDoppel extends AbstractDoppel {
    public static String ORB_ID = SpireAnniversary5Mod.makeID(DrawCardDoppel.class.getSimpleName());
    public static final OrbStrings ORB_STRING = CardCrawlGame.languagePack.getOrbString(ORB_ID);

    private final TextureRegion image;

    public DrawCardDoppel(AbstractCard card) {
        super(card);
        this.ID = ORB_ID;
        this.image = AbstractPower.atlas.findRegion("128/draw");
        this.name = ORB_STRING.NAME;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        applyFocus();
        this.description = String.format(ORB_STRING.DESCRIPTION[0], this.getCardName());
    }

    @Override
    protected void applyPowers(int numFocus, int numWormHole) {
    }

    @Override
    public void onStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new AnimateDoppelAction(this, 0.4f));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
    }

    @Override
    protected AbstractDoppel makeDoppelCopy() {
        return new DrawCardDoppel(this.card);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        int width = this.image.getRegionWidth();
        int height = this.image.getRegionHeight();
        sb.draw(this.image,
                this.cX - width / 2f,
                this.cY - height / 2f + this.bobEffect.y,
                width / 2f, height / 2f,
                width, height,
                this.scale / 1.7f, this.scale / 1.7f,
                0);

        this.hb.render(sb);
    }
}
