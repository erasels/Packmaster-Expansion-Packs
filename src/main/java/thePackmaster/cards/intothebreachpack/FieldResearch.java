package thePackmaster.cards.intothebreachpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.intothebreachpack.FieldResearchPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class FieldResearch extends IntoTheBreachCard {
    public final static String ID = makeID("FieldResearch");

    public FieldResearch() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FieldResearchPower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}
