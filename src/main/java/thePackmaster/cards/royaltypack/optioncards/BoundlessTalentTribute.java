package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.royaltypack.RetainerStrikeAction;
import thePackmaster.actions.royaltypack.PayTributeAction;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class BoundlessTalentTribute extends AbstractRoyaltyCard {

    public final static String ID = makeID("BoundlessTalentTribute");
    public final static int TRIBUTE_GOLD_AMOUNT = 5;

    public BoundlessTalentTribute(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionTribute.png");
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        Wiz.atb(new PayTributeAction(TRIBUTE_GOLD_AMOUNT));
        Wiz.atb(new RetainerStrikeAction());
    }



}
