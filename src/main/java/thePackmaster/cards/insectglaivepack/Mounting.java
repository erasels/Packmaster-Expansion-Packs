package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.insectglaivepack.MountingPower;

public class Mounting extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Mounting.class.getSimpleName());

    public Mounting() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MountingPower(p, this.magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);
    }
}
