package thePackmaster.util.dancepack;

import basemod.helpers.CardModifierManager;
import basemod.interfaces.OnCardUseSubscriber;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.cardmodifiers.dancepack.DefaultDanceMod;

import java.util.ArrayList;

public class JediUtil implements
        OnCardUseSubscriber
{
    public static final String[] typeText = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup").TEXT;

    public static ArrayList<AbstractCard> currentDance = new ArrayList<>();
    public static ArrayList<AbstractCard> longestDance = new ArrayList<>();
    @Override
    public void receiveCardUsed(AbstractCard c) {
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!currentDance.isEmpty()) {
                    AbstractCard tmp = currentDance.get(currentDance.size() - 1);
                    if (tmp.type == c.type) {
                        if (currentDance.size() >= longestDance.size()) {
                            longestDance.clear();
                            longestDance.addAll(currentDance);
                        }
                        currentDance.clear();
                    }
                }
                currentDance.add(c);
            }
        });
        AbstractDungeon.player.hand.group.stream().
                filter(crd -> CardModifierManager.hasModifier(crd, DefaultDanceMod.ID)).
                forEach(AbstractCard::initializeDescription);
    }

    public static void clearLists()
    {
        currentDance.clear();
        longestDance.clear();
    }

    public static ArrayList<AbstractCard> getDance()
    {
        //TODO power check
        return currentDance;
    }

    public static int getDanceSize()
    {
        int retVal = getDance().size();
        if (retVal < 2) return 0;
        return retVal;
    }

    public static String getLastCardType()
    {
        String text = "";
        if (!CardCrawlGame.isInARun()) return text;
        if (!(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) return  text;
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()) return text;
        switch (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(0).type) {
            case ATTACK:
                text = typeText[0];
                break;
            case CURSE:
                text = typeText[3];
                break;
            case STATUS:
                text = typeText[7];
                break;
            case SKILL:
                text = typeText[1];
                break;
            case POWER:
                text = typeText[2];
                break;
            default:
                text = typeText[5];
        }
        return text;
    }
}
