package thePackmaster.powers.magnetizepack;

import basemod.helpers.CardModifierManager;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WorldDynamoPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID(WorldDynamoPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private boolean usedUp = false;

    public WorldDynamoPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onDrawOrDiscard() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (!usedUp && !Wiz.adp().hand.isEmpty() && Wiz.adp().hand.group.stream().allMatch(card -> CardModifierManager.hasModifier(card, MagnetizedModifier.ID))) {
                    flash();
                    addToTop(new GainEnergyAction(amount));
                    usedUp = true;
                }
                isDone = true;
            }
        });
    }

    @Override
    public void atStartOfTurn() {
        usedUp = false;
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new WorldDynamoPower(owner, amount);
    }
}




