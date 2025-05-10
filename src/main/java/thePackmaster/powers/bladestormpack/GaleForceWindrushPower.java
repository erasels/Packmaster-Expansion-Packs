package thePackmaster.powers.bladestormpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.bladestormpack.GaleForceEffect;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

//REFS: WhirlwindAction (base game)
public class GaleForceWindrushPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GaleForceWindrushPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public GaleForceWindrushPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (amount <= 0 || c.type != AbstractCard.CardType.ATTACK) { return; }

        this.flashWithoutSound();
        addToBot(new VFXAction(new GaleForceEffect(Color.TEAL, false), 0.0F));

        atb(new ApplyPowerAction(player, player, new WindrushPower(player, (Wiz.getLogicalCardCost(c)) + amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0]
                + this.amount + DESCRIPTIONS[1];
    }
}
