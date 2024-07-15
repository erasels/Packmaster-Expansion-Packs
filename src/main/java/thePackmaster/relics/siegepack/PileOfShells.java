package thePackmaster.relics.siegepack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.SiegePack;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class PileOfShells extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("PileOfShells");
    public static final int SHELLS = 3;

    public PileOfShells() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, SiegePack.ID);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.setCounter(SHELLS);
        //REFS: DimensionCore (pixiepack), FuelTank (fueledpack)
    }

    @Override
    public void onPlayerEndTurn() {
        if (counter <= 0) { return; }
        counter--;

        //Gain Shell
        if (player == null) { return; }
        Wiz.applyToSelf(new ShellPower(player, 1));
        flash();
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + SHELLS + this.DESCRIPTIONS[1];
    }
}
