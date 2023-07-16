package thePackmaster.cards.maridebuffpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class PentupAnger extends AbstractMariDebuffCard {
    public final static String ID = makeID("PentupAnger");

    public PentupAnger() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 11;
        this.baseMagicNumber = this.magicNumber = 2;
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        atb(new ReducePowerAction(p, p, FrailPower.POWER_ID, this.magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        AbstractPlayer p = AbstractDungeon.player;
        atb(new ApplyPowerAction(p, p, new FrailPower(p, 1, false), 1));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }


}


