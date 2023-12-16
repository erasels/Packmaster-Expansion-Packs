package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.royaltypack.PayTributeAction;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class MajesticBloodlineTribute extends AbstractRoyaltyCard {

    public final static String ID = makeID("MajesticBloodlineTribute");
    private AbstractCard cardToPlayAgain;
    public final static int TRIBUTE_GOLD_AMOUNT = 10;

    public MajesticBloodlineTribute(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionTribute.png");
    }

    public MajesticBloodlineTribute(AbstractCard cardToPlayAgain){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionTribute.png");
        this.cardToPlayAgain = cardToPlayAgain;
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption(){
        Wiz.atb(new PayTributeAction(TRIBUTE_GOLD_AMOUNT));
        if (cardToPlayAgain != null){
            AbstractCard tmp = cardToPlayAgain.makeSameInstanceOf();
            tmp.purgeOnUse = true;

            Wiz.atb(new NewQueueCardAction(tmp, false));
        }
        else {
            Logger logger = LogManager.getLogger(ID);
            logger.info("Ok, how we reached this we shouldn't be here, call Levender");
        }

    }
}
