package thePackmaster.actions.doppelpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;
import thePackmaster.orbs.doppelpack.AbstractDoppel;
import thePackmaster.packs.DoppelPack;
import thePackmaster.powers.doppelpack.TurbulencePower;

import java.util.ArrayList;
import java.util.List;

public class SummonAction extends AbstractGameAction {

    private ArrayList<AbstractCard> affectedCards;

    public SummonAction() {
        this(1);
    }

    public SummonAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        isDone = true;
        if (AbstractDungeon.player.maxOrbs <= 0) {
            return;
        }

        affectedCards = new ArrayList<>(AbstractDungeon.player.hand.group);
        for (AbstractCard card : affectedCards) {
            CardModifierManager.addModifier(card, new ShowDoppel());
        }
        addToTop(new SelectFromHandAction(
                c -> true,
                this::afterSelectCard,
                DoppelPack.UI_STRINGS.TEXT[5],
                amount,
                false,
                false,
                ActionType.CARD_MANIPULATION,
                true
        ));
    }

    private void afterSelectCard(List<AbstractCard> cards) {
        if (affectedCards != null) {
            for (AbstractCard card : affectedCards) {
                CardModifierManager.removeModifiersById(card, ShowDoppel.ID, false);
            }
        }
        for (int cardsSize = cards.size(), i = cardsSize - 1; i >= 0; i--) {
            AbstractCard card = cards.get(i);
            AbstractDungeon.player.hand.empower(card);
            AbstractDungeon.player.hand.applyPowers();
            doSummon(card, true);
        }
    }

    public static void doSummon(AbstractCard card, boolean addToTop) {
        AbstractDoppel doppel = AbstractDoppel.createDoppel(card);
        if (addToTop) {
            AbstractDungeon.actionManager.addToTop(new ChannelAction(doppel));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(doppel));
        }

        AbstractPlayer player = AbstractDungeon.player;
        if (player != null) {
            AnonymousAction action = new AnonymousAction(() -> {
                TurbulencePower power = (TurbulencePower) player.getPower(TurbulencePower.ID);
                if (power != null) {
                    power.onSummoned(doppel);
                }
            });
            if (addToTop) {
                AbstractDungeon.actionManager.addToTop(action);
            } else {
                AbstractDungeon.actionManager.addToBottom(action);
            }
        }
    }
}
