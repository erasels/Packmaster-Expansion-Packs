package thePackmaster.cards.sneckopack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SneckoEyes extends AbstractSneckoCard {


    public final static String ID = makeID("SneckoEyes");

    public SneckoEyes() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new ConfusionPower(p)));
        addToBot(new ApplyPowerAction(p,p, new DrawPower(p, magicNumber)));
    }

    public void upp() {
        isInnate = true;
        if(timesUpgraded > 1) {
            upgradeMagicNumber(1);
        }
    }
}
