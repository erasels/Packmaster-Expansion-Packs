package thePackmaster.powers.magnetizepack;

import basemod.helpers.CardModifierManager;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.patches.magnetizepack.OnDiscardPatch;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RepulsionFieldPower extends AbstractPackmasterPower implements CloneablePowerInterface, OnDiscardPatch.DiscardListener {
    public static final String POWER_ID = makeID(RepulsionFieldPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public RepulsionFieldPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onExhaust(AbstractCard card) {
        trigger(card);
    }

    @Override
    public void onManualDiscard(AbstractCard card) {
        trigger(card);
    }

    private void trigger(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, MagnetizedModifier.ID)) {
            flash();
            addToBot(new GainBlockAction(owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new RepulsionFieldPower(owner, amount);
    }
}




