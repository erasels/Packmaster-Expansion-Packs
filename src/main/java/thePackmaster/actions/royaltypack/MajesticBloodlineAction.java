package thePackmaster.actions.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public class MajesticBloodlineAction extends AbstractGameAction {

    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(
            SpireAnniversary5Mod.makeID("MajesticBloodlineAction")).TEXT;
    private ArrayList<AbstractCard> powers;

    public MajesticBloodlineAction(ArrayList<AbstractCard> powers) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SHUFFLE;
        this.powers = powers;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(powers, TEXT[0], false);
            this.tickDuration();
        } else {
            doAction();
        }
    }

    private void doAction(){
        if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
            AbstractCard tmpCard = AbstractDungeon.cardRewardScreen.discoveryCard.
                    makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(tmpCard,
                    Settings.WIDTH/2, 3*Settings.HEIGHT/4, true));
            AbstractDungeon.cardRewardScreen.discoveryCard = null;
            this.isDone = true;
        }
    }
}
