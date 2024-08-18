package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.actions.royaltypack.RetainerStrikeAction;
import thePackmaster.actions.royaltypack.TributeOrAusterityAction;
import thePackmaster.cards.artificerpack.AbstractArtificerCard;
import thePackmaster.cards.royaltypack.optioncards.NobleStrikeAusterity;
import thePackmaster.cards.royaltypack.optioncards.NobleStrikeTribute;
import thePackmaster.util.Wiz;

import javax.smartcardio.Card;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class RetainerStrike extends AbstractRoyaltyCard {

    public final static String ID = makeID("RetainerStrike");

    public RetainerStrike(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        baseDamage = 8;
    }

    @Override
    public void upp() {
        this.upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (!this.upgraded){
            CardGroup hand = Wiz.p().hand;
            CardGroup handWithoutRetain = new CardGroup(CardGroup.CardGroupType.HAND);
            for (AbstractCard c: hand.group){
                if (!c.retain && c != this) handWithoutRetain.addToHand(c);
            }
            AbstractCard toRetain;
            atb(new TalkAction(true, "" + handWithoutRetain.size(), 0.6f, 1.6f));
            if (handWithoutRetain.size() > 0) {
                toRetain = handWithoutRetain.getRandomCard(AbstractDungeon.cardRandomRng);
            }
            else {
                toRetain = hand.getRandomCard(AbstractDungeon.cardRandomRng);
            }
            toRetain.retain = true;
            toRetain.flash();
        }
        else {
            atb(new RetainerStrikeAction());
        }
    }
}
