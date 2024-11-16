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
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.DoppelPack;

public class AttackDefendDoppel extends AttackDoppel {
    public static String ORB_ID = SpireAnniversary5Mod.makeID(AttackDefendDoppel.class.getSimpleName());
    public static final OrbStrings ORB_STRING = CardCrawlGame.languagePack.getOrbString(ORB_ID);

    private final int baseBlock;
    private int block;

    public AttackDefendDoppel(AbstractCard card, DoppelPack.CardProperty cardProperty) {
        super(card, cardProperty);
        this.ID = ORB_ID;
        this.name = ORB_STRING.NAME;
        this.baseBlock = this.getDoppelDefend(card, 1);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        applyFocus();
        this.description = String.format(ORB_STRING.DESCRIPTION[multiDamage ? 1 : 0], this.damage, this.block, this.getCardName());
    }

    @Override
    protected void applyPowers(int numFocus, int numWormHole) {
        super.applyPowers(numFocus, numWormHole);
        AbstractPlayer player = AbstractDungeon.player;
        AbstractPower dexterityPower = player == null ? null : player.getPower(DexterityPower.POWER_ID);
        int numDexterity = dexterityPower == null ? 0 : dexterityPower.amount;
        this.block = Math.max(0, this.baseBlock + numFocus + numDexterity * numWormHole);
    }

    @Override
    public void onEndOfTurn() {
        super.onEndOfTurn();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, this.block));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new AttackDefendDoppel(this.card, this.cardProperty);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.INTENT_DEFEND, this.cX - 32f, this.cY - 32f + this.bobEffect.y, 32f, 32f, 64f, 64f, this.scale, this.scale, 0, 0, 0, 64, 64, false, false);

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
                true, false);

        if (!this.showEvokeValue) {
            String text = this.damage + "/" + this.block;
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, text, this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, this.c, this.fontScale);
        }

        this.hb.render(sb);
    }
}
