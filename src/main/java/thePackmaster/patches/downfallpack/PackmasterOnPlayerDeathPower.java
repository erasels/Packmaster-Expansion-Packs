package thePackmaster.patches.downfallpack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

// We've had to make our own version of StsLib's OnPlayerDeathPower because the StsLib
// version triggers after base game on death relics such as Lizard Tail, which is never
// what the player wants. While there's agreement that this is undesired and that it
// would be best to change StsLib, StsLib doesn't seem to be getting updated much these
// days, thus our implementation.
public interface PackmasterOnPlayerDeathPower {
    /**
     * @return       Whether or not to kill the player (true = kill, false = live)
     */
    boolean onPlayerDeath(AbstractPlayer p, DamageInfo damageInfo);
}
