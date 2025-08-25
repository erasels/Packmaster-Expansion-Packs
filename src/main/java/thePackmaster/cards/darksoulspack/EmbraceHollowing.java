package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EmbraceHollowing extends AbstractDarkSoulsCard {
    public final static String ID = makeID("EmbraceHollowing");
    // intellij stuff power, self, rare, , , , , 5, 3

    public EmbraceHollowing() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 0;
    }

    @Override
    public void applyPowers() {
        secondMagic = getApplyStrengthCount() * 2;
        isSecondMagicModified = baseSecondMagic != secondMagic;
        super.applyPowers();

        String baseDescription = upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION;
        if (secondMagic == 0) {
            this.rawDescription = baseDescription;
        } else {
            this.rawDescription = baseDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = getApplyStrengthCount();
        for (int i = 0; i < count; i++)
            Wiz.applyToSelf(new StrengthPower(p, 2));
    }

    private int getApplyStrengthCount() {
        int count = Wiz.countDebuffs(Wiz.p());
        if (this.upgraded){
            count++;
        }
        return count;
    }

    public void upp() {
    }
}