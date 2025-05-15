package thePackmaster.cardmodifiers.insectglaivepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceWhitePower;
import thePackmaster.util.TexLoader;

import java.util.Collections;
import java.util.List;


public class ExtractedEssenceWhite extends AbstractCardModifier {
    public static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("InsectGlaiveEssenceNameAndDescription"));

    public static String ID = SpireAnniversary5Mod.makeID("ExtractedEssenceWhite");

    private static Texture texture = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/ui/insectglaivepack/white.png");;


    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        InsectGlaivePack.renderRotateTexture(sb, texture, card.current_x, card.current_y,
                -AbstractCard.IMG_WIDTH_S * 0.6F * Settings.scale * card.drawScale, AbstractCard.IMG_HEIGHT_S * 0.25F * Settings.scale * card.drawScale,
                card.drawScale, card.angle);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ExtractedEssenceWhitePower(AbstractDungeon.player)));
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        return Collections.singletonList(new TooltipInfo(UI_STRINGS.TEXT[1], UI_STRINGS.TEXT[4]));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ExtractedEssenceWhite();
    }
}
