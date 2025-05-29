package thePackmaster.cards.insectglaivepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceRed;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceWhite;
import thePackmaster.cardmodifiers.insectglaivepack.ExtractedEssenceYellow;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceWhitePower;
import thePackmaster.util.Wiz;

import java.util.Collections;
import java.util.List;

public abstract class AbstractInsectGlaiveCard extends AbstractPackmasterCard {
    public static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("EssenceNoticeMod"));


    public AbstractInsectGlaiveCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "insectglaive");

        CardModifierManager.addModifier(this, new AbstractCardModifier() {


            @Override
            public Color getGlow(AbstractCard card) {
                if (Wiz.isInCombat() && AbstractDungeon.player.hasPower(ExtractedEssenceWhitePower.ID))
                    return Color.WHITE.cpy();
                return null;
            }

            @Override
            public List<TooltipInfo> additionalTooltips(AbstractCard card) {
                if (CardModifierManager.hasModifier(card, ExtractedEssenceRed.ID) ||
                        CardModifierManager.hasModifier(card, ExtractedEssenceYellow.ID) ||
                        CardModifierManager.hasModifier(card, ExtractedEssenceWhite.ID))
                    return null;
                return Collections.singletonList(new TooltipInfo(UI_STRINGS.TEXT[0], UI_STRINGS.TEXT[1]));
            }

            @Override
            public boolean isInherent(AbstractCard card) {
                return true;
            }

            @Override
            public AbstractCardModifier makeCopy() {
                return null;
            }
        });
    }
}
