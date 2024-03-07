package thePackmaster.cards.grandopeningpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class DashIn extends AbstractGrandOpeningCard implements StartupCard {
    public final static String ID = makeID("DashIn");

    public DashIn() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
        baseBlock = block = 7;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
        atb(new ScryAction(secondMagic));
    }

    @Override
    public boolean atBattleStartPreDraw() {
        addToTop(new DrawCardAction(magicNumber));
        return true;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}