package thePackmaster.patches.needlework;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.ExtraIconsPatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.IconPayload;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import thePackmaster.cardmodifiers.needlework.StitchedMod;
import thePackmaster.powers.needlework.CopyAndPastePower;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StitchPatches {
    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class StitchedField {
        //The StitchMod *holding* this card, not the mod attached to the card.
        public static SpireField<StitchedMod> stitch = new SpireField<>(()->null);
    }

    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class StitchesGoNowherePatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn<Void> doNothing(UseCardAction __instance, AbstractCard ___targetCard)
        {
            if (___targetCard != null) {
                StitchedMod mod = StitchedField.stitch.get(___targetCard);
                if (mod != null) //This card is attached to another
                {
                    mod.played(___targetCard); //Report to cardmod that this attached card was played
                    AbstractDungeon.player.cardInUse = null;
                    __instance.isDone = true;
                    return SpireReturn.Return();
                }
            }
            return SpireReturn.Continue();
        }

        public static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher matcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "purgeOnUse");
                return LineFinder.findInOrder(ctBehavior, matcher);
            }
        }
    }

    @SpirePatch2(
            clz = AbstractCard.class,
            method = "render",
            paramtypez = {SpriteBatch.class, boolean.class}
    )
    @SpirePatch2(
            clz = AbstractCard.class,
            method = "renderInLibrary"
    )
    public static class CardModPreRenderPatch {
        @SpirePrefixPatch
        public static void PreRender(AbstractCard __instance, SpriteBatch sb) {
            //i didn't want to loop over player powers for every card rendered and otherwise caching it is also kinda gross
            if (CopyAndPastePower.grossStaticListOfUUIDsToShowIcon.contains(__instance.uuid)) {
                ArrayList<IconPayload> icons = ExtraIconsPatch.ExtraIconsField.extraIcons.get(__instance);
                if (!icons.contains(CopyAndPastePower.icon)) {
                    icons.add(CopyAndPastePower.icon);
                }
            }

            for (AbstractCardModifier mod : CardModifierManager.modifiers(__instance)) {
                if (mod instanceof PreCardRenderModifier)
                    ((PreCardRenderModifier) mod).preRender(__instance, sb);
            }
        }

        public interface PreCardRenderModifier {
            void preRender(AbstractCard card, SpriteBatch sb);
        }
    }


    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "renderHand"
    )
    public static class AfterRenderLimboPatch {
        private static final Set<StitchedMod> manualUpdated = new HashSet<>(); //for preventing repeat updates
        @SpirePostfixPatch
        public static void afterRender(AbstractPlayer __instance, SpriteBatch sb)
        {
            AbstractDungeon.player.limbo.group.removeIf(
                    (c)->checkStitch(c, sb)
            );

            if (AbstractDungeon.player.cardInUse != null)
                checkStitch(AbstractDungeon.player.cardInUse, sb);

            manualUpdated.clear();
            StitchedMod.aliveMods.clear();
        }

        private static boolean checkStitch(AbstractCard c, SpriteBatch sb)
        {
            StitchedMod stitch = StitchedField.stitch.get(c);
            if (stitch == null) return false;

            c.unfadeOut(); // frick screens fading cards out, all my homies hate screens fading cards out

            if (StitchedMod.aliveMods.contains(stitch))
                return !stitch.detachedCards.contains(c); //Not detached, meaning the mod will render the card.
            else { //mod is not being updated, do it manually
                if (!manualUpdated.contains(stitch)) {
                    stitch.updatePositions(false);
                    stitch.renderStitches(sb);
                    manualUpdated.add(stitch);
                }

                //Similar to the aliveMods branch; just only removes it once card is near target position
                return (Math.pow(c.current_x - c.target_x, 2) + Math.pow(c.current_y - c.target_y, 2)) < 5 &&
                        !stitch.detachedCards.contains(c);
            }
        }
    }
}
