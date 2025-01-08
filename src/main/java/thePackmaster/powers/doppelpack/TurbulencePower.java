package thePackmaster.powers.doppelpack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.powers.AbstractPackmasterPower;

public class TurbulencePower extends AbstractPackmasterPower {
    public static String ID = SpireAnniversary5Mod.makeID(TurbulencePower.class.getSimpleName());
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;

    public TurbulencePower(AbstractCreature owner, int amount) {
        super(ID, STRINGS.NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[amount == 1 ? 0 : 1], this.amount);
    }

    @Override
    public void onChannel(AbstractOrb orb) {
        this.flash();
        for (int i = 0; i < amount; i++) {
            if (orb instanceof PackmasterOrb) ((PackmasterOrb) orb).passiveEffect();
            orb.onStartOfTurn();
            orb.onEndOfTurn();
        }
    }
}
