package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.showmanpack.ShowstopperAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Showstopper extends AbstractShowmanCard {
    public final static String ID = makeID("Showstopper");
    private final static int BLOCKTHRESHOLD = 100;
    private final static int DAMAGE = 50;

    public Showstopper() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 8;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int energy = this.energyOnUse;
        for (int i = 0; i < timesUpgraded; i++){
            energy++;
        }
        addToBot(new ShowstopperAction(p, block, this.freeToPlayOnce, energy, BLOCKTHRESHOLD, DAMAGE));
    }

    public void upp() {
    }
}