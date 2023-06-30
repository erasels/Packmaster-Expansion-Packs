package thePackmaster.cardmodifiers.magnetizepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.magnetizepack.MagnetizeAction;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MagnetizedModifier extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID(MagnetizedModifier.class.getSimpleName());
    private final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    boolean isInherent;

    public MagnetizedModifier(boolean isInherent) {
        this.isInherent = isInherent;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return isInherent;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, MagnetizedModifier.ID) && card.cost != -2;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.EXTRA_TEXT[0] + rawDescription;
    }

//    @Override
//    public void onExhausted(AbstractCard card) {
//        spread(card);
//    }

    public void onDiscarded(AbstractCard card) {
        spread(card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        spread(card);
    }

    private void spread(AbstractCard card) {
        List<AbstractCard> candidates = AbstractDungeon.player.hand.group.stream().filter(c -> {
            return c != card &&
                    !CardModifierManager.hasModifier(c, ID) &&
                    c.cost != -2;
        }).collect(Collectors.toList());

        if (candidates.size() > 0)
            AbstractDungeon.actionManager.addToBottom(new MagnetizeAction(candidates.get(AbstractDungeon.cardRandomRng.random(candidates.size()-1))));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MagnetizedModifier(isInherent);
    }

}
