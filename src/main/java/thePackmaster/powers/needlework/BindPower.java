package thePackmaster.powers.needlework;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class BindPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("Bind");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private boolean remove = false;

    public BindPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, true, owner, amount);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage - this.amount : damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && owner instanceof AbstractPlayer) {
            this.flash();
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, this.amount));
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (!owner.isPlayer && !remove) {
            this.flash();
            remove = true;
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (remove) {
            addToBot(new ReducePowerAction(this.owner, this.owner, this, this.amount));
        }
    }

    public void updateDescription() {
        if (owner.isPlayer) {
            this.description = String.format(DESCRIPTIONS[0], amount);
        }
        else {
            this.description = String.format(DESCRIPTIONS[1], amount);
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new BindPower(owner, amount);
    }
}
