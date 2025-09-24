package thePackmaster.cards.warriorpack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import thePackmaster.cardmodifiers.warriorpack.FastDamage;
import thePackmaster.cardmodifiers.warriorpack.FrontDamage;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Rapier extends AbstractWarriorCard {

    public final static String ID = makeID(Rapier.class.getSimpleName());

    private static final int COST = 0;

    public Rapier(){
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 5;
        DamageModifierManager.addModifier(this, new FrontDamage());
        DamageModifierManager.addModifier(this, new FastDamage());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            this.addToTop(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
