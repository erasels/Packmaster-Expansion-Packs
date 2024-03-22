package thePackmaster.cards.needleworkpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.needlework.StitchAction;
import thePackmaster.powers.needlework.CrossStitchPower;
import thePackmaster.powers.needlework.PincushionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Pincushion extends AbstractNeedleworkCard {
    public final static String ID = makeID("Pincushion");

    public Pincushion() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL);

        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PincushionPower(p, 1)));
        addToBot(new StitchAction(this));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}
