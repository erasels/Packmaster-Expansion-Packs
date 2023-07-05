package thePackmaster.util.maridebuffpack;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

// simple utility class to allow the tracking of player debuffs being FULLY lost
public class DebuffLossManager {
    public static int debuffsLostThisCombat;
    public static ArrayList<String> activeDebuffIds = new ArrayList<>();

    public static void resetDebuffTracker(){
        activeDebuffIds.clear();
        debuffsLostThisCombat = 0;
    }

    public static void onPowersModified(){
        ArrayList<String> currentDebuffIds = new ArrayList<>();
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p.type == AbstractPower.PowerType.DEBUFF){
                currentDebuffIds.add(p.ID);
            }
        }

        if(activeDebuffIds.size() > 0){
            activeDebuffIds.removeAll(currentDebuffIds);
            for (int i = 0; i < activeDebuffIds.size(); i++) {
                onDebuffLost();
            }
        }
        activeDebuffIds = currentDebuffIds;
    }

    public static void onDebuffLost(){
        debuffsLostThisCombat++;
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p instanceof OnDebuffLossPower){
                ((OnDebuffLossPower) p).onDebuffLoss();
            }
        }
    }
}
