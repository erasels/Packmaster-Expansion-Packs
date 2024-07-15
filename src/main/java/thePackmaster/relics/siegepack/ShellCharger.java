package thePackmaster.relics.siegepack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
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
        super(ID, RelicTier.UNCOMMON, LandingSound.CLINK, SiegePack.ID);
    }

    public String getUpdatedDescription()
    {
        return this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1];
    }

    public void atBattleStart() {
        super.atBattleStart();
        this.setCounter(0);
    }

    // On "consume a shell" ( = attack with a ShellPower), gain stack of ShellForgeEffectUp.
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (player == null ) { return; }

        if (player.hasPower(ShellPower.POWER_ID)) {
            Wiz.applyToSelf(new ShellForgeEffectUpPower(player, 1));
            counter++;
            flash();
        }
    }
}
