package thePackmaster.powers.bladestormpack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

//REFS: BufferPower & TungstenRod relic (base game), ConstructPower (entropypack)
public class WindrushPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("WindrushPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public WindrushPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);

        this.priority = -42068; //ConstructPower: -42069, BufferPower: 5. Triggers after Construct, and protects Buffer.

    }

    //Based on BufferPower, which does not affect enemy intents (that results in over-mitigation).
    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.NORMAL) { return damageAmount; }

        //Block DID already reduce damage by this point. Game pretends blocked damage never happened.
        if (damageAmount > 0) {    //So this is only unblocked damage.
            this.flashWithoutSound();
            return Math.max(0, damageAmount - this.amount);
        }
        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}