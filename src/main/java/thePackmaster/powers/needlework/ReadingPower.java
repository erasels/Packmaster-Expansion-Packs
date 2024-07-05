package thePackmaster.powers.needlework;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.needlework.StitchAction;
import thePackmaster.actions.upgradespack.SuperUpgradeAction;
import thePackmaster.cards.needleworkpack.Read;
import thePackmaster.powers.AbstractPackmasterPower;

public class ReadingPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("ReadingPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ReadingPower(AbstractCreature owner, int upgLevel) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, upgLevel);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();

        int upgAmount = amount;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;

                Read toStitch = new Read();
                for (int upg = 1; upg < upgAmount; ++upg) {
                    SuperUpgradeAction.forceUpgrade(toStitch, false);
                }

                if (AbstractDungeon.player.hasPower(MasterRealityPower.POWER_ID)) {
                    toStitch.upgrade(); //Will only do anything if you would make an unupgraded one
                }
                StSLib.onCreateCard(toStitch);

                toStitch.drawScale = toStitch.targetDrawScale = 0.1f;
                toStitch.current_x = toStitch.target_x = Settings.WIDTH / 2f;
                toStitch.current_y = toStitch.target_y = Settings.HEIGHT * 2;
                addToTop(new StitchAction(toStitch));
            }
        });
    }

    public void updateDescription() {
        Read temp = new Read();
        for (int upg = 1; upg < amount; ++upg) {
            SuperUpgradeAction.forceUpgrade(temp, false);
        }
        this.description = String.format(DESCRIPTIONS[0], FontHelper.colorString(temp.name, "y"));
    }

    @Override
    public AbstractPower makeCopy() {
        return new ReadingPower(owner, amount);
    }
}