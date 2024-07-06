package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.siegepack.ShrapnelPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

//ShrapnelPower and ShrapnelAction classes do the heavy lifting.
public class Shrapnel extends AbstractSiegeCard {
    public final static String ID = makeID("Shrapnel");
    private static final int COST = 1;

    public Shrapnel() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction (p, p, new ShrapnelPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {isInnate = true;}
}
