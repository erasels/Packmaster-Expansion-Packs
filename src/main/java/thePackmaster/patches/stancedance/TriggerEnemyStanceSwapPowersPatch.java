package thePackmaster.patches.stancedance;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.util.Wiz;

public class TriggerEnemyStanceSwapPowersPatch {

    @SpirePatch2(clz = AbstractPlayer.class, method = "switchedStance")
    public static class SwitchedStance {
        @SpirePostfixPatch
        public static void onStanceSwap() {
            for (AbstractMonster q : Wiz.getEnemies()) {
                for (AbstractPower p : q.powers) {
                    if (p instanceof EnemyTriggerOnStanceSwapPower) {
                        ((EnemyTriggerOnStanceSwapPower) p).onPlayerChangeStance();
                    }
                }
            }
        }
    }


}
