package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.WillpowerAusterity;
import thePackmaster.cards.royaltypack.optioncards.WillpowerTribute;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Willpower extends AbstractRoyaltyCard {

    public final static String ID = makeID("Willpower");

    public Willpower(){
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isInnate = false;
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractRoyaltyCard willTributeChoiceCard = new WillpowerTribute();
        AbstractRoyaltyCard willAusterityChoiceCard = new WillpowerAusterity();
        if (this.upgraded){
            willTributeChoiceCard.upgrade();
            willAusterityChoiceCard.upgrade();
        }

        Wiz.atb(new TributeOrAusterityAction(willTributeChoiceCard, willAusterityChoiceCard));
    }
}
