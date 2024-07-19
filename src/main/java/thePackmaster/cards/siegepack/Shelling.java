package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.siegepack.ShellingAction;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;    //ExpansionPacks version gives "MISSING_TITLE!"
import static thePackmaster.cards.siegepack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

//REF: ReboundVolley (intothebreachpack).
public class Shelling extends AbstractSiegeCard {
    public final static String ID = makeID("Shelling");
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int DAMAGE_TO_RANDOM = 3;
    private static final int UPGRADE_DAMAGE_TO_RANDOM = 1;
    private static final int AMOUNT_OF_SHELLS = 1;

    public Shelling(){
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = DAMAGE;
        secondDamage = baseSecondDamage = DAMAGE_TO_RANDOM;
        magicNumber = baseMagicNumber = AMOUNT_OF_SHELLS;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Deal damage to ONE RANDOM OTHER enemy
        atb(new ShellingAction(this, m, 1));
        //Gain 1 Shell
        Wiz.applyToSelf(new ShellPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeSecondDamage(UPGRADE_DAMAGE_TO_RANDOM);
    }

    // This method is used so the second damage value on the card
    // (the "to a random other enemy" one)
    // doesn't change based on the targeted enemy's powers
    // (e.g. Vulnerable)
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.applyPowers();
        int tmp = baseSecondDamage;
        baseSecondDamage = -1;
        super.calculateCardDamage(mo);
        baseSecondDamage = tmp;
    }

    public void superCalculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
    }
}
