package thePackmaster.cards.doppelpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.SummonAction;

public class Summon extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Summon.class.getSimpleName());

    public Summon() {
        super(ID, 1, AbstractCard.CardType.SKILL, CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        previewDoppel = true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new SummonAction());
    }
}
