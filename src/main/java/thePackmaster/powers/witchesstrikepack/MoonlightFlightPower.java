package thePackmaster.powers.witchesstrikepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.cards.WitchesStrike.Bullet;
import thePackmaster.orbs.WitchesStrike.Arcane;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightFlightPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("MoonlightFlightPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static boolean activated = false;
    public MoonlightFlightPower(AbstractCreature owner) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,-1);
        canGoNegative = false;
    }
    @Override
    public void atStartOfTurnPostDraw() {
        Wiz.atb(new MakeTempCardInHandAction(new Bullet()));
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}

