package thePackmaster.cards.infestpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.infestpack.LordOfTheFliesPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class LordOfTheFlies extends AbstractInfestCard {
    public final static String ID = makeID("LordOfTheFlies");
    // intellij stuff power, self, rare, , , , , , 

    public LordOfTheFlies() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LordOfTheFliesPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}