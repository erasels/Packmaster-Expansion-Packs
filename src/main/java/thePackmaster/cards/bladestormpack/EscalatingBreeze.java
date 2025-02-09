package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bladestormpack.EscalatingBreezePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

/*REFS: Crusher (dimensiongatepack2), Paintball (monsterhunterpack), WellWrittenScript (grandopeningpack),
TempRetainCardsPower & RetainCardsAction (base game), Rummage (packmaster)*/
public class EscalatingBreeze extends AbstractBladeStormCard {
    public final static String ID = makeID("EscalatingBreeze");
    private static final int COST = 0;
    private static final int RETAINED_CARDS = 1;
    private static final int UPG_RETAINED_CARDS = 1;
    private static final int COST_REDUCTION = 2;

    public EscalatingBreeze() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = RETAINED_CARDS;
        baseSecondMagic = secondMagic = COST_REDUCTION;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EscalatingBreezePower(p, magicNumber, secondMagic)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_RETAINED_CARDS);
    }
}
