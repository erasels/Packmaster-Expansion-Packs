package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Foreshadow extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Foreshadow");

    public Foreshadow() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 6;
        exhaust = true;
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() > 1) {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn.subList(0, AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1)) {
                addToTop(new MakeTempCardInDrawPileAction(c, 1, true, true));
            }
        }
    }


    @Override
    public void triggerOnGlowCheck() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() > 0) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}