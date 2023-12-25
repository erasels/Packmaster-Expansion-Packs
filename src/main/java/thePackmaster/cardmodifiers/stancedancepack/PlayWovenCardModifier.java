package thePackmaster.cardmodifiers.stancedancepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.util.Objects;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PlayWovenCardModifier extends AbstractCardModifier {
    private static final String ID = makeID("PlayWovenCardModifier");
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    private AbstractCard cardToPlay;

    public PlayWovenCardModifier(AbstractCard cardIn) {
        cardToPlay = cardIn;
        cardToPlay.purgeOnUse = true;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
            return rawDescription + uiStrings.TEXT[0] + cardToPlay.name + LocalizedStrings.PERIOD + " NL " ;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new NewQueueCardAction(cardToPlay, target, true, true));
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new PlayWovenCardModifier(cardToPlay);
    }
}
