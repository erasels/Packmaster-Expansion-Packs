package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class ForcefieldAusterity extends AbstractRoyaltyCard {

    public final static String ID = makeID("ForcefieldAusterity");
    private int temporaryHPGained;

    public ForcefieldAusterity(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionAusterity.png");
        temporaryHPGained = 0;
        baseMagicNumber = magicNumber = temporaryHPGained;
    }

    public ForcefieldAusterity(int temporaryHPGained){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionAusterity.png");
        this.temporaryHPGained = temporaryHPGained;
        baseMagicNumber = magicNumber = this.temporaryHPGained;
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
        Wiz.atb(new AddTemporaryHPAction(AbstractDungeon.player,
                AbstractDungeon.player, this.magicNumber));
    }
}
