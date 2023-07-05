package thePackmaster.cards.maridebuffpack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.maridebuffpack.HalfBlockPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class ToughFront extends AbstractMariDebuffCard {
    public final static String ID = makeID("ToughFront");

    public ToughFront() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = 11;
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, p, this.block));
        atb(new ApplyPowerAction(p, p, new HalfBlockPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }


}


