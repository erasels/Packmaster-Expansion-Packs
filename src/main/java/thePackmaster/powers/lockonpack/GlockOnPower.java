package thePackmaster.powers.lockonpack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GlockOnPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(GlockOnPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public GlockOnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }


    public void updateDescription() {
        description = amount == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }


    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        AbstractPower focus = AbstractDungeon.player.getPower(FocusPower.POWER_ID);
        if (focus != null) {
            return damage + focus.amount;
        }
        return damage;
    }

    public float atDamageGiveAgainstSpecificFoe(float damage, AbstractMonster foe) {
        if (foe.hasPower(LockOnPower.POWER_ID)) {
            return AbstractOrb.applyLockOn(foe, (int) damage);
        }
        return damage;
    }


    @Override
    public void atEndOfRound() {
        Wiz.reducePower(this);
    }
}
