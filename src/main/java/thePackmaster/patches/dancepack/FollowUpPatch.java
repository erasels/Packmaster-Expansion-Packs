package thePackmaster.patches.dancepack;


import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;

public class FollowUpPatch
{
    @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
    public static class UsePatch
    {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(AbstractCard c, AbstractMonster monster)
        {
            if (c instanceof FollowUpInterface && testFollowUp(c))
                ((FollowUpInterface)c).followUpEffect(monster);
        }

        private static boolean testFollowUp(AbstractCard c)
        {
            return AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2).type == ((FollowUpInterface)c).getFollowUpCard();
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(GameActionManager.class, "addToBottom");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
    @SpirePatch2(clz = AbstractCard.class, method = "triggerOnGlowCheck")
    public static class GlowPatch
    {
        public static void Postfix(AbstractCard __instance)
        {
            if (__instance.glowColor.equals(ReflectionHacks.getPrivateStatic(AbstractCard.class, "BLUE_BORDER_GLOW_COLOR")) &&
            __instance instanceof FollowUpInterface &&
                    testFollowUp(__instance))
            {
                __instance.glowColor = ReflectionHacks.getPrivateStatic(AbstractCard.class, "GOLD_BORDER_GLOW_COLOR");
            }
        }

        public static boolean testFollowUp(AbstractCard c)
        {
            return !AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).type == ((FollowUpInterface)c).getFollowUpCard();
        }
    }
}
