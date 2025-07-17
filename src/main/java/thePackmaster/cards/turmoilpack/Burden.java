package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;

public class Burden extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Burden");
    private static final int COST = 2;
    private static final int STRENGTH = 2;
    private static final int UPGRADE_STRENGTH = 1;

    public Burden() {
        super(ID, COST, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        this.magicNumber = this.baseMagicNumber = STRENGTH;
        this.selfRetain = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_STRENGTH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
    }
}
