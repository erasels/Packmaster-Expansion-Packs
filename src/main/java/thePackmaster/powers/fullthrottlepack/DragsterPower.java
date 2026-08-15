package thePackmaster.powers.fullthrottlepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.fullthrottlepack.NitroMod;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DragsterPower extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("DragsterPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public DragsterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0 && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= this.amount) {
            this.flash();
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    CardModifierManager.addModifier(card, new NitroMod());
                    this.isDone = true;
                }
            });
            if (card.type != AbstractCard.CardType.POWER && !card.shuffleBackIntoDrawPile) {
                card.shuffleBackIntoDrawPile = true;
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        card.shuffleBackIntoDrawPile = false;
                        this.isDone = true;
                    }
                });
            }
        }
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
}
