package thePackmaster.cardmodifiers.dancepack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.dancepack.JediUtil;

public class DefaultDanceMod extends AbstractCardModifier {
    public static final String ID = SpireAnniversary5Mod.makeID(DefaultDanceMod.class.getSimpleName());
    public static final String[] uiStrings = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup").TEXT;


    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        String text = "";
        if (CardCrawlGame.isInARun() &&
                AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty()
        )
        {
            text += JediUtil.getLastCardType();
        }
        return rawDescription + text;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DefaultDanceMod();
    }
}
