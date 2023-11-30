package thePackmaster.cards.maridebuffpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.maridebuffpack.DebuffLossManager;
import thePackmaster.vfx.maridebuffpack.MariTheFlyingCarEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class TheFLYINGCAR extends AbstractMariDebuffCard {
    public final static String ID = makeID("TheFLYINGCAR");

    public TheFLYINGCAR() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = this.damage = 27;
        this.baseMagicNumber = this.magicNumber = 5;
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new MariTheFlyingCarEffect(m.hb.cX, m.hb.cY),1.5F));
        atb(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void applyPowers() {
        int trueBase = this.baseDamage;
        this.baseDamage = trueBase + this.magicNumber* DebuffLossManager.debuffsLostThisCombat;
        super.applyPowers();
        this.baseDamage = trueBase;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int trueBase = this.baseDamage;
        this.baseDamage = trueBase + this.magicNumber* DebuffLossManager.debuffsLostThisCombat;
        super.calculateCardDamage(mo);
        this.baseDamage = trueBase;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }


}


