package thePackmaster.actions.bladestormpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

/*REFS: AttackVial (womaninbluepack), StrikingStrike (strikepack), Mimicry & Paintbrush (creativitypack),
RazorWind (dragonwrathpack), DualHeal (summonerspellspack), RandomUpgradeWithVfxAction (upgradespack),
NeowReward & DamageAction (base game), BallisticStrike (siegepack)*/
public class TradeWindsAction extends AbstractGameAction {
    private final AbstractCreature p;
    private final AbstractCard chosenCard;
    private final int healIfCommon;
    private final int goldLossIfRare;
    private static final float VFX_X_OFFSET = -1080F * Settings.scale;
    private static final float VFX_Y_OFFSET = 360F * Settings.scale;

    public TradeWindsAction(AbstractCreature source, int heal, int goldLoss, AbstractCard chosenCard) {
        this.p = source;
        healIfCommon = heal;
        goldLossIfRare = goldLoss;
        this.chosenCard = chosenCard;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        this.isDone = true;
        //Depending on rarity of the card printed by the Discover calling this Action, heal or lose gold.
        if (chosenCard.rarity == AbstractCard.CardRarity.COMMON) {
            addToTop(new HealAction(p, p, healIfCommon, 0));

        } else if (chosenCard.rarity == AbstractCard.CardRarity.RARE) {
            addToTop(new AbstractGameAction() {
                //Lose gold with SFX and VFX to make player aware in case they didn't realize.
                //Based on DamageAction (base game).
                @Override
                public void update() {
                    isDone = true;
                    if (player.gold == 0) {
                        return;
                    }

                    //Calculate coins for VFX.
                    int tempGoldLoss = goldLossIfRare;
                    if (player.gold < tempGoldLoss) {
                        tempGoldLoss = player.gold;
                    }
                    //Remove the actual amount. If player's broke, they "borrow from an ally thanks to the wind".
                    player.gold -= goldLossIfRare;

                    //SFX & VFX. The wind visually taking away "borrowed" gold would not make sense.
                    CardCrawlGame.sound.play("GOLD_JINGLE");
                    for (int i = 0; i < tempGoldLoss; i++) {
                        AbstractDungeon.effectList.add(new GainPennyEffect(
                                player, player.hb.cX, player.hb.cY,
                                player.hb.cX + VFX_X_OFFSET, player.hb.cY + VFX_Y_OFFSET, false
                        ));
                    }
                }
            });
        }
    }
}
