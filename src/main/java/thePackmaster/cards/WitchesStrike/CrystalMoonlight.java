package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.powers.witchesstrikepack.LoseFocusPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CrystalMoonlight extends AbstractWitchStrikeCard {
    public final static String ID = makeID("CrystalMoonlight");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CrystalMoonlight() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChannelAction(new Dark()));
        Wiz.applyToSelf(new FocusPower(p,magicNumber));
        Wiz.applyToSelf(new LoseFocusPower(p,magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return Miracle.ID;
    }
}
