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


public class TimeStep extends AbstractStanceDanceCard {
    public final static String ID = makeID("TimeStep");

    public TimeStep() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO: Gain 1 Strength for each Stance entered this combat. Also have the card text state the amount that will be gained.
        //No stance subscriber I can see... need to patch?
        if (upgraded){
            Wiz.applyToSelf(new StrengthPower(p, 1));
        }
    }


    @Override
    public void upp() {

    }

}


