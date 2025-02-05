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
    private final int HEAL_IF_COMMON;
    private final int GOLD_LOSS_IF_RARE;
    private final boolean upgradeIt;

    public TradeWindsAction(AbstractCreature source, int heal, int goldLoss, AbstractCard chosenCard, boolean upgradeIt) {
        this.p = source;
        HEAL_IF_COMMON = heal;
        GOLD_LOSS_IF_RARE = goldLoss;
        this.chosenCard = chosenCard;
        this.actionType = ActionType.SPECIAL;
        this.upgradeIt = upgradeIt;
    }

    public void update() {
        this.isDone = true;
        //Depending on rarity of the card printed by the Discover calling this Action, heal or lose gold.
        if (chosenCard.rarity == AbstractCard.CardRarity.COMMON) {
            addToTop(new HealAction(p, p, HEAL_IF_COMMON, 0));
        } else if (chosenCard.rarity == AbstractCard.CardRarity.RARE) {
            AbstractDungeon.player.loseGold(GOLD_LOSS_IF_RARE);     //from NeowReward.
        }
        if (upgradeIt) { chosenCard.upgrade(); }
    }
}
