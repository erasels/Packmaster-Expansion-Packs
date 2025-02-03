package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bladestormpack.WindrushPower;
import thePackmaster.powers.instadeathpack.Precision;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

public class GeoDaRay extends AbstractBladeStormCard {
    public final static String ID = makeID("GeoDaRay");
    private static final int COST = 2;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int WINDRUSH_AND_VIGOR_GAIN = 1;

    public GeoDaRay(){
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = WINDRUSH_AND_VIGOR_GAIN;
        isMultiDamage = true;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Gain buffs once for each enemy.
        int count = Wiz.getEnemies().size();
        Wiz.applyToSelf(new WindrushPower(p, magicNumber * count));
        Wiz.applyToSelf(new Precision(p, magicNumber * count));
        Wiz.doAllDmg(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, false);
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
