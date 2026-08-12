package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.cellspack.IceArmorPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IceArmor extends AbstractCellsCard {

    public final static String ID = makeID(IceArmor.class.getSimpleName());

    public IceArmor() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doBlk(block);
        addToBot(new ApplyPowerAction(p, p, new IceArmorPower(p, magicNumber)));
    }
}
