package thePackmaster.powers.insectglaivepack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.AbstractPackmasterPower;

public class ExtractedEssenceRedPower extends AbstractPackmasterPower {
    public static String ID = SpireAnniversary5Mod.makeID("ExtractedEssenceRedPower");
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;

    public ExtractedEssenceRedPower(AbstractCreature owner) {
        super(ID, STRINGS.NAME, PowerType.BUFF, false, owner, -1);
    }

    @Override
    public void onInitialApplication() {
        InsectGlaivePack.movePowerPosition(this);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL)
            return damage + 2;
        return damage;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer)
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
