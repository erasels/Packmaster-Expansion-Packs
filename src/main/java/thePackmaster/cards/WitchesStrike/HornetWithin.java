package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.green.Bane;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HornetWithin extends AbstractWitchStrikeCard {
    public final static String ID = makeID("HornetWithin");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HornetWithin() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 12;
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m,damage);
        Wiz.atb(new IncreaseMaxOrbAction(1));
        Wiz.atb(new ChannelAction(new Arcane()));
    }
    public void upp() {
        upgradeDamage(2);
    }
    @Override
    public String cardArtCopy() {
        return Bane.ID;
    }
}
