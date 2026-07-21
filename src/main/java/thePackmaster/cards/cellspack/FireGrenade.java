package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.shuffleIn;

public class FireGrenade extends AbstractCellsCard {
    public final static String ID = makeID("FireGrenade");

    public FireGrenade() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 4;
        baseSecondMagic = secondMagic = 2;
        isMultiDamage = true;
        cardsToPreview = new OilGrenade();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        Wiz.forAllMonstersLiving((mo)->
                Wiz.applyToEnemy(mo, new IgnitePower(mo,magicNumber)));
        shuffleIn(new OilGrenade(), secondMagic);

    }
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}