package thePackmaster.patches.insectglaivepack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.insectglaivepack.doppelpack.ExtractedEssenceRed;
import thePackmaster.cardmodifiers.insectglaivepack.doppelpack.ExtractedEssenceWhite;
import thePackmaster.cardmodifiers.insectglaivepack.doppelpack.ExtractedEssenceYellow;
import thePackmaster.cards.insectglaivepack.AbstractInsectGlaiveCard;
import thePackmaster.packs.InsectGlaivePack;

import java.util.ArrayList;

@SpirePatch(clz = AbstractDungeon.class, method = "getRewardCards")
public class GenerateEssencePatch {
    @SpireInsertPatch(rloc = 1866 - 1792, localvars = {"retVal2"})
    public static void Insert(ArrayList<AbstractCard> retVal2) {
        for (AbstractCard c : retVal2) {
            if (c instanceof AbstractInsectGlaiveCard) {
                InsectGlaivePack.initInsectRamdom();
                //添加modifier
                if (CardModifierManager.modifiers(c).stream().noneMatch(e ->
                        e instanceof ExtractedEssenceRed ||
                                e instanceof ExtractedEssenceWhite ||
                                e instanceof ExtractedEssenceYellow)) {
                    switch (InsectGlaivePack.InsectRamdom.random(2)) {
                        case 0:
                            CardModifierManager.addModifier(c, new ExtractedEssenceRed());
                            break;
                        case 1:
                            CardModifierManager.addModifier(c, new ExtractedEssenceWhite());
                            break;
                        case 2:
                            CardModifierManager.addModifier(c, new ExtractedEssenceYellow());
                            break;
                    }
                }
            }
        }
    }
}
