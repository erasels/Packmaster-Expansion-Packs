package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.blue.Capacitor;
import com.megacrit.cardcrawl.cards.red.SeverSoul;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.actions.witchesstrikepack.MoonlightBarrageAction;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.powers.witchesstrikepack.FlowoftheSwordPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightBarrage extends AbstractWitchStrikeCard {
    public final static String ID = makeID("MoonlightBarrage");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MoonlightBarrage() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new FlowoftheSwordPower(p,1));
    }

    public void upp() {

    }
    @Override
    public String cardArtCopy() {
        return Capacitor.ID;
    }
}
