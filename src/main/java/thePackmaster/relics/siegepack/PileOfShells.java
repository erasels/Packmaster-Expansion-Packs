package thePackmaster.relics.siegepack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.SiegePack;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//REFS: DimensionCore (pixiepack), FuelTank (fueledpack)
public class PileOfShells extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("PileOfShells");
    public static final int SHELLS = 3;

    public PileOfShells() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, SiegePack.ID);
    }

    @Override
    public void onEquip() {
        this.setCounter(SHELLS);
    }

    @Override
    public void atBattleStart() {
        this.setCounter(SHELLS);
    }

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
        this.setCounter(SHELLS);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + SHELLS + this.DESCRIPTIONS[1];
    }
}
