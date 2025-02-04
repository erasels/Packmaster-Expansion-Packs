package thePackmaster.actions.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

/*REFS: AttackVial (womaninbluepack), StrikingStrike (strikepack), Mimicry & Paintbrush (creativitypack),
RazorWind (dragonwrathpack), DualHeal (summonerspellspack), RandomUpgradeWithVfxAction (upgradespack) */
public class TradeWindsAction extends AbstractGameAction {
    private final AbstractCreature p;
    private final AbstractCard chosenCard;
    private final int HEAL_IF_COMMON;
    private final int DAMAGE_IF_RARE;
    private final boolean upgradeIt;

    public TradeWindsAction(AbstractCreature source, int heal, int damage, AbstractCard chosenCard, boolean upgradeIt) {
        this.p = source;
        HEAL_IF_COMMON = heal;
        DAMAGE_IF_RARE = damage;
        this.chosenCard = chosenCard;
        this.actionType = ActionType.SPECIAL;
        this.upgradeIt = upgradeIt;
    }

    public void update() {
        this.isDone = true;
        //Depending on rarity of the card printed by the Discover calling this Action, heal or damage self.
        if (chosenCard.rarity == AbstractCard.CardRarity.COMMON) {
            addToTop(new HealAction(p, p, HEAL_IF_COMMON, 0));
        } else if (chosenCard.rarity == AbstractCard.CardRarity.RARE) {
            this.addToTop(new DamageAction(p, new DamageInfo(p, DAMAGE_IF_RARE, DamageInfo.DamageType.THORNS)));
        }
        if (upgradeIt) { chosenCard.upgrade(); }
    }
}
