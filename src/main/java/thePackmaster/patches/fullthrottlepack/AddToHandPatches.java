package thePackmaster.patches.fullthrottlepack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import thePackmaster.util.Wiz;

@SuppressWarnings("unused")
public class AddToHandPatches {

    public static boolean isPlayerTurn = false;

    public interface AddToHandInterface {
        void onAddCard(CardGroup group, AbstractCard card);
    }

    @SpirePatch(clz = CardGroup.class, method = "addToHand")
    public static class onAddCardPatch {
        @SpirePrefixPatch
        public static void Prefix(CardGroup __instance, AbstractCard __c) {
            if((AbstractDungeon.currMapNode == null || AbstractDungeon.player == null)) return;
            if((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && isPlayerTurn) {
                if (__c instanceof AddToHandInterface) {
                    ((AddToHandInterface) __c).onAddCard(__instance, __c);
                }
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    if (p instanceof AddToHandInterface) {
                        ((AddToHandInterface) p).onAddCard(__instance, __c);
                    }
                }
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    if (r instanceof AddToHandInterface) {
                        ((AddToHandInterface) r).onAddCard(__instance, __c);
                    }
                }
            }
        }
    }

    @SpirePatch2(clz = GameActionManager.class, method = "getNextAction")
    public static class playerTurnPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static void whatTurn() {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isPlayerTurn = true;
                    this.isDone = true;
                }
            });
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnPostDrawPowers");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch2(clz = AbstractRoom.class, method = "update")
    public static class playerTurnPatch2 {
        @SpireInsertPatch(locator = Locator.class)
        public static void whyTurn() {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isPlayerTurn = true;
                    this.isDone = true;
                }
            });
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnPostDrawRelics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch2(clz = AbstractRoom.class, method = "update")
    public static class playerTurnPatch3 {
        @SpireInsertPatch(locator = Locator.class)
        public static void whyTurn() {
            isPlayerTurn = false;
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfCombatPreDrawLogic");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch2(clz = GameActionManager.class, method = "endTurn")
    public static class PlayerTurnPatch4 {
        @SpirePostfixPatch
        public static void whoTurn() {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isPlayerTurn = false;
                    this.isDone = true;
                }
            });
        }
    }

}
