package thePackmaster.powers.discopack;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class FastMovementsPower extends AbstractPackmasterPower implements DiscardHookPatch.OnDiscardThing{
    public static final String POWER_ID = makeID("FastMovementsPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public FastMovementsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        this.isTwoAmount = true;
        amount2 = 0;
        updateDescription();
    }


    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + amount2 +DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurnPostDraw(){
        atb(new VFXAction(new FlameBarrierEffect(owner.hb.cX, owner.hb.cY)));
        applyToSelf(new VigorPower(owner, amount2));
        amount2 = 0;
        updateDescription();
    }

    @Override
    public void onManualDiscardThing() {
        amount2 += amount;
        updateDescription();
    }
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        amount2 += amount;
        updateDescription();
    }
}
