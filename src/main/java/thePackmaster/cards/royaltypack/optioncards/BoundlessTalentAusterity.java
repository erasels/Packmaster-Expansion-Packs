package thePackmaster.cards.royaltypack.optioncards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.royaltypack.AbstractRoyaltyCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class BoundlessTalentAusterity extends AbstractRoyaltyCard {

    public final static String ID = makeID("BoundlessTalentAusterity");
    private int maxDrawHandSize;

    public BoundlessTalentAusterity(){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                        "anniv5Resources/images/cards/OptionAusterity.png");
        this.maxDrawHandSize = 4;
        baseMagicNumber = magicNumber = this.maxDrawHandSize;
    }

    public BoundlessTalentAusterity(int maxDrawHandSize){
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW,
                "anniv5Resources/images/cards/OptionAusterity.png");
        this.maxDrawHandSize = maxDrawHandSize;
        baseMagicNumber = magicNumber = this.maxDrawHandSize;
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
        int amountOfCardsToDraw = maxDrawHandSize - AbstractDungeon.player.hand.size();
        for (int i = 0; i < amountOfCardsToDraw; i++){
            Wiz.atb(new DrawCardAction(AbstractDungeon.player, 1));
        }

    }
}
