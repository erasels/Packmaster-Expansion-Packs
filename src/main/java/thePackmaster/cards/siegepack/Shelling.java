package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.siegepack.FeralDamage;
import thePackmaster.cardmodifiers.siegepack.FrontDamage;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Shelling extends AbstractSiegeCard {

    public final static String ID = makeID(Shelling.class.getSimpleName());

    private static final int COST = 1;

    public Shelling(){
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 2;
        //Add damage to random enemy
        //Add Gain 1 Shell

        //REFS
        //DamageModifierManager.addModifier(this, new FeralDamage());
        
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
