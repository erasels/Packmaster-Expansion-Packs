package thePackmaster.cards.siegepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.siegepack.ShellForgeEffectUpPower;
import thePackmaster.powers.siegepack.ShellForgeTurnGainPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShellForge extends AbstractSiegeCard {
    public final static String ID = makeID("ShellForge");
    private static final int COST = 1;
    private static final int SHELL_EFFECT_INCREASE = 2;
    private static final int UPGRADE_SHELL_EFFECT_INCREASE = 2;
    private static final int SHELL_GAIN_PER_TURN = 1;

    public ShellForge() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = SHELL_EFFECT_INCREASE;
        baseSecondMagic = secondMagic = SHELL_GAIN_PER_TURN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ShellForgeEffectUpPower(p, magicNumber));
        Wiz.applyToSelf(new ShellForgeTurnGainPower(p, secondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_SHELL_EFFECT_INCREASE);
    }
}
