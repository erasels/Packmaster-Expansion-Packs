package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.blue.Capacitor;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.summonspack.FireSpirit;
import thePackmaster.powers.witchesstrikepack.FlowoftheSwordPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightBarrage extends AbstractWitchStrikeCard {
    public final static String ID = makeID("MoonlightBarrage");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MoonlightBarrage() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ChannelAction(new FireSpirit()));
        Wiz.applyToSelf(new FlowoftheSwordPower(p, 1));
    }

    public void upp() {
        isEthereal = false;
    }

    @Override
    public String cardArtCopy() {
        return Capacitor.ID;
    }
}
