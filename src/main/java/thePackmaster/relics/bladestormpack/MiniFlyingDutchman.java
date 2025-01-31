package thePackmaster.relics.bladestormpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.BladeStormPack;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class MiniFlyingDutchman  extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("MiniFlyingDutchman");
    public static final int WINDRUSH_GAIN = 1;
    public static final int ATTACKS = 2;

    public MiniFlyingDutchman() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, BladeStormPack.ID);
    }

    @Override
    public void onEquip() {
        this.setCounter(ATTACKS);
    }

//    @Override
//    public void atBattleStart() {
//        this.setCounter(ATTACKS);
//    }

    @Override
    public void atTurnStart() { this.setCounter(ATTACKS); }

    @Override
    public void onPlayerEndTurn() {
        if (counter <= 0) { return; }
        counter--;

        //Gain Shell
        Wiz.applyToSelf(new ShellPower(player, 1));
        flash();
        if (counter <= 0) {
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory () {
        this.grayscale = false;
        this.setCounter(ATTACKS);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + ATTACKS + this.DESCRIPTIONS[1];
    }
}
