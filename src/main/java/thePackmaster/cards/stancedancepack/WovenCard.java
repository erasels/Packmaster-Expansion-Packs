package thePackmaster.cards.stancedancepack;


import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import thePackmaster.cardmodifiers.stancedancepack.PlayWovenCardModifier;
import thePackmaster.powers.stancedancepack.NextWovenCheaper;
import thePackmaster.stances.stancedancepack.Weaver;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class WovenCard extends AbstractStanceDanceCard {
    public final static String ID = makeID("WovenCard");

    public ArrayList<AbstractCard> storedcards = new ArrayList<>();

    public WovenCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);

    }

    public WovenCard(Weaver source) {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        storedcards.addAll(source.storedcards);

        int totalcost=0;
        for (AbstractCard c:storedcards
        ) {
            CardModifierManager.addModifier(this, new PlayWovenCardModifier(c));
            totalcost+=c.cost;
        }

        //TODO: I've tried 5 ways of making this cost system work.
        if (AbstractDungeon.player.hasPower(NextWovenCheaper.POWER_ID)){
            if (AbstractDungeon.player.getPower(NextWovenCheaper.POWER_ID).amount > 0) {

                upgradeBaseCost(totalcost-2);
                AbstractDungeon.player.getPower(NextWovenCheaper.POWER_ID).onSpecificTrigger();
            } else {
                upgradeBaseCost(totalcost-1);
            }
        } else {
            upgradeBaseCost(totalcost-1);
        }

        //dumb, but MultiCardPreview can't accept an arraylist.
        AbstractCard p1 = null;
        AbstractCard p2 = null;
        AbstractCard p3 = null;
        if (storedcards.size() > 0) p1 = storedcards.get(0);
        if (storedcards.size() > 1) p2 = storedcards.get(1);
        if (storedcards.size() > 2) p3 = storedcards.get(2);

        //Why doesn't this work?!
        MultiCardPreview.add(this, p1, p2, p3);


        CardModifierManager.addModifier(this, new ExhaustMod());

        retain = true;
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


