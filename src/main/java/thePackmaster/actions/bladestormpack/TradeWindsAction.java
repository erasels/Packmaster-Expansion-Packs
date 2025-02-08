package thePackmaster.actions.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/*REFS: AttackVial (womaninbluepack), StrikingStrike (strikepack), Mimicry & Paintbrush (creativitypack),
RazorWind (dragonwrathpack), DualHeal (summonerspellspack), RandomUpgradeWithVfxAction (upgradespack),
NeowReward (base game)*/
public class TradeWindsAction extends AbstractGameAction {
    private final AbstractCreature p;
    private final AbstractCard chosenCard;
    private final int healIfCommon;
    private final int goldLossIfRare;

    public TradeWindsAction(AbstractCreature source, int heal, int goldLoss, AbstractCard chosenCard) {
        this.p = source;
        healIfCommon = heal;
        goldLossIfRare = goldLoss;
        this.chosenCard = chosenCard;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        this.isDone = true;
        //Depending on rarity of the card printed by the Discover calling this Action, heal or lose gold.
        if (chosenCard.rarity == AbstractCard.CardRarity.COMMON) {
            addToTop(new HealAction(p, p, healIfCommon, 0));
        } else if (chosenCard.rarity == AbstractCard.CardRarity.RARE) {
            addToTop(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    AbstractDungeon.player.loseGold(TradeWindsAction.this.goldLossIfRare);  //from NeowReward (base game).
                }
            });
        }
    }
}
