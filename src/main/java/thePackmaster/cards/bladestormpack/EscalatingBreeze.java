package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.bladestormpack.EscalatingBreezeAction;
import thePackmaster.powers.bladestormpack.EscalatingBreezePower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

/*REFS: Crusher (dimensiongatepack2), Paintball (monsterhunterpack), WellWrittenScript (grandopeningpack),
TempRetainCardsPower & RetainCardsAction (base game), Rummage (packmaster),
Upgrade and give Retain : Beacon (odditiespack)
Retain your hand : Equilibrium (base game), TheSuckamidRareSkill (pinnaclepack), Vision (ringofpainpack)
Effect ALL Attacks : OpeningStrike (overwhelmingpack)
*/
public class EscalatingBreeze extends AbstractBladeStormCard {
    public final static String ID = makeID("EscalatingBreeze");
    private static final int COST = 2;
    private static final int UPG_COST = 1;
    private static final int DURATION_IN_TURNS = 1;

    public EscalatingBreeze() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = DURATION_IN_TURNS;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Gain 1 [E] next turn per Attack (in hand right now).
        addToBot(new EscalatingBreezeAction(player));

        //Retain all Attacks for 1 turn.
        addToBot(new ApplyPowerAction(p, p, new EscalatingBreezePower(p, magicNumber)));
    }

    @Override
    public void upp() { upgradeBaseCost(UPG_COST); }
}
