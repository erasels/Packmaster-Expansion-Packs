package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class SuppressAction extends AbstractGameAction {
    private final int strengthReduction;

    public SuppressAction(AbstractMonster m, int strength) {
        this.target = m;
        strengthReduction = strength;
    }

    public void update() {
        if (target == null) {
            isDone = true;
            return;
        }
        // Temporary Strength reduction.
        if (!target.hasPower(ArtifactPower.POWER_ID)) {
            addToTop(new ApplyPowerAction(target, player, new GainStrengthPower(target, strengthReduction), strengthReduction));
        }
        addToTop(new ApplyPowerAction(target, player, new StrengthPower(target, -strengthReduction), -strengthReduction));

        this.isDone = true;
    }
}
