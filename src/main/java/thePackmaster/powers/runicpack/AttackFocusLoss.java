package thePackmaster.powers.runicpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class AttackFocusLoss extends AbstractPackmasterPower {

    public static final String POWER_ID = SpireAnniversary5Mod.makeID("AttackFocusLoss");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    ArrayList<AbstractCard> journalLog = new ArrayList<>();

    public AttackFocusLoss(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type.equals(AbstractCard.CardType.ATTACK)){
            Wiz.atb(new ReducePowerAction(this.owner, this.owner, this, 1));
            Wiz.atb(new ApplyPowerAction(this.owner, this.owner, new FocusPower(this.owner, -1), -1));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
