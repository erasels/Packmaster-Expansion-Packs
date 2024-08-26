package thePackmaster.powers.entropypack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.FlurryPower;
import thePackmaster.powers.RuinPower;

public class RuinousPower extends FlurryPower implements CloneablePowerInterface {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("RuinousPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public RuinousPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, owner, amount);
    }

    @Override
    public void onFlurry(AbstractCard played, AbstractCreature m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;

                target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                if (target != null) {
                    addToTop(new ApplyPowerAction(target, owner, new RuinPower(target, RuinousPower.this.amount), RuinousPower.this.amount, true));
                }
            }
        });
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new RuinousPower(owner, amount);
    }
}
