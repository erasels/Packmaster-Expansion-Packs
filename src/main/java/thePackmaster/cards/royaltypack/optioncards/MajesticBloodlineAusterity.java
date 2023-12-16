package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class MajesticBloodlineAusterity extends AbstractRoyaltyCard {

    public final static String ID = makeID("MajesticBloodlineAusterity");

    public MajesticBloodlineAusterity(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionAusterity.png");
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

    }
}
