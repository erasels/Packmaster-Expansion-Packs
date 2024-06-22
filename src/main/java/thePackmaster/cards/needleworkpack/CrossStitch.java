package thePackmaster.cards.needleworkpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.powers.needlework.CrossStitchPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CrossStitch extends AbstractNeedleworkCard {
    public final static String ID = makeID("CrossStitch");

    public CrossStitch() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);

        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
        if (m != null)
            addToBot(new ApplyPowerAction(p, p, new CrossStitchPower(p, m, 1)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
