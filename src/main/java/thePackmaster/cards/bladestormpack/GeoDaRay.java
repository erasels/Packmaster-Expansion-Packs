package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.powers.bladestormpack.WindrushPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

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

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Gain Windrush and Vigor for each enemy. TEST: does this count enemies killed by the attack?
        for (AbstractMonster mo : Wiz.getEnemies()) {
            Wiz.applyToSelf(new WindrushPower(p, magicNumber));
            Wiz.applyToSelf(new VigorPower(p, magicNumber));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
