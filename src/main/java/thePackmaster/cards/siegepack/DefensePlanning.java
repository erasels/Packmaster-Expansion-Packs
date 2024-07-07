package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class DefensePlanning extends AbstractSiegeCard {
    public final static String ID = makeID("DefensePlanning");
    private static final int COST = 1;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 3;

    public DefensePlanning() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();

        // Gain 1 Blur if none present.
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (!player.hasPower(BlurPower.POWER_ID)) {
                    Wiz.applyToSelfTop(new BlurPower(player, 1));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}

/*
REFS (all cards to be made, not just this one) :

    Detect "enemy intends to attack" :
        Batter : BatterUp
        Utility : Preemptive Strike
        RingOfPain : Skittering Strike

    Detect "Unblocked" :
        Aggression : Slam
        Bardic Inspiration : LifeDrain !

    Enemy loses Strength temporarily :
        Aggression : Decapitate
        Quiet : Lie

    Deal 2x damage if enemy fulfills condition :
        ConjurerPetra : Mud Fissure (if has debuffs)
        MonsterHunter : HuntingStrike (if is elite/boss)

    Attack applies Ignite based on damage dealt :
        Marisa : LuminousStrike

    Apply Ignite if condition fulfilled :
        Shaman : FlameBlast (if enemy Vulnerable)

    Attacks are modified and apply something :
        serpentinepack : Venomous stance. (Venomous Sheath, Toxicology, Sinister Concoction)

    Apply Weak :
        Hermit : Glare
        Needlework : Cross Stitch
        Secret Level : Joust

    At the start of your turn, gain 1+ stack(s) of an attack buff :
        Marisa : Event Horizon
        Bardic Inspiration : Splendid Form

    At the start of your turn, gain Energy :
        Energy & Echo : Slowing
        Energized : Storm Form

    At the start/end of your turn but conditional :
        Board Games : PerceptionCheck
        MadScienceMod :-(   : PowerArmor

    Damage & damage to random OTHER enemy :
        Into the Breach : Rebound Volley (literally the only reference, and complicated).

    Next turn effect :
        Core set : Showoff (but it's draw)

    Gain any amount of a pack-specific (attack damage, preferably) buff:
        Dragonwrath : RazorWind
        RimWorld : Luciferium
        The Swarm : ScarabShield (conditional, Artifact is base-game too)

    Gain Blur :
        (Found no card-based examples in PM)

    Create a power buff on self :
        :

    Gain Block per enemy :
        Arcana : Justice

    When attack deals "at least" X :
        Weapons : PirateHook (only itself)
        Weapons : ArmorUp (POWER!)

    Gain Strength and Dexterity :
        Basics : Familiar kit
        Frosty Fun : Snack
        Oddities : ShiningStyle

    Double damage debuff on enemy :
        Into the Breach : ACID projector

    Gain energy (conditional) :
        summonerspellspack in "actions" folder : Flash
 */