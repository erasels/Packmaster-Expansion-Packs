package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.royaltypack.NobleFormPower;
import thePackmaster.powers.royaltypack.TrueNobleFormPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NobleForm extends AbstractRoyaltyCard {

    public final static String ID = makeID("NobleForm");

    public NobleForm(){
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upp() {
        this.upgraded = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (!upgraded){
            Wiz.atb(new ApplyPowerAction(abstractPlayer, abstractPlayer, new NobleFormPower(abstractPlayer, 1)));
        }
        else {
            Wiz.atb(new ApplyPowerAction(abstractPlayer, abstractPlayer, new TrueNobleFormPower(
                    abstractPlayer, 1)));
        }
    }
}
