package thePackmaster.actions.fullthrottlepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.fullthrottlepack.NitroMod;

import java.util.ArrayList;

public class ApplyRandomNitroAction extends AbstractGameAction {

    CardGroup g;

    public ApplyRandomNitroAction(int amount, CardGroup g) {
        this.amount = amount;
        this.g = g;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> cardsWithoutNitro = new ArrayList<>();
        for (AbstractCard c : g.group) {
            if (!CardModifierManager.hasModifier(c, NitroMod.ID) && c.cost != -2) {
                cardsWithoutNitro.add(c);
            }
        }

        if (amount > cardsWithoutNitro.size()) {
            amount = cardsWithoutNitro.size();
        }

        for (int i = 0; i < amount; i++) {
            AbstractCard d = cardsWithoutNitro.get(AbstractDungeon.cardRandomRng.random(cardsWithoutNitro.size() - 1));
            CardModifierManager.addModifier(d, new NitroMod());
            cardsWithoutNitro.remove(d);
        }
        this.isDone = true;
    }
}
