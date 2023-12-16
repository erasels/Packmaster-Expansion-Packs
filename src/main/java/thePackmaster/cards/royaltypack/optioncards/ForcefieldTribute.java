package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.royaltypack.PayTributeAction;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class ForcefieldTribute extends AbstractRoyaltyCard {

    public final static String ID = makeID("ForcefieldTribute");
    public final static int TRIBUTE_GOLD_AMOUNT = 8;
    private int temporaryHpGained;

    public ForcefieldTribute(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionTribute.png");
        temporaryHpGained = 0;
        baseMagicNumber = magicNumber = temporaryHpGained;
    }

    public ForcefieldTribute(int temporaryHpGained){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionTribute.png");
        this.temporaryHpGained = temporaryHpGained;
        baseMagicNumber = magicNumber = this.temporaryHpGained;
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
        Wiz.atb(new AddTemporaryHPAction(AbstractDungeon.player,
                AbstractDungeon.player, this.magicNumber));
    }
}
