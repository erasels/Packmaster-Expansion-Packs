package thePackmaster;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.evacipated.cardcrawl.mod.stslib.patches.CustomTargeting;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.CalmStance;
import thePackmaster.hats.HatMenu;
import thePackmaster.hats.specialhats.InstantDeathHat;
import thePackmaster.packs.CthulhuPack;
import thePackmaster.packs.FrostPack;
import thePackmaster.packs.InstantDeathPack;
import thePackmaster.packs.SpheresPack;
import thePackmaster.patches._expansionpacks.RelicParentPackExpansionPatches;
import thePackmaster.patches.overwhelmingpack.MakeRoomPatch;
import thePackmaster.patches.sneckopack.EnergyCountPatch;
import thePackmaster.relics.summonspack.BlueSkull;
import thePackmaster.stances.aggressionpack.AggressionStance;
import thePackmaster.stances.cthulhupack.NightmareStance;
import thePackmaster.stances.downfallpack.AncientStance;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.stances.sentinelpack.Serene;
import thePackmaster.util.maridebuffpack.DebuffLossManager;

import java.util.*;

import static thePackmaster.util.Wiz.p;

@SpireInitializer
public class ExpansionPacks implements
        PostInitializeSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber,
        OnPlayerLoseBlockSubscriber,
        OnPowersModifiedSubscriber,
        AddAudioSubscriber {

    private static ExpansionPacks thismod;
    public static final String modID = "expansionPacks";

    //You shouldn't be making much use of this. Use the SpireAnniversary5Mod class instead
    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public ExpansionPacks() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        thismod = new ExpansionPacks();
    }

    @Override
    public void receivePostInitialize() {
        HatMenu.specialHats.put(InstantDeathPack.ID, new InstantDeathHat());

        //Please add your pack IDs to the relics from PM here
        HashMap<String, List<String>> relicParentPackMap = RelicParentPackExpansionPatches.pmRelicParentExpansions;
        relicParentPackMap.put(BlueSkull.ID, Arrays.asList(SpheresPack.ID, FrostPack.ID));

        // Custom Targeting Register
        CustomTargeting.registerCustomTargeting(SelfOrEnemyTargeting.SELF_OR_ENEMY, new SelfOrEnemyTargeting());
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        MakeRoomPatch.reset();
        EnergyCountPatch.energySpentThisCombat = 0;
        CthulhuPack.lunacyThisCombat = 0;
        DebuffLossManager.resetDebuffTracker(); // MariDebuffPack
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        MakeRoomPatch.reset();
    }

    @Override
    public int receiveOnPlayerLoseBlock(int i) {
        i = Serene.receiveOnPlayerLoseBlock(i);
        return i;
    }

    @Override
    public void receivePowersModified() {
        // MariDebuffPack: Tracking player power list after each modification
        // cannot only track when powers are removed due to cases such as negative strength becoming positive
        DebuffLossManager.onPowersModified();
    }

    public static AbstractStance getPackmasterStanceInstance(boolean useCardRng) {
        String stance = getPackmasterStance(useCardRng);

        //Is there a cleaner way to do this without instantiating an arraylist of stances objects?
        //Case can't use .STANCE_ID

        if (Objects.equals(stance, Angry.STANCE_ID)) {
            return new Angry();
        } else if (Objects.equals(stance, CalmStance.STANCE_ID)) {
            return new CalmStance();
        } else if (Objects.equals(stance, Serene.STANCE_ID)) {
            return new Serene();
        } else if (Objects.equals(stance, AncientStance.STANCE_ID)) {
            return new AncientStance();
        } else if (Objects.equals(stance, AggressionStance.STANCE_ID)) {
            return new AggressionStance();
        } else {

            return new NightmareStance();
        }

    }

    public static String getPackmasterStance(boolean useCardRng) {
        ArrayList<String> stances = new ArrayList<>();
        stances.add(Angry.STANCE_ID);
        stances.add(Serene.STANCE_ID);
        stances.add(CalmStance.STANCE_ID);
        stances.add(AncientStance.STANCE_ID);
        stances.add(AggressionStance.STANCE_ID);
        stances.add(NightmareStance.STANCE_ID);

        stances.remove(p().stance.ID);

        return useCardRng ? stances.get(AbstractDungeon.cardRandomRng.random(stances.size() - 1)) : stances.get(MathUtils.random(stances.size() - 1));
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio(SpireAnniversary5Mod.makeID("MariDebuffPack_TheFLYINGCAR"), SpireAnniversary5Mod.makePath("audio/maridebuffpack/MariTheFlyingCar.ogg"));
    }
}
