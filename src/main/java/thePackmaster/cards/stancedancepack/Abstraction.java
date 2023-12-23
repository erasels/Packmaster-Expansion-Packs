package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.stances.sentinelpack.Serene;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Abstraction extends AbstractStanceDanceCard {
    public final static String ID = makeID("Abstraction");

    public Abstraction() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new DexterityPower(p, magicNumber));
        addToBot(new ChangeStanceAction(new Serene()));
    }


    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

}


