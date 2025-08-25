package thePackmaster.orbs.doppelpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.AnimateDoppelAction;
import thePackmaster.util.TexLoader;

public class GainEnergyDoppel extends AbstractDoppel {
    public static String ORB_ID = SpireAnniversary5Mod.makeID(GainEnergyDoppel.class.getSimpleName());
    public static final OrbStrings ORB_STRING = CardCrawlGame.languagePack.getOrbString(ORB_ID);

    public GainEnergyDoppel(AbstractCard card) {
        super(card);
        this.ID = ORB_ID;
        this.img = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/1024/energy.png");
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
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    }

    @Override
    protected AbstractDoppel makeDoppelCopy() {
        return new GainEnergyDoppel(this.card);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        int width = this.img.getWidth();
        int height = this.img.getHeight();
        sb.draw(this.img,
                this.cX - width / 2f,
                this.cY - height / 2f + this.bobEffect.y,
                width / 2f, height / 2f,
                width, height,
                this.scale / 2.5f, this.scale / 2.5f,
                0, //this.angle,
                0, 0,
                width, height,
                false, false);

        this.hb.render(sb);
    }
}
