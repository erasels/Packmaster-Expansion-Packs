package thePackmaster.cards.doppelpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.HashMap;
import java.util.Map;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractDoppelCard extends AbstractPackmasterCard {

    protected boolean previewDoppel = false;
    protected Map<AbstractCard, ShowDoppel> affectedCards;

    public AbstractDoppelCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "doppel");
    }

    @Override
    public void hover() {
        super.hover();
        if (previewDoppel && AbstractDungeon.player != null) {
            AbstractPlayer player = AbstractDungeon.player;
            if (player.hand.contains(this)) {
                if (affectedCards == null) {
                    affectedCards = new HashMap<>();
                }
                for (AbstractCard card : player.hand.group) {
                    if (card == this || affectedCards.containsKey(card)) {
                        continue;
                    }
                    ShowDoppel showDoppel = new ShowDoppel();
                    affectedCards.put(card, showDoppel);
                    CardModifierManager.addModifier(card, showDoppel);
                }
            }
        }
    }

    @Override
    public void unhover() {
        super.unhover();
        if (affectedCards != null) {
            for (Map.Entry<AbstractCard, ShowDoppel> entry : affectedCards.entrySet()) {
                CardModifierManager.removeSpecificModifier(entry.getKey(), entry.getValue(), false);
            }
            affectedCards = null;
        }
    }

    protected static CardStrings getCardStrings(String id) {
        return languagePack.getCardStrings(id);
    }
}
