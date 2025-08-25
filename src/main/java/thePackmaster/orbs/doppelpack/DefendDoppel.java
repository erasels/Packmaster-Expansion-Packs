package thePackmaster.orbs.doppelpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.AnimateDoppelAction;

public class DefendDoppel extends AbstractDoppel {
    public static String ORB_ID = SpireAnniversary5Mod.makeID(DefendDoppel.class.getSimpleName());
    public static final OrbStrings ORB_STRING = CardCrawlGame.languagePack.getOrbString(ORB_ID);

    public int baseBlock;
    private int block;

    public DefendDoppel(AbstractCard card) {
        super(card);
        this.ID = ORB_ID;
        this.img = ImageMaster.INTENT_DEFEND;
        this.name = ORB_STRING.NAME;
        this.baseBlock = this.getDoppelDefend(card, 2);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        applyFocus();
        this.description = String.format(ORB_STRING.DESCRIPTION[0], this.block, this.getCardName());
    }

    @Override
    protected void applyPowers(int numFocus, int numWormHole) {
        AbstractPlayer player = AbstractDungeon.player;
        AbstractPower dexterityPower = player == null ? null : player.getPower(DexterityPower.POWER_ID);
        int numDexterity = dexterityPower == null ? 0 : dexterityPower.amount;
        this.block = Math.max(0, this.baseBlock + numFocus + numDexterity * numWormHole);
    }

    @Override
    public void onEndOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new AnimateDoppelAction(this, 0.4f));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, this.block));
    }

    @Override
    protected AbstractDoppel makeDoppelCopy() {
        return new DefendDoppel(this.card);
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
                0,
                0, 0,
                size, size,
                false, false);

        if (!this.showEvokeValue) {
            String text = String.valueOf(this.block);
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, text, this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, this.c, this.fontScale);
        }

        this.hb.render(sb);
    }
}
