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
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class InsectGlaivePack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("InsectGlaive");
    public static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];


    public static final Logger logger = LogManager.getLogger(InsectGlaivePack.class.getName());


    public InsectGlaivePack() {
        super(ID, NAME, DESC, AUTHOR, new PackSummary(4, 2, 3, 3, 3));
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

    //是否处于空中
    public static boolean isHover(int n) {
        return Wiz.isInCombat() && AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= n &&
                DrawAnalyzer.isDrawCard(
                        AbstractDungeon.actionManager.cardsPlayedThisCombat.get(
                                AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - n).getClass().getName());
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
