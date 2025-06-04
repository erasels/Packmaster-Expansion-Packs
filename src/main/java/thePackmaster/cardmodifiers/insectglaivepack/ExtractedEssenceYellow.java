package thePackmaster.cardmodifiers.insectglaivepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.insectglaivepack.derivative.CardForSectionOfIG;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceYellowPower;
import thePackmaster.util.TexLoader;

import java.util.Collections;
import java.util.List;


public class ExtractedEssenceYellow extends AbstractCardModifier {
    public static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("InsectGlaiveEssenceNameAndDescription"));

    public static String ID = SpireAnniversary5Mod.makeID("ExtractedEssenceYellow");

    private static Texture texture = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/ui/insectglaivepack/yellow.png");

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof CardForSectionOfIG)
            ExtraIcons.renderIcon(card, texture,
                    AbstractCard.IMG_WIDTH_S * 0.4F / Settings.scale,
                    -AbstractCard.IMG_HEIGHT_S * 0.1F / Settings.scale,
                    Color.WHITE);
        else
            ExtraIcons.renderIcon(card, texture, 0, 0, Color.WHITE);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ExtractedEssenceYellowPower(AbstractDungeon.player)));
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        return Collections.singletonList(new TooltipInfo(UI_STRINGS.TEXT[2], UI_STRINGS.TEXT[5]));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ExtractedEssenceYellow();
    }
}
