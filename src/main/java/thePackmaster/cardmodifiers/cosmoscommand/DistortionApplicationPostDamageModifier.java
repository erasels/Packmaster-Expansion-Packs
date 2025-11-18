package thePackmaster.cardmodifiers.cosmoscommand;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.distortionpack.DistortionPower;
import thePackmaster.util.Wiz;

public class DistortionApplicationPostDamageModifier extends AbstractDamageModifier {
    public static final String ID = SpireAnniversary5Mod.makeID("DistortionApplicationPostDamageModifier");
    public float multiplier;

    public DistortionApplicationPostDamageModifier(boolean autoBind, float multiplier) {
        this.automaticBindingForCards = autoBind;
        this.multiplier = multiplier;
    }

    @Override
    public void onLastDamageTakenUpdate(DamageInfo info, int lastDamageTaken, int overkillAmount, AbstractCreature targetHit) {
        if (lastDamageTaken > 0) {
            int amount = (int) (lastDamageTaken * multiplier);
            if (amount > 0) {
                Wiz.applyToEnemyTop((AbstractMonster) targetHit, new DistortionPower(targetHit, info.owner, amount));
            }
        }
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new DistortionApplicationPostDamageModifier(automaticBindingForCards, multiplier);
    }

    public boolean isInherent() {
        return true;
    }
}
