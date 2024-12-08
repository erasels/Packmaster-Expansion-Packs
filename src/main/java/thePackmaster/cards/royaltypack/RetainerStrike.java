package thePackmaster.cards.royaltypack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.royaltypack.RetainForOneTurnModifier;
import thePackmaster.util.Wiz;

import java.awt.*;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RetainerStrike extends AbstractRoyaltyCard {

    public final static String ID = makeID("RetainerStrike");
    private static final String[] CHOOSE_RETAIN_TEXT = CardCrawlGame.languagePack.getUIString(
            SpireAnniversary5Mod.makeID("RetainerStrikeAction")).TEXT;

    public RetainerStrike(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        baseDamage = 9;
    }

    @Override
    public void upp()
    {
        this.upgradeDamage(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        if (!this.upgraded){
            CardGroup hand = Wiz.p().hand;
            CardGroup handWithoutRetain = new CardGroup(CardGroup.CardGroupType.HAND);
            for (AbstractCard c: hand.group){
                if (!c.retain && !c.selfRetain && c != this) handWithoutRetain.addToHand(c);
            }
            AbstractCard toRetain;
            if (handWithoutRetain.size() > 0) {
                toRetain = handWithoutRetain.getRandomCard(AbstractDungeon.cardRandomRng);
            }
            else {
                toRetain = hand.getRandomCard(AbstractDungeon.cardRandomRng);
            }
            CardModifierManager.addModifier(toRetain, new RetainForOneTurnModifier());
            toRetain.flash();

        }
        else {
            if (AbstractDungeon.player.hand.isEmpty()) {

            } else{
                CardGroup hand = Wiz.p().hand;
                CardGroup handWithoutRetain = new CardGroup(CardGroup.CardGroupType.HAND);
                for (AbstractCard c: hand.group){
                    if (!c.retain && !c.selfRetain && c != this) handWithoutRetain.addToHand(c);
                }
                if (handWithoutRetain.size() == 0){

                }
                else if (handWithoutRetain.size() == 1){
                    AbstractCard card = hand.getRandomCard(AbstractDungeon.cardRandomRng);
                    CardModifierManager.addModifier(card, new RetainForOneTurnModifier());
                } else {
                    Wiz.atb(new SelectCardsAction(handWithoutRetain.group, CHOOSE_RETAIN_TEXT[0],
                            (List<AbstractCard> cards) -> {
                                AbstractCard card = cards.get(0);
                                CardModifierManager.addModifier(card, new RetainForOneTurnModifier());
                                card.flash();
                            }
                    ));
                }
            }
        }
    }
}
