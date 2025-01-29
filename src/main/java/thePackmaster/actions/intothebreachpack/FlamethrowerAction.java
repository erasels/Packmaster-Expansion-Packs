package thePackmaster.actions.intothebreachpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.shamanpack.IgnitePower;

import java.util.ArrayList;
import java.util.Collections;

import static thePackmaster.util.Wiz.*;

public class FlamethrowerAction extends AbstractGameAction {
    @Override
    public void update() {
        ArrayList<AbstractMonster> monsters = getEnemies();
        // Reverse the list since we are adding to top i.e. actions are added backwards
        Collections.reverse(monsters);

        for (AbstractMonster mo : monsters) {
            AbstractPower ignite = mo.getPower(IgnitePower.POWER_ID);
            if (ignite != null) {
                att(new LoseHPAction(mo, adp(), ignite.amount, AttackEffect.FIRE));
            }
        }

        this.isDone = true;
    }
}
