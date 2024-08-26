package thePackmaster.cards.grandopeningpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Warcry;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Foreshadow extends AbstractGrandOpeningCard implements StartupCard {
    public final static String ID = makeID("Foreshadow");

    public Foreshadow() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 12;
        cardsToPreview = new Warcry();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        cardsToPreview.upgrade();
        upgradeBlock(2);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        AbstractCard c = new Warcry();
        if (this.upgraded) {
            c.upgrade();
        }
        atb(new MakeTempCardInHandAction(c));
        return false;
    }
}