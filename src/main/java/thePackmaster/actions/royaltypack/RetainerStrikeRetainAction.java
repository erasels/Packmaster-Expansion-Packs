package thePackmaster.actions.royaltypack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.royaltypack.RetainForOneTurnModifier;
import thePackmaster.cards.royaltypack.RetainerStrike;
import thePackmaster.util.Wiz;

import java.util.List;
import java.util.UUID;

public class RetainerStrikeRetainAction extends AbstractGameAction {

    private UUID retainerStrikeIdentifier;
    private boolean retainerStrikeIsUpgraded;

    private String selectCardsActionText;

    public RetainerStrikeRetainAction(UUID retainerStrikeIdentifier,
                                            boolean retainerStrikeIsUpgraded,
                                            String selectCardsActionText){

        this.retainerStrikeIdentifier = retainerStrikeIdentifier;
        this.retainerStrikeIsUpgraded = retainerStrikeIsUpgraded;
        this.selectCardsActionText = selectCardsActionText;
    }

    @Override
    public void update() {
        if (!AbstractDungeon.player.hand.isEmpty()) {

            CardGroup hand = Wiz.p().hand;
            CardGroup handWithoutRetain = new CardGroup(CardGroup.CardGroupType.HAND);

            for (AbstractCard c: hand.group){
                if (!c.retain && !c.selfRetain && c.uuid != retainerStrikeIdentifier &&
                    !CardModifierManager.hasModifier(c, RetainForOneTurnModifier.ID))
                    handWithoutRetain.addToHand(c);
            }

            if (handWithoutRetain.size() > 0){

                if (!this.retainerStrikeIsUpgraded){
                    AbstractCard toRetain = handWithoutRetain.getRandomCard(AbstractDungeon.cardRandomRng);
                    CardModifierManager.addModifier(toRetain, new RetainForOneTurnModifier());
                    toRetain.flash();
                }
                else {
                    if (handWithoutRetain.size() == 1){
                        AbstractCard card = handWithoutRetain.getRandomCard(AbstractDungeon.cardRandomRng);
                        CardModifierManager.addModifier(card, new RetainForOneTurnModifier());
                        card.flash();
                    } else {
                        Wiz.atb(new SelectCardsAction(handWithoutRetain.group, selectCardsActionText,
                                (List<AbstractCard> cards) -> {
                                    AbstractCard card = cards.get(0);
                                    CardModifierManager.addModifier(card, new RetainForOneTurnModifier());
                                    card.flash();
                                }
                        ));
                    }
                }
            }
        }




        this.isDone = true;

    }





}
