package thePackmaster.powers.siegepack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.Objects;

//This buff is the actual shells, boosting damage and consumed (1 per attack).
public class ShellPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID(ShellForgeEffectUpPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public int DAMAGE_BOOST = 5;

    public ShellPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        amount2 = updateBoostValue();
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage + (float)updateBoostValue() : damage;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            //Remove only 1 stack, don't change damage.
            this.addToBot(new ReducePowerAction(this.owner,this.owner, this, 1));
        }
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (!Objects.equals(power.ID, ShellForgeEffectUpPower.POWER_ID)) { return; }
        if (power.owner != this.owner) { return; }
        //if (power.owner != player) { return; }
        updateBoostValue();
    }

    private int updateBoostValue() {
        AbstractPower shellEffectUp = owner.getPower(ShellForgeEffectUpPower.POWER_ID);
        if (shellEffectUp != null) {
            return amount2 = DAMAGE_BOOST + shellEffectUp.amount;
        }
        return amount2 = DAMAGE_BOOST;
    }
}

//Vigor definition (decompiled)
/*
public float atDamageGive(float damage, DamageInfo.DamageType type) {
    return type == DamageInfo.DamageType.NORMAL ? damage + (float)this.amount : damage;
}
public void onUseCard(AbstractCard card, UseCardAction action) {
    if (card.type == AbstractCard.CardType.ATTACK) {
        this.flash();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Vigor"));
    }
}
*/