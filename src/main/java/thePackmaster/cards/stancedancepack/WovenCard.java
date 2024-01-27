package thePackmaster.cards.stancedancepack;


import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
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

        int totalcost = 0;
        for (int i = 0; i < storedcards.size(); i++) {
            AbstractCard c = storedcards.get(i);
            CardModifierManager.addModifier(this, new PlayWovenCardModifier(c, i == storedcards.size() - 1));
            totalcost += c.cost;
        }

        //TODO: I've tried 5 ways of making this cost system work.
        if (AbstractDungeon.player.hasPower(NextWovenCheaper.POWER_ID)) {
            if (AbstractDungeon.player.getPower(NextWovenCheaper.POWER_ID).amount > 0) {
                cost = totalcost - 2;
                AbstractDungeon.player.getPower(NextWovenCheaper.POWER_ID).onSpecificTrigger();
            } else {
                cost = totalcost - 1;
            }
        } else {
            cost = totalcost - 1;
        }
        costForTurn = cost;

        MultiCardPreview.multiCardPreview.get(this).addAll(this.storedcards);

        CardModifierManager.addModifier(this, new ExhaustMod());

        retain = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : storedcards) {
            SpireAnniversary5Mod.logger.info(c.name);
            Wiz.atb(new NewQueueCardAction(c, m));
        }
    }


    @Override
    public void upp() {
        for (AbstractCard c : storedcards) {
            c.upgrade();
        }
    }

}


