package thePackmaster.cards.stancedancepack;


import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.PlayCardModifier;

import thePackmaster.powers.stancedancepack.NextWovenCheaper;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.stances.stancedancepack.Weaver;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class WovenCard extends AbstractStanceDanceCard {
    public final static String ID = makeID("WovenCard");

    public ArrayList<AbstractCard> storedcards = new ArrayList<>();

    public WovenCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);

        exhaust = true;
    }

    public WovenCard(Weaver source) {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);

        //TODO: This still doesn't work. The logger below reads the cards, so they are getting passed in
        //at this point, but the MultiCardPreview doesn't kick in, and the cards are gone out of the array
        //by the time the Use is activated when the card is played.
        storedcards = source.storedcards;
        this.rawDescription = "";
        for (AbstractCard c:storedcards
        ) {
            SpireAnniversary5Mod.logger.info(c.name);
            MultiCardPreview.add(this, c);
            this.rawDescription = this.rawDescription + "NL" + cardStrings.EXTENDED_DESCRIPTION[0] + c.name + LocalizedStrings.PERIOD;
            modifyCostForCombat(c.cost);
        }
        if (AbstractDungeon.player.hasPower(NextWovenCheaper.POWER_ID)){
            if (AbstractDungeon.player.getPower(NextWovenCheaper.POWER_ID).amount > 0) {
                modifyCostForCombat(-1);
                AbstractDungeon.player.getPower(NextWovenCheaper.POWER_ID).onSpecificTrigger();
            }
        }

        this.rawDescription = this.rawDescription + cardStrings.DESCRIPTION;

        this.initializeDescription();

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c:storedcards
        ) {
            SpireAnniversary5Mod.logger.info(c.name);
            Wiz.atb(new NewQueueCardAction(c, m));
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


