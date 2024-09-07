package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.ForcefieldAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Forcefield extends AbstractRoyaltyCard {

    public final static String ID = makeID("Forcefield");

    public Forcefield() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 8;
        baseSecondMagic = secondMagic = 0;
    }

    @Override
    public void upp() {
        this.upgradeSecondMagic(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ForcefieldAction(
                abstractPlayer, this.block,
                this.freeToPlayOnce, this.energyOnUse, this.secondMagic));
    }
}
