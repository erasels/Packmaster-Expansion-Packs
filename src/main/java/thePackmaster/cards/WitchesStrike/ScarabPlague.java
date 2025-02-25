package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.green.Backflip;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ScarabPlague extends AbstractWitchStrikeCard {
    public final static String ID = makeID("ScarabPlague");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ScarabPlague() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Bullet();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChannelAction(new Arcane()));
        Wiz.atb(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy(), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public String cardArtCopy() {
        return Backflip.ID;
    }
}