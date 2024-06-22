package thePackmaster.powers.needlework;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.IconPayload;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.TexLoader;

import java.util.*;

public class CopyAndPastePower extends AbstractPackmasterPower implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("CopyAndPastePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture t = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/CopyAndPastePower84.png");
    public static final IconPayload icon = new IconPayload(ExtraIcons.icon(t));

    public static List<UUID> grossStaticListOfUUIDsToShowIcon = new ArrayList<>();

    private final boolean upgraded;
    private final Set<UUID> playedThisTurn = new HashSet<>();

    public CopyAndPastePower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

        this.upgraded = upgraded;
        updateDescription();
    }

    @Override
    public boolean isStackable(AbstractPower power) {
        return power instanceof CopyAndPastePower && ((CopyAndPastePower) power).upgraded == this.upgraded;
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (!playedThisTurn.add(card.uuid)) {
            playedThisTurn.remove(card.uuid);
            grossStaticListOfUUIDsToShowIcon.remove(card.uuid);

            this.flash();
            AbstractCard cpy = card.makeStatEquivalentCopy();
            cpy.modifyCostForCombat(-9999); //surely nobody will play a 10000 cost card
            if (upgraded) {
                addToBot(new MakeTempCardInDrawPileAction(cpy, amount, true, amount > 1));
            }
            else {
                addToBot(new MakeTempCardInDiscardAction(cpy, amount));
            }
        }
        else {
            grossStaticListOfUUIDsToShowIcon.add(card.uuid);
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        playedThisTurn.clear();
        grossStaticListOfUUIDsToShowIcon.clear();
    }

    /*@Override
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
    }*/

    /*private static AbstractCard genCpy(AbstractCard stitched) {
        AbstractCard cpy = stitched.makeStatEquivalentCopy();

        cpy.drawScale = cpy.targetDrawScale = stitched.drawScale;
        cpy.current_x = cpy.target_x = stitched.current_x;
        cpy.current_y = cpy.target_y = stitched.current_y;

        return cpy;
    }*/

    public void updateDescription() {
        int index = (upgraded ? 2 : 0);
        if (amount == 1) {
            this.description = DESCRIPTIONS[index];
        }
        else {
            this.description = String.format(DESCRIPTIONS[index + 1], amount);
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new CopyAndPastePower(owner, amount, upgraded);
    }
}
