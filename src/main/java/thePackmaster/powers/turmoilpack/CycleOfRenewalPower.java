package thePackmaster.powers.turmoilpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.turmoilpack.AbandonAction;
import thePackmaster.powers.AbstractPackmasterPower;

public class CycleOfRenewalPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("CycleOfRenewal");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CycleOfRenewalPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flashWithoutSound();
        this.addToBot(new AbandonAction(c -> c.rarity == AbstractCard.CardRarity.BASIC || c.rarity == AbstractCard.CardRarity.COMMON || c.type == AbstractCard.CardType.STATUS,l -> {
            if (l.isEmpty()) { return; }
            this.addToTop(new GainBlockAction(this.owner, this.amount * l.size()));
            this.addToTop(new DrawCardAction(l.size()));
        }));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0].replace("{0}", this.amount + "");
    }
}
