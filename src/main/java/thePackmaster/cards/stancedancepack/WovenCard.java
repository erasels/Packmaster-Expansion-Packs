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

    public boolean weaveInitialized = false;
    public ArrayList<AbstractCard> storedcards = new ArrayList<>();

    public WovenCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);

        exhaust = true;
    }

    public WovenCard(Weaver source) {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        storedcards.addAll(source.storedcards);

        this.rawDescription = "";
        for (AbstractCard c:storedcards
        ) {
            SpireAnniversary5Mod.logger.info(c.name);
            if (storedcards.get(0) == c){
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + c.name + LocalizedStrings.PERIOD;
            } else {
                this.rawDescription = this.rawDescription + "NL" + cardStrings.EXTENDED_DESCRIPTION[0] + c.name + LocalizedStrings.PERIOD;
            }
            SpireAnniversary5Mod.logger.info(c.cost);
            modifyCostForCombat(c.cost);
            SpireAnniversary5Mod.logger.info(this.cost);
        }
        if (AbstractDungeon.player.hasPower(NextWovenCheaper.POWER_ID)){
            if (AbstractDungeon.player.getPower(NextWovenCheaper.POWER_ID).amount > 0) {
                modifyCostForCombat(-1);
                AbstractDungeon.player.getPower(NextWovenCheaper.POWER_ID).onSpecificTrigger();
            }
        }

        //dumb, but MultiCardPreview can't accept an arraylist.
        AbstractCard p1 = null;
        AbstractCard p2 = null;
        AbstractCard p3 = null;
        if (storedcards.size() > 0) p1 = storedcards.get(0);
        if (storedcards.size() > 1) p2 = storedcards.get(1);
        if (storedcards.size() > 2) p3 = storedcards.get(2);
        SpireAnniversary5Mod.logger.info("size " + storedcards.size());

        MultiCardPreview.add(this, p1, p2, p3);
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


