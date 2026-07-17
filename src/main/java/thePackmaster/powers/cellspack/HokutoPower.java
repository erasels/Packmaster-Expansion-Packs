package thePackmaster.powers.cellspack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.summonspack.JinxPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HokutoPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(HokutoPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public HokutoPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new AllEnemyApplyPowerAction(this.owner, this.amount, m -> new JinxPower(m, this.amount)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0].replace("{0}", this.amount + "");
    }
}
