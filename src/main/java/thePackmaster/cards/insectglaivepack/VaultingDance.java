package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class VaultingDance extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(VaultingDance.class.getSimpleName());

    public VaultingDance() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
