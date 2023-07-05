package thePackmaster.cards.maridebuffpack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.powers.maridebuffpack.DenouementPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Denouement extends AbstractMariDebuffCard {
    public final static String ID = makeID("Denouement");

    public Denouement() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 2;
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
        atb(new ApplyPowerAction(p, p, new DenouementPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }


}


