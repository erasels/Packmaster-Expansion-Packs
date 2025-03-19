package thePackmaster.powers.doppelpack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.doppelpack.AbstractDoppel;
import thePackmaster.powers.AbstractPackmasterPower;

public class WormHolePower extends AbstractPackmasterPower {
    public static String ID = SpireAnniversary5Mod.makeID(WormHolePower.class.getSimpleName());
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;

    public WormHolePower(AbstractCreature owner, int amount) {
        super(ID, STRINGS.NAME, PowerType.BUFF, false, owner, -1);
    }

    @Override
    public void onInitialApplication() {
        AbstractDungeon.player.orbs.stream()
                .filter(o -> o instanceof AbstractDoppel)
                .forEach(AbstractOrb::updateDescription);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        AbstractDungeon.player.orbs.stream()
                .filter(o -> o instanceof AbstractDoppel)
                .forEach(AbstractOrb::updateDescription);
    }

    @Override
    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], 1);
    }

    public int getEffectAmount() {
        return 1;
    }
}
