package thePackmaster.powers.maridebuffpack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HalfBlockPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("HalfBlockPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public HalfBlockPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.DEBUFF,true,owner,amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        if (card.baseBlock >= 0 && !card.cardID.equals(RitualDagger.ID)){
            flash();
            addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
    }

    @Override
    public float modifyBlock(float blockAmount){
        if(blockAmount < 0) return blockAmount;
        return blockAmount/2;
    }

    @Override
    public void updateDescription() {
        if(this.amount == 1){
            description = DESCRIPTIONS[0];
        }else{
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
