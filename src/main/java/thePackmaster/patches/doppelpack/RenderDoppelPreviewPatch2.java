package thePackmaster.patches.doppelpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;
import thePackmaster.cards.AbstractPackmasterCard;

@SpirePatch(clz = AbstractPackmasterCard.class, method = "render")
public class RenderDoppelPreviewPatch2 {
    @SpirePostfixPatch
    public static void Postfix(AbstractPackmasterCard card, SpriteBatch sb) {
        for (AbstractCardModifier modifier : CardModifierManager.getModifiers(card, ShowDoppel.ID)) {
            ((ShowDoppel) modifier).doppel.render(sb);
        }
    }
}
