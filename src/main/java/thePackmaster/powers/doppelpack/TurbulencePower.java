package thePackmaster.powers.doppelpack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.doppelpack.AbstractDoppel;
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

    public void onSummoned(AbstractDoppel doppel) {
        this.flash();
        for (int i = 0; i < amount; i++) {
            doppel.onStartOfTurn();
            doppel.onEndOfTurn();
        }
    }
}
