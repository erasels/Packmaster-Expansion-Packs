package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

public abstract class AbstractShowmanCard extends AbstractPackmasterCard
{
    public AbstractShowmanCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "showman");

    }

    public AbstractShowmanCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    public static void postExhaustTrigger(AbstractCard exhausted) {
        for(AbstractCard c : Wiz.hand().group) {
            if(c instanceof SmokeAndMirrors) {
                ((SmokeAndMirrors) c).triggerExhaustIncreaseBlock();
            }
        }
    }
}