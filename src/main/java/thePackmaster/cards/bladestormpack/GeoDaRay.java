package thePackmaster.cards.bladestormpack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.powers.bladestormpack.WindrushPower;
import thePackmaster.powers.instadeathpack.Precision;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.bladestormpack.GeoDaRayEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

public class GeoDaRay extends AbstractBladeStormCard {
    public final static String ID = makeID("GeoDaRay");
    private static final int COST = 2;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int WINDRUSH_AND_PRECISION_GAIN = 3;
    private static final int UPG_WINDRUSH_AND_PRECISION_GAIN = 2;

    public GeoDaRay(){
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = WINDRUSH_AND_PRECISION_GAIN;
        isMultiDamage = true;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new GeoDaRayEffect(Color.GRAY, false), 0.0F));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));

        Wiz.doAllDmg(this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, false);
        Wiz.applyToSelf(new WindrushPower(p, magicNumber));
        Wiz.applyToSelf(new Precision(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgradeMagicNumber(UPG_WINDRUSH_AND_PRECISION_GAIN);
    }
}
