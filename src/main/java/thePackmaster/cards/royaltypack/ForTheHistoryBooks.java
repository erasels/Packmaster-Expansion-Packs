package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.royaltypack.HiredSupportPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ForTheHistoryBooks extends AbstractRoyaltyCard {

    public final static String ID = makeID("ForTheHistoryBooks");

    public final static int AMOUNT_OF_TEMP_STR_AND_DEX = 3;
    public final static int INCREASE_ON_UPGRADE = 2;

    public ForTheHistoryBooks(){
        super(ID, -1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = AMOUNT_OF_TEMP_STR_AND_DEX;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(INCREASE_ON_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
        Wiz.atb(new ApplyPowerAction(
                    abstractPlayer,
                    abstractPlayer,
                new StrengthPower(abstractPlayer, magicNumber)));

        Wiz.atb(new ApplyPowerAction(
                abstractPlayer,
                abstractPlayer,
                new LoseStrengthPower(abstractPlayer, magicNumber)));

        Wiz.atb(new ApplyPowerAction(
                abstractPlayer,
                abstractPlayer,
                new DexterityPower(abstractPlayer, magicNumber)));

        Wiz.atb(new ApplyPowerAction(
                abstractPlayer,
                abstractPlayer,
                new LoseDexterityPower(abstractPlayer, magicNumber)));

        Wiz.atb(new ApplyPowerAction(
                abstractPlayer,
                abstractPlayer,
                new HiredSupportPower(abstractPlayer, 1)));
    }
}
