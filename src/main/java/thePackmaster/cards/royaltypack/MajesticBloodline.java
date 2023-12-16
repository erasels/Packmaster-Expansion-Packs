package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.royaltypack.MajesticBloodlineAction;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.royaltypack.optioncards.MajesticBloodlineAusterity;
import thePackmaster.cards.royaltypack.optioncards.MajesticBloodlineTribute;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MajesticBloodline extends AbstractRoyaltyCard {

    public final static String ID = makeID("MajesticBloodline");
    public final static int BASE_AMOUNT_OF_POWER_CHOICES = 3;

    public MajesticBloodline(){
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = BASE_AMOUNT_OF_POWER_CHOICES;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new MajesticBloodlineAction(createPowers(magicNumber)));
        AbstractRoyaltyCard mbTributeChoiceCard = new MajesticBloodlineTribute(this);
        AbstractRoyaltyCard mbAusterityChoiceCard = new MajesticBloodlineAusterity();

        Wiz.atb(new TributeOrAusterityAction(mbTributeChoiceCard, mbAusterityChoiceCard));
    }

    private ArrayList<AbstractCard> createPowers(int amount){
        ArrayList<AbstractCardPack> currentPoolPacks = SpireAnniversary5Mod.currentPoolPacks;
        ArrayList<AbstractCard> currentPowers = choosePowers(currentPoolPacks);
        ArrayList<AbstractCard> cardsForThePlayerToChooseFrom = theChosenCards(currentPowers, amount);
        return cardsForThePlayerToChooseFrom;
    }

    private ArrayList<AbstractCard> choosePowers (ArrayList<AbstractCardPack> currentPoolPacks){
        ArrayList<AbstractCard> currentPowers = new ArrayList<AbstractCard>();
        for (int i = 0; i < currentPoolPacks.size(); i++){
            for (int j = 0; j < currentPoolPacks.get(i).cards.size(); j++){
                AbstractCard card = currentPoolPacks.get(i).cards.get(j);
                if ((card.type == CardType.POWER) && (card.rarity != CardRarity.SPECIAL) && (!card.hasTag(CardTags.HEALING))){
                    currentPowers.add(card);
                }
            }
        }
        return currentPowers;
    }

    private ArrayList<AbstractCard> theChosenCards(ArrayList<AbstractCard> currentPowers, int amount){

        ArrayList theChosen = new ArrayList();

        while(theChosen.size() != amount) {
            boolean dupe = false;
            AbstractCard tmp = currentPowers.get(AbstractDungeon.cardRandomRng.random(currentPowers.size() - 1));
            Iterator iterator = theChosen.iterator();

            while(iterator.hasNext()) {
                AbstractCard c = (AbstractCard)iterator.next();
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                theChosen.add(tmp.makeCopy());
            }
        }

        return theChosen;
    }


}
