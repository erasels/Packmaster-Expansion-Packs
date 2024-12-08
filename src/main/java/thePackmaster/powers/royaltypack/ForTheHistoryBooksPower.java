package thePackmaster.powers.royaltypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ForTheHistoryBooksPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ForTheHistoryBooksPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ForTheHistoryBooksPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount +
                            DESCRIPTIONS[1] + amount +
                            DESCRIPTIONS[2];
    }

    public void atStartOfTurnPostDraw() {
        AbstractPlayer p = Wiz.p();
        Wiz.atb(new ApplyPowerAction(
                p, p, new StrengthPower(p, amount)));

        Wiz.atb(new ApplyPowerAction(
                p, p, new LoseStrengthPower(p, amount)));

        Wiz.atb(new ApplyPowerAction(
                p, p, new DexterityPower(p, amount)));

        Wiz.atb(new ApplyPowerAction(
                p, p, new LoseDexterityPower(p, amount)));

        Wiz.atb(new ApplyPowerAction(
                p, p, new HiredSupportPower(p, 1)));

        Wiz.atb(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }



}
