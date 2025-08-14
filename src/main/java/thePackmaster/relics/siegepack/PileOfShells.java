package thePackmaster.relics.siegepack;

import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.SiegePack;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//REFS: DimensionCore (pixiepack), FuelTank (fueledpack)
public class PileOfShells extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("PileOfShells");
    public static final int VIGOR_DELIVERIES = 3;

    public PileOfShells() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, SiegePack.ID);
    }

    @Override
    public void onEquip() {
        this.setCounter(VIGOR_DELIVERIES);
    }

    @Override
    public void atBattleStart() {
        this.setCounter(VIGOR_DELIVERIES);
    }

    @Override
    public void onPlayerEndTurn() {
        if (counter <= 0) { return; }
        counter--;

        //Gain Shell
        Wiz.applyToSelf(new VigorPower(player, 5));
        flash();
        if (counter <= 0) {
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory () {
        this.grayscale = false;
        this.setCounter(VIGOR_DELIVERIES);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + VIGOR_DELIVERIES + this.DESCRIPTIONS[1];
    }
}
