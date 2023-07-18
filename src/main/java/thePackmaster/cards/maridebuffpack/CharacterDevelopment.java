package thePackmaster.cards.maridebuffpack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.maridebuffpack.CharacterDevelopmentPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class CharacterDevelopment extends AbstractMariDebuffCard {
    public final static String ID = makeID("CharacterDevelopment");

    public CharacterDevelopment() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1; //scaling magic
        this.baseSecondMagic = this.secondMagic = 3; //constant magic
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new StrengthPower(p, this.secondMagic), this.secondMagic));
        atb(new ApplyPowerAction(p, p, new CharacterDevelopmentPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }


}


