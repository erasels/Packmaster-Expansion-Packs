package thePackmaster.powers.royaltypack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.vfx.royaltypack.LoseGoldTextEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HiredSupportPower extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("HiredSupportPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public static final int ENERGY_TO_GOLD_CONVERSION = 10;

    public HiredSupportPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.costForTurn > 0) {
            int goldAmount = card.costForTurn * ENERGY_TO_GOLD_CONVERSION;
            if (goldAmount <= AbstractDungeon.player.gold) {
                card.freeToPlayOnce = true;
                AbstractDungeon.effectList.add(new LoseGoldTextEffect(-goldAmount));
                AbstractDungeon.player.loseGold(goldAmount);
                CardCrawlGame.sound.play("GOLD_GAIN", 0.3F);

                this.amount -= 1;
                updateDescription();
                if (this.amount <= 0) {
                    removeThis();
                }
            }
        } else if (card.cost == -1) {
            int goldAmount = EnergyPanel.getCurrentEnergy() * ENERGY_TO_GOLD_CONVERSION;
            if (goldAmount <= AbstractDungeon.player.gold) {
                card.freeToPlayOnce = true;
                AbstractDungeon.effectList.add(new LoseGoldTextEffect(-goldAmount));
                AbstractDungeon.player.loseGold(goldAmount);
                CardCrawlGame.sound.play("GOLD_GAIN", 0.3F);

                this.amount -= 1;
                updateDescription();
                if (this.amount <= 0) {
                    removeThis();
                }
            }
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) removeThis();
    }

    @Override
    public void updateDescription() {
        if (amount <= 1) description = DESCRIPTIONS[0];
        else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
