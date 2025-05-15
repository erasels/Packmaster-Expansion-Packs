package thePackmaster.powers.insectglaivepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.insectglaivepack.AbstractInsectGlaiveCard;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.powers.AbstractPackmasterPower;

public class ExtractedEssenceWhitePower extends AbstractPackmasterPower {
    public static String ID = SpireAnniversary5Mod.makeID("ExtractedEssenceWhitePower");
    private static final PowerStrings STRINGS = CardCrawlGame.languagePack.getPowerStrings(ID);
    private static final String[] DESCRIPTIONS = STRINGS.DESCRIPTIONS;

    public ExtractedEssenceWhitePower(AbstractCreature owner) {
        super(ID, STRINGS.NAME, PowerType.BUFF, false, owner, -1);
    }

    @Override
    public void onInitialApplication() {
        InsectGlaivePack.movePowerPosition(this);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractInsectGlaiveCard)
            addToBot(new DrawCardAction(1));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer)
            addToBot(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
