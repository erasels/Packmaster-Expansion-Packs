package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.green.DeadlyPoison;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.cardmodifiers.InfestModifier;
import thePackmaster.cards.OnInfestCard;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.powers.witchesstrikepack.LoseFocusPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HornetWithin extends AbstractWitchStrikeCard {
    public final static String ID = makeID("HornetWithin");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HornetWithin() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new FocusPower(p, -magicNumber));
        Wiz.applyToSelf(new LoseFocusPower(p, -magicNumber));
        Wiz.atb(new IncreaseMaxOrbAction(1));
        Wiz.atb(new ChannelAction(new CrescentMoon()));
    }
    public void upp() {
        upgradeMagicNumber(-1);
    }
    @Override
    public String cardArtCopy() {
        return DeadlyPoison.ID;
    }
}
