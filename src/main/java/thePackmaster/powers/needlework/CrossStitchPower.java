package thePackmaster.powers.needlework;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;

public class CrossStitchPower extends FlurryPower implements CloneablePowerInterface {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("CrossStitchPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private final AbstractCreature target;

    public CrossStitchPower(AbstractCreature owner, AbstractCreature target, int amount) {
        super(POWER_ID, NAME, owner, amount);
        this.target = target;
        updateDescription();
    }

    @Override
    public void onFlurry(AbstractCard played, AbstractMonster m) {
        addToBot(new ApplyPowerAction(target, owner, new BindPower(target, this.amount), this.amount, true));
    }

    public void updateDescription() {
        if (target != null)
            this.description = String.format(DESCRIPTIONS[0], amount, FontHelper.colorString(target.name, "y"));
    }

    @Override
    public AbstractPower makeCopy() {
        return new CrossStitchPower(owner, target, amount);
    }
}
