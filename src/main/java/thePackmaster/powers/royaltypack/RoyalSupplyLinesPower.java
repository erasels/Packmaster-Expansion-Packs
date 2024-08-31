package thePackmaster.powers.royaltypack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class RoyalSupplyLinesPower extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("RoyalSupplyLinesPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private final int BASE_CARDS_TO_DRAW = 2;
    private final int BASE_CARDS_TO_RETAIN = 2;

    public RoyalSupplyLinesPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,1);

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + BASE_CARDS_TO_DRAW + DESCRIPTIONS[1];
        this.description += DESCRIPTIONS[2] + BASE_CARDS_TO_RETAIN + DESCRIPTIONS[3];
    }

    @Override
    public void atStartOfTurn(){
        atb(new DrawCardAction(BASE_CARDS_TO_DRAW));
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer){
            CardGroup hand = Wiz.p().hand;
            CardGroup handWithoutRetain = new CardGroup(CardGroup.CardGroupType.HAND);
            for (AbstractCard c: hand.group){
                if (!c.retain && !c.selfRetain) handWithoutRetain.addToHand(c);
            }
            Wiz.atb(new SelectCardsAction(handWithoutRetain.group, 2, "testing",
                    (List<AbstractCard> cards) -> {
                        AbstractCard card = cards.get(0);
                        card.retain = true;
                        card.flash();
                    }
            ));
        }
    }

}
