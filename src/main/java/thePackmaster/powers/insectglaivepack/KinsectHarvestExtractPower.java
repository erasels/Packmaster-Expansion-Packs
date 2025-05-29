package thePackmaster.powers.insectglaivepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.AbstractPackmasterPower;

public class KinsectHarvestExtractPower extends AbstractPackmasterPower {
    public static String ID = SpireAnniversary5Mod.makeID("KinsectHarvestExtractPower");
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;

    public int[] colors;

    public KinsectHarvestExtractPower(AbstractCreature owner, int i) {
        super(ID, STRINGS.NAME, PowerType.BUFF, false, owner, -1);
        colors = new int[3];
        colors[i] = 1;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        if (colors[0] == 1) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new ExtractedEssenceRedPower(this.owner)));
        }
        if (colors[1] == 1) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new ExtractedEssenceWhitePower(this.owner)));
        }
        if (colors[2] == 1) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new ExtractedEssenceYellowPower(this.owner)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if(colors != null) {
            if (colors[0] == 1) this.description += DESCRIPTIONS[1];
            if (colors[1] == 1) this.description += DESCRIPTIONS[2];
            if (colors[2] == 1) this.description += DESCRIPTIONS[3];
        }
    }
}
