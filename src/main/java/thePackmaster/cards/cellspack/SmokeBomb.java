package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.getEnemies;

public class SmokeBomb extends AbstractCellsCard {
    public final static String ID = makeID("SmokeBomb");

    public SmokeBomb() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        block = baseBlock = 7;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (AbstractMonster mo : getEnemies()) {
            applyToEnemy(mo, new VulnerablePower(mo, magicNumber, false));
        }
    }

    public void upp() {

        upgradeBlock(3);
    }
}
