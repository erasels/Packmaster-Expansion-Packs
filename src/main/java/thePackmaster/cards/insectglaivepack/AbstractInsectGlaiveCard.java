package thePackmaster.cards.insectglaivepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardBorderGlowManager;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceWhite;

import java.util.HashMap;
import java.util.Map;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractInsectGlaiveCard extends AbstractPackmasterCard {
    public AbstractInsectGlaiveCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "insectglaive");

        CardModifierManager.addModifier(this, new AbstractCardModifier() {
            @Override
            public Color getGlow(AbstractCard card) {
                if (InsectGlaivePack.isInCombat() && AbstractDungeon.player.hasPower(ExtractedEssenceWhite.ID))
                    return Color.WHITE.cpy();
                return null;
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
