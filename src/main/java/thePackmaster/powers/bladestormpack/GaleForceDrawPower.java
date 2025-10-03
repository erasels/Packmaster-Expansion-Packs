package thePackmaster.powers.bladestormpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.bladestormpack.GaleForceEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

//REFS: WhirlwindAction (base game)
public class GaleForceDrawPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GaleForceDrawPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private final int COST_THRESHOLD;

    public GaleForceDrawPower(AbstractCreature owner, int amount, int threshold) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        COST_THRESHOLD = threshold;
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (amount <= 0 || c.type != AbstractCard.CardType.ATTACK || Wiz.getLogicalCardCost(c) < COST_THRESHOLD) {
            return;
        }

        this.flashWithoutSound();
        addToBot(new VFXAction(new GaleForceEffect(Color.LIGHT_GRAY, false), 0.0F));

        addToBot(new DrawCardAction(amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        if (amount == 1) {
            this.description += this.amount + DESCRIPTIONS[1];
        } else {
            this.description += this.amount + DESCRIPTIONS[2];
        }
        this.description += COST_THRESHOLD + DESCRIPTIONS[3];
    }
}
