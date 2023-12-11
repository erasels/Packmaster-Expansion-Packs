package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Reiji extends AbstractLockonCard {

    public final static String ID = makeID(Reiji.class.getSimpleName());

    public Reiji() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        magicNumber = baseMagicNumber = 0;
        secondMagic = baseSecondMagic = 3;
        isInnate = true;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AllEnemyApplyPowerAction(p, magicNumber, (mon) -> new LockOnPower(mon, secondMagic)));
        if (magicNumber > 0) {
            Wiz.forAllMonstersLiving(mon -> {
                if(mon.hasPower(LockOnPower.POWER_ID))
                    addToBot(new ApplyPowerAction(p, p, new FocusPower(p, magicNumber)));
            });
        }
    }

}
