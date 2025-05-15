package thePackmaster.packs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.insectglaivepack.*;
import thePackmaster.patches.insectglaivepack.DrawAnalyzer;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceRedPower;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceWhitePower;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceYellowPower;

import java.util.ArrayList;

public class InsectGlaivePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("InsectGlaive");
    public static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];


    public static final Logger logger = LogManager.getLogger(InsectGlaivePack.class.getName());


    public InsectGlaivePack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(3, 3, 5, 4, 1, PackSummary.Tags.Orbs));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(RisingSlashCombo.ID);
        cards.add(OverheadSmash.ID);
        cards.add(DodgeSlash.ID);
        cards.add(KinsectHarvestExtract.ID);
        cards.add(Vault.ID);
        cards.add(JumpingAdvancingSlash.ID);
        cards.add(DescendingSlash.ID);
        cards.add(RisingSpiralSlash.ID);
        cards.add(Evade.ID);
        cards.add(Mounting.ID);
        return cards;
    }

    //自己的随机数
    public static Random InsectRamdom = new Random();

    public static void initInsectRamdom() {
        InsectRamdom = new Random(Settings.seed + AbstractDungeon.floorNum * 10L);
    }

    //是否处于战斗中
    public static boolean isInCombat() {
        return (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                AbstractDungeon.player != null;
    }


    //是否处于空中
    public static boolean isHover(int n) {
        return isInCombat() && AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= n &&
                DrawAnalyzer.isDrawCard(
                        AbstractDungeon.actionManager.cardsPlayedThisCombat.get(
                                AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - n).getClass().getName());
    }

    //画图案
    private static final Matrix4 mx4 = new Matrix4();
    private static final Matrix4 rotatedTextMatrix = new Matrix4();

    public static void renderRotateTexture(SpriteBatch sb, Texture t, float x, float y, float offsetX, float offsetY, float scale, float angle) {
        mx4.setToRotation(0.0F, 0.0F, 1.0F, angle);

        Vector2 vec = new Vector2(offsetX, offsetY);
        rotate(vec, angle);
        mx4.trn(x + vec.x,
                y + vec.y, 0.0F);
        sb.end();
        sb.setTransformMatrix(mx4);
        sb.begin();
        sb.draw(t, 0, 0, 0, 0, t.getWidth(), t.getHeight(), scale, scale, 0,
                0, 0, t.getWidth(), t.getHeight(), false, false);
        sb.end();
        sb.setTransformMatrix(rotatedTextMatrix);
        sb.begin();
    }

    //旋转
    public static void rotate(Vector2 vec, float radians) {
        float cos = (float) Math.cos((double) radians * 0.017453292F);
        float sin = (float) Math.sin((double) radians * 0.017453292F);
        float newX = vec.x * cos - vec.y * sin;
        float newY = vec.x * sin + vec.y * cos;
        vec.x = newX;
        vec.y = newY;
    }

    //移动位置
    public static void movePowerPosition(AbstractPower p) {
        int index = -1;
        for (int i = 0; i < AbstractDungeon.player.powers.size(); i++) {
            AbstractPower po = AbstractDungeon.player.powers.get(i);
            if ((po instanceof ExtractedEssenceRedPower || po instanceof ExtractedEssenceWhitePower || po instanceof ExtractedEssenceYellowPower) &&
                    po != p) {
                index = i;
            }
        }

        if (index >= 0) {
            AbstractDungeon.player.powers.remove(p);
            AbstractDungeon.player.powers.add(index+1, p);
        }
    }
}
