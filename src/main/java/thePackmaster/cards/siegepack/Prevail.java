package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.siegepack.PrevailPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Prevail extends AbstractSiegeCard {
    public final static String ID = makeID("Prevail");
    private static final int COST = 0;
    private static final int STRENGTH_LOSS_ON_HIT = 2;
    private static final int UPGRADE_STRENGTH_LOSS_ON_HIT = 1;

    public Prevail() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = STRENGTH_LOSS_ON_HIT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PrevailPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_STRENGTH_LOSS_ON_HIT);
    }
}
