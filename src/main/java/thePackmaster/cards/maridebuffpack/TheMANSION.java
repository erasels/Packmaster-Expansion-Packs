package thePackmaster.cards.maridebuffpack;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.maridebuffpack.TheMANSIONAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class TheMANSION extends AbstractMariDebuffCard {
    public final static String ID = makeID("TheMANSION");

    public TheMANSION() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = this.block = 30;
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, p, this.block));
        atb(new TheMANSIONAction());
    }

    @Override
    public void upp() {
        upgradeBlock(6);
    }


}


