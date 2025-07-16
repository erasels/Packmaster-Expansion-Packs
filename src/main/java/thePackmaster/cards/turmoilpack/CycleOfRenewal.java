package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.turmoilpack.CycleOfRenewalPower;

public class CycleOfRenewal extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("CycleOfRenewal");
    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    public CycleOfRenewal() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
       this.addToBot(new ApplyPowerAction(p, p, new CycleOfRenewalPower(p, 1)));
    }
}
