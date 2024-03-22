package thePackmaster.powers.needlework;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.needlework.StitchAction;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;
import java.util.List;

public class CopyAndPastePower extends AbstractPackmasterPower implements OnStitchPower, CloneablePowerInterface {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("CopyAndPastePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public CopyAndPastePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onStitch(AbstractCard stitched, AbstractCard attachedTo) {
        List<AbstractCard> goodOptions = new ArrayList<>(), badOptions = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.equals(attachedTo)) continue;
            if (c.type != AbstractCard.CardType.STATUS && c.type != AbstractCard.CardType.CURSE && c.color != AbstractCard.CardColor.CURSE)
                goodOptions.add(c);
            else
                badOptions.add(c);
        }

        int amt = this.amount;
        while (amt > 0 && !goodOptions.isEmpty()) {
            AbstractCard toAttach = goodOptions.remove(AbstractDungeon.cardRandomRng.random(goodOptions.size() - 1));
            addToTop(new StitchAction(genCpy(stitched), toAttach, false));
            --amt;
        }

        while (amt > 0 && !badOptions.isEmpty()) {
            AbstractCard toAttach = badOptions.remove(AbstractDungeon.cardRandomRng.random(badOptions.size() - 1));
            addToTop(new StitchAction(genCpy(stitched), toAttach, false));
            --amt;
        }
    }

    private static AbstractCard genCpy(AbstractCard stitched) {
        AbstractCard cpy = stitched.makeStatEquivalentCopy();

        cpy.drawScale = cpy.targetDrawScale = stitched.drawScale;
        cpy.current_x = cpy.target_x = stitched.current_x;
        cpy.current_y = cpy.target_y = stitched.current_y;

        return cpy;
    }

    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        }
        else {
            this.description = String.format(DESCRIPTIONS[1], amount);
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new CopyAndPastePower(owner, amount);
    }
}
