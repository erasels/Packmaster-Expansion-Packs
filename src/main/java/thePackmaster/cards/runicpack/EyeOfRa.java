package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.powers.runicpack.AttackFocusLoss;
import thePackmaster.stances.runicpack.RunicStance;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EyeOfRa extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int MAGIC = 2;
    public final static String ID = makeID("EyeOfRa");


    public EyeOfRa() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new ChangeStanceAction(new RunicStance()));
        Wiz.atb(new ApplyPowerAction(abstractPlayer, abstractPlayer, new FocusPower(abstractPlayer, magicNumber), magicNumber));
        Wiz.atb(new ApplyPowerAction(abstractPlayer, abstractPlayer, new AttackFocusLoss(abstractPlayer, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
