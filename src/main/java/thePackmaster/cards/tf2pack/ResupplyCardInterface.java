package thePackmaster.cards.tf2pack;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface ResupplyCardInterface {
    default public void triggerOnSelfResupply() {}
    default public void triggerOnOtherResupply(AbstractCard card) {}
}
