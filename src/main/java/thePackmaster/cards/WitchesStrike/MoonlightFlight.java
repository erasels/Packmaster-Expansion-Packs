package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.powers.witchesstrikepack.MoonlightFlightPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightFlight extends AbstractWitchStrikeCard

    {
    public final static String ID = makeID("MoonlightFlight");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public MoonlightFlight() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
         magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new MoonlightFlightPower(p,magicNumber)));
        Wiz.atb(new IncreaseMaxOrbAction(1));
        Wiz.atb(new ChannelAction(new CrescentMoon()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return LiveForever.ID;
    }
}
