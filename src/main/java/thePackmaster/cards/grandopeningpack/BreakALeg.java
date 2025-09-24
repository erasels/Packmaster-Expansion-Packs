package thePackmaster.cards.grandopeningpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class BreakALeg extends AbstractGrandOpeningCard implements StartupCard {
    public final static String ID = makeID("BreakALeg");

    public BreakALeg() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new ApplyPowerAction(m, p, new VulnerablePower(m, secondMagic, false)));
    }

    @Override
    public boolean atBattleStartPreDraw() {
        addToTop(new GainEnergyAction(magicNumber));
        return true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}