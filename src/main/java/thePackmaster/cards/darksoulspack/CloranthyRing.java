package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CloranthyRing extends AbstractDarkSoulsCard {
    public final static String ID = makeID("CloranthyRing");
    // intellij stuff skill, self, rare, , , , , 2, 1

    public CloranthyRing() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 0;
        this.exhaust=true;
    }

    @Override
    public void applyPowers() {
        secondMagic = Wiz.countDebuffs(Wiz.p());
        isSecondMagicModified = baseSecondMagic != secondMagic;
        super.applyPowers();

        if (secondMagic == 0) {
            this.rawDescription = cardStrings.DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int energy = Wiz.countDebuffs(p);
        if (energy > 0) {
            Wiz.atb(new GainEnergyAction(energy));
        }
        Wiz.atb(new DrawCardAction(this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}