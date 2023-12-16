package thePackmaster.patches.royaltypack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "loseGold"
)
public class LoseGoldPatch {
    public interface OnLoseGold {
        void onLoseGold(int goldAmount);
    }

    @SpirePostfixPatch
    public static void loseGold(AbstractPlayer player, int goldAmount) {
        if (!(AbstractDungeon.getCurrRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom))
        {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnLoseGold) {
                    ((OnLoseGold) p).onLoseGold(goldAmount);
                }
            }
            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof OnLoseGold) {
                    ((OnLoseGold) orb).onLoseGold(goldAmount);
                }
            }
        }
    }
}