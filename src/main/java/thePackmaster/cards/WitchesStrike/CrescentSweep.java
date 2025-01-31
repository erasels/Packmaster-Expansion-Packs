package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.green.LegSweep;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CrescentSweep extends AbstractWitchStrikeCard {
    public final static String ID = makeID("CrescentSweep");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CrescentSweep() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = 1;
        cardsToPreview = new Bullet();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy(),magicNumber));
        addToBot(new ChannelAction(new Arcane()));
    }
    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return LegSweep.ID;
    }
}
