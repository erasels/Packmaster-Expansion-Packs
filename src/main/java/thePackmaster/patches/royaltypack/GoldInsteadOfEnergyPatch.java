package thePackmaster.patches.royaltypack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.powers.overwhelmingpack.FreeSkillPower;
import thePackmaster.powers.royaltypack.HiredSupportPower;

@SpirePatch(
        clz = AbstractCard.class,
        method = "freeToPlay"
)

public class GoldInsteadOfEnergyPatch {

    @SpirePostfixPatch
    public static boolean makeCardsUsableWithoutEnergyForHiredSupport(boolean __result, AbstractCard __instance) {
        if (!__result) {
            return AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                    AbstractDungeon.player.hasPower(HiredSupportPower.POWER_ID) &&
                    __instance.costForTurn * 10 <= AbstractDungeon.player.gold;
        }
        return true;
    }

}
