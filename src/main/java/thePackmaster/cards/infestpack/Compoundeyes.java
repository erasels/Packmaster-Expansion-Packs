package thePackmaster.cards.infestpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.infestpack.CompoundeyesPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class Compoundeyes extends AbstractInfestCard {
    public final static String ID = makeID("Compoundeyes");
    // intellij stuff skill, self, uncommon, , , , , 4, 2

    public Compoundeyes() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        isInnate = true;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        applyToSelf(new CompoundeyesPower(p));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}