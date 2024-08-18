package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.artificerpack.AbstractArtificerCard;
import thePackmaster.cards.royaltypack.optioncards.NobleStrikeAusterity;
import thePackmaster.cards.royaltypack.optioncards.NobleStrikeTribute;
import thePackmaster.util.Wiz;

import javax.smartcardio.Card;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RetainerStrike extends AbstractRoyaltyCard {

    public final static String ID = makeID("RetainerStrike");

    public RetainerStrike(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        baseDamage = 8;
        magicNumber = baseMagicNumber = 2;
        this.rawDescription = cardStrings.DESCRIPTION;
        if (this.magicNumber == 1){
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        }
        else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        }
        initializeDescription();
    }

    @Override
    public void upp() {
        this.upgradeDamage(2);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        if (this.magicNumber == 1){
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
        }
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (!this.upgraded){
            /*
                Unupgraded retain logic:
                Get hand group.
                Remove from hand group all cards that already have Retain.
                If hand group > 0
                    Randomly choose one of the cards there.
                    Give it retain.
             */
            CardGroup hand = Wiz.p().hand;
            CardGroup handWithoutRetain = new CardGroup(CardGroup.CardGroupType.HAND);
            for (AbstractCard c: hand.group){
                if (!c.retain) handWithoutRetain.addToHand(c);
            }
            AbstractCard toRetain;
            if (handWithoutRetain.size() > 0) {
                toRetain = handWithoutRetain.getRandomCard(AbstractDungeon.cardRandomRng);
            }
            else {
                toRetain = hand.getRandomCard(AbstractDungeon.cardRandomRng);
            }
            toRetain.retain = true;
            toRetain.flash();
        }
    }
}
