package thePackmaster.patches.tf2pack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatches;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.tf2pack.ResupplyCardInterface;
import thePackmaster.powers.tf2pack.ResupplyPowerInterface;

@SpirePatches({@SpirePatch(
        clz = CardGroup.class,
        method = "addToHand"
), @SpirePatch(
        clz = CardGroup.class,
        method = "addToTop"
), @SpirePatch(
        clz = CardGroup.class,
        method = "addToBottom"
), @SpirePatch(
        clz = CardGroup.class,
        method = "addToRandomSpot"
)})
public class TriggerOnResupplyPatch {
    public TriggerOnResupplyPatch() {
    }

    @SpirePostfixPatch
    public static void trigger(CardGroup __instance, AbstractCard c) {
        // check if player is null to make sure you're in battle
        // check if the card is being added to hand pile specifically
        // check if it is the middle of the turn
        // the end turn button is actually a pretty reliable indicator of when the player interactive phase of the turn begins and ends
        if (AbstractDungeon.player == null || __instance != AbstractDungeon.player.hand || !AbstractDungeon.overlayMenu.endTurnButton.enabled)
            return;
        if (c instanceof ResupplyCardInterface)
            ((ResupplyCardInterface)c).triggerOnSelfResupply();
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof ResupplyPowerInterface) {
                ((ResupplyPowerInterface)power).triggerOnAnyResupply(c);
            }
        }
        for (AbstractCard handCard : AbstractDungeon.player.hand.group) {
            if (handCard instanceof ResupplyCardInterface && c != handCard) {
                ((ResupplyCardInterface)handCard).triggerOnOtherResupply(c);
            }
        }
    }
}
