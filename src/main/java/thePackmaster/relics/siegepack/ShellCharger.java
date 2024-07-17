package thePackmaster.relics.siegepack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.SiegePack;
import thePackmaster.powers.siegepack.ShellForgeEffectUpPower;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class ShellCharger extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("ShellCharger");

    public ShellCharger() {
        super(ID, RelicTier.UNCOMMON, LandingSound.HEAVY, SiegePack.ID);
    }

    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        this.setCounter(0);
    }

    public void atTurnStart() {
        update();   //Seems to fix some visual bugs
    }

    // On "consume a shell" ( = attack with a ShellPower), gain stack of ShellForgeEffectUp.
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (player.hasPower(ShellPower.POWER_ID)) {
            //Parent relic counter starts at -1, bugs tests if relic was instant-gained.
            if (counter < 0) {counter = 0;}

            Wiz.applyToSelf(new ShellForgeEffectUpPower(player, 1));
            counter++;
            flash();
        }
    }

    @Override
    public void onUsePotion() {
        //Do nothing if not in combat, else Crash. REF: ToyOrnithopter (base game)
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            return;
        }
        Wiz.applyToSelf(new ShellPower(player, 1));
        flash();
    }

    @Override
    public void onVictory () {
        this.setCounter(0);
    }
}
