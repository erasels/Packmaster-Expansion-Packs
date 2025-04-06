package thePackmaster.powers.insectglaivepack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class ExtractedEssenceYellow extends AbstractPackmasterPower {
    public static String ID = SpireAnniversary5Mod.makeID("ExtractedEssenceYellow");
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;

    public ExtractedEssenceYellow(AbstractCreature owner) {
        super(ID, STRINGS.NAME, PowerType.BUFF, false, owner, -1);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer)
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public float modifyBlock(float blockAmount) {
        if ((blockAmount += 2) < 0.0F)
            return 0.0F;
        return blockAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
