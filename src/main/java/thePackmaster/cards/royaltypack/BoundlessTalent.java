package thePackmaster.cards.royaltypack;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.BoundlessTalentAusterity;
import thePackmaster.cards.royaltypack.optioncards.BoundlessTalentTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BoundlessTalent extends AbstractRoyaltyCard {

    public final static String ID = makeID("BoundlessTalent");
    private static final int EXHAUSTIVE = 2;

    public BoundlessTalent() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        baseSecondMagic = secondMagic = 1;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
        this.exhaust = false;
        ExhaustiveVariable.setBaseValue(this, EXHAUSTIVE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractRoyaltyCard btTributeChoiceCard = new BoundlessTalentTribute();
        AbstractRoyaltyCard btAusterityChoiceCard = new BoundlessTalentAusterity(magicNumber);
        for (int i = 0; i < this.timesUpgraded; i++){
            btTributeChoiceCard.upgrade();
            btAusterityChoiceCard.upgrade();
        }

        Wiz.atb(new TributeOrAusterityAction(btTributeChoiceCard, btAusterityChoiceCard));
    }
}
