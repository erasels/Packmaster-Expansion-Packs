package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import thePackmaster.powers.cthulhupack.EndOfDaysPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EndOfDays extends AbstractCthulhuCard {
    public final static String ID = makeID("EndOfDays");


    public EndOfDays() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            Wiz.atb(new ChannelAction(new Dark()));
        }
        Wiz.applyToSelf(new EndOfDaysPower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}