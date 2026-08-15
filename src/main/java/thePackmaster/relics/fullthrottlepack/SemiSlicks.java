package thePackmaster.relics.fullthrottlepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.FueledPack;
import thePackmaster.packs.FullThrottlePack;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;
import thePackmaster.powers.fullthrottlepack.AfterburnerPower;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.util.Wiz;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class SemiSlicks extends AbstractPackmasterRelic implements AddToHandPatches.AddToHandInterface {
    public static final String ID = SpireAnniversary5Mod.makeID(SemiSlicks.class.getSimpleName());

    public SemiSlicks() {
        super(ID, RelicTier.RARE, LandingSound.HEAVY, FullThrottlePack.ID);
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onAddCard(CardGroup group, AbstractCard card) {

        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            addToBot(new ApplyPowerAction(m, adp(), new IgnitePower(m, 1)));
        }
        flash();

    }
}
