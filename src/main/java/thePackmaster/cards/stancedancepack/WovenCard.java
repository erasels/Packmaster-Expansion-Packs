package thePackmaster.cards.stancedancepack;


import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.madsciencepack.PlayCardModifier;

import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class WovenCard extends AbstractStanceDanceCard {
    public final static String ID = makeID("WovenCard");

    public ArrayList<AbstractCard> storedcards = new ArrayList<>();

    public WovenCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);

        exhaust = true;
    }

    public void initializePreview(){
        int cost = 0;

        //TODO: This doesn't work.
        for (AbstractCard c:storedcards
        ) {
            MultiCardPreview.add(this, c);
            this.rawDescription = this.rawDescription + "NL" + cardStrings.EXTENDED_DESCRIPTION[0] + c.name + LocalizedStrings.PERIOD;
            cost += c.cost;
        }
        this.rawDescription = this.rawDescription + cardStrings.DESCRIPTION;

        this.initializeDescription();
        this.cost = cost;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c:storedcards
        ) {
            Wiz.atb(new NewQueueCardAction(c.makeSameInstanceOf(), m, true));
        }
    }


    @Override
    public void upp() {
        for (AbstractCard c:storedcards
             ) {
            c.upgrade();
        }
    }

}


