package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.discopack.SpecificToHandFromDiscardAction;
import thePackmaster.powers.discopack.RobotPower;
import thePackmaster.vfx.discopack.ElectricFX;


import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class TheRobot extends AbstractSmoothCard{
    public static final String ID = makeID("TheRobot");

    public TheRobot() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = magicNumber = 3;
        //this.baseBlock = block = 7;
        this.baseDamage = damage = 5;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new ElectricFX(m, 0.75f, true, 5), 0.33f));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        this.applyToSelf(new RobotPower(p, magicNumber));
    }
    //public void triggerOnManualDiscard() {blck();}

    @Override
    public void upp() {
        this.upgradeDamage(2);
        this.upgradeMagicNumber(1);
    }
}
