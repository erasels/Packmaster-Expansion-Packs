package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Exultation extends AbstractIntrigueCard {
    public final static String ID = makeID("Exultation");

    public static int BLOCK = 20;
    private final static int PERM_BLOCK = 3;
    private final static int PERM_BLOCK_INC = 2;

    public Exultation() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK + misc;
        magicNumber = baseMagicNumber = PERM_BLOCK;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void applyPowers() {
        this.baseBlock = BLOCK + misc;
        super.applyPowers();
        this.initializeDescription();
    }

    public void upp() {
        upgradeMagicNumber(PERM_BLOCK_INC);
    }

    public void pumpUp(){
        misc += magicNumber;
        baseBlock = BLOCK + misc;
        initializeDescription();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        Exultation c = (Exultation)super.makeStatEquivalentCopy();
        c.misc = this.misc;
        return c;
    }
}
