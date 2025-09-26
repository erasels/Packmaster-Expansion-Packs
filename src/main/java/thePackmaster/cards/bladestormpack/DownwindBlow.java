package thePackmaster.cards.bladestormpack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import thePackmaster.powers.instadeathpack.CloudPower;
import thePackmaster.powers.instadeathpack.Precision;
import thePackmaster.vfx.bladestormpack.ColoredSmokeBombEffect;
import thePackmaster.vfx.bladestormpack.DownwindBlowEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

//REFS: DramaticExit (showmanpack), Cloud (instadeathpack), TabooDrop (Professor mod).
public class DownwindBlow extends AbstractBladeStormCard {
    public final static String ID = makeID("DownwindBlow");
    private static final int COST = 3;
    private static final int DAMAGE = 18;
    private static final int UPG_DAMAGE = 5;
    private static final int PRECISION = 7;
    private static final int UPG_PRECISION = 3;

    public DownwindBlow() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = PRECISION;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new ColoredSmokeBombEffect(p.hb.cX, p.hb.cY, Color.SKY)));
        if (m != null) {
            addToBot(new VFXAction(new DownwindBlowEffect(m.hb.cX, m.hb.cY, Color.LIGHT_GRAY)));
        }
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new WaitAction(0.35f));
        if (m != null) {
            this.addToBot(new VFXAction(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F));
        }

        dmg(m, AbstractGameAction.AttackEffect.NONE);

        addToBot(new ApplyPowerAction(p, p, new Precision(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new CloudPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_PRECISION);
    }
}
