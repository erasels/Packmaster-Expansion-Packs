package thePackmaster.actions.discopack;


import basemod.BaseMod;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.vfx.discopack.ElectricFX;
import thePackmaster.vfx.upgradespack.LightUpgradeShineEffect;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.util.Wiz.*;

public class ElectricAction extends AbstractGameAction {

    private final List<AbstractCard> selectedCards = new ArrayList<>();

    public ElectricAction(List<AbstractCard> selectedCards, int amount) {
        this.amount = amount;
        this.selectedCards.addAll(selectedCards);
    }

    @Override
    public void update() {
        for (AbstractCard c : selectedCards) {
            AbstractDungeon.effectsQueue.add(new ElectricFX(p(), 0.75f, false, 2));
            DiscardCard(c);
            att(new GainBlockAction(p(), amount));
        }
        isDone = true;
    }
    public void DiscardCard(AbstractCard c){
        //SpireAnniversary5Mod.logger.info("Starting discard with card:  " + c);
        p().hand.moveToDiscardPile(c);
        c.triggerOnManualDiscard();
        GameActionManager.incrementDiscard(false);
    }
}