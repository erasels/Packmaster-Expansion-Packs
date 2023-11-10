package thePackmaster.cards.discopack;


import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.discopack.FastMovementsPower;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class FastMovements extends AbstractSmoothCard{
    public static final String ID = makeID("FastMovements");

    public FastMovements() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 1;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToSelf(new FastMovementsPower(p, magicNumber));
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
    }
}


