package thePackmaster.cards.needleworkpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.needlework.StitchAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Backstitch extends AbstractNeedleworkCard {
    public final static String ID = makeID("Backstitch");

    public Backstitch() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);

        baseBlock = block = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new StitchAction(this));
    }

    @Override
    public void upp() {
        upgradeBlock(1);
    }
}
