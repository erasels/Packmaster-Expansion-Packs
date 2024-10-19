package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.siegepack.SuppressAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

public class Suppress extends AbstractSiegeCard {
    public final static String ID = makeID("Suppress");
    private static final int COST = 2;
    private static final int DAMAGE = 11;
    private static final int WEAK = 1;
    private static final int CONDITIONAL_STRENGTH_DOWN = 3;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int UPGRADE_WEAK = 1;

    public Suppress() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = WEAK;
        baseSecondMagic = secondMagic = CONDITIONAL_STRENGTH_DOWN;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);

        // Applies Weak
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
        // Applies temporary Strength reduction.
        addToBot(new SuppressAction(m, secondMagic));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPGRADE_WEAK);
    }
}