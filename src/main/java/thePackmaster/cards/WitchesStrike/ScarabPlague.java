package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.colorless.GoodInstincts;
import com.megacrit.cardcrawl.cards.green.Backflip;
import com.megacrit.cardcrawl.cards.green.Bane;
import com.megacrit.cardcrawl.cards.green.Dash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ScarabPlague extends AbstractWitchStrikeCard {
    public final static String ID = makeID("ScarabPlague");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ScarabPlague() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = 3;
        cardsToPreview = new Bullet();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChannelAction(new Arcane()));
        Wiz.atb(new MakeTempCardInHandAction(cardsToPreview.makeStatEquivalentCopy(),magicNumber));
    }
    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return Backflip.ID;
    }
}