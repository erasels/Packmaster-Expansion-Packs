package thePackmaster.patches.doppelpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cardmodifiers.doppelpack.ShowDoppel;
import thePackmaster.cards.AbstractPackmasterCard;

@SpirePatch(clz = AbstractCard.class, method = "render", paramtypez = {SpriteBatch.class})
public class RenderDoppelPreviewPatch {
    @SpirePostfixPatch
    public static void Postfix(AbstractCard card, SpriteBatch sb) {
        if (!(card instanceof AbstractPackmasterCard)) {
            for (AbstractCardModifier modifier : CardModifierManager.getModifiers(card, ShowDoppel.ID)) {
                ((ShowDoppel) modifier).doppel.render(sb);
            }
        }
    }
}
