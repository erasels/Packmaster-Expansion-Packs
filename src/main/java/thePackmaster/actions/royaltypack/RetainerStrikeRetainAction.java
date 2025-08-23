package thePackmaster.actions.royaltypack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.royaltypack.RetainForOneTurnModifier;
import thePackmaster.util.Wiz;

import java.util.List;
import java.util.UUID;

public class RetainerStrikeRetainAction extends AbstractGameAction {

    private final UUID retainerStrikeIdentifier;
    private final boolean retainerStrikeIsUpgraded;

    private final String selectCardsActionText;

    public RetainerStrikeRetainAction(UUID retainerStrikeIdentifier,
                                      boolean retainerStrikeIsUpgraded,
                                      String selectCardsActionText) {

        this.retainerStrikeIdentifier = retainerStrikeIdentifier;
        this.retainerStrikeIsUpgraded = retainerStrikeIsUpgraded;
        this.selectCardsActionText = selectCardsActionText;
    }

    @Override
    public void update() {
        if (!AbstractDungeon.player.hand.isEmpty()) {

            CardGroup hand = Wiz.p().hand;
            CardGroup handWithoutRetain = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

            for (AbstractCard c : hand.group) {
                if (!c.retain && !c.selfRetain && c.uuid != retainerStrikeIdentifier &&
                        !CardModifierManager.hasModifier(c, RetainForOneTurnModifier.ID))
                    handWithoutRetain.addToHand(c);
            }

            if (handWithoutRetain.size() > 0) {

                if (!this.retainerStrikeIsUpgraded) {
                    AbstractCard card = handWithoutRetain.getRandomCard(AbstractDungeon.cardRandomRng);
                    addModifierAndDoSuperFlash(card);
                } else {
                    if (handWithoutRetain.size() == 1) {
                        AbstractCard card = handWithoutRetain.getRandomCard(AbstractDungeon.cardRandomRng);
                        addModifierAndDoSuperFlash(card);
                    } else {
                        Wiz.atb(new SelectCardsAction(handWithoutRetain.group, selectCardsActionText,
                                (List<AbstractCard> cards) -> {
                                    AbstractCard card = cards.get(0);
                                    addModifierAndDoSuperFlash(card);
                                }
                        ));
                    }
                }
            }
        }

        this.isDone = true;

    }

    private void addModifierAndDoSuperFlash(AbstractCard card) {
        CardModifierManager.addModifier(card, new RetainForOneTurnModifier());
        card.superFlash(Color.YELLOW);
    }


}
