package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PlannedDefense extends AbstractGrandOpeningCard {
    public final static String ID = makeID("PlannedDefense");

    public PlannedDefense() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        isInnate = true;
        baseBlock = block = 16;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(AbstractDungeon.player, block));
        boolean innatePlayed = false;
        for(int c = 0; c<AbstractDungeon.actionManager.cardsPlayedThisTurn.size()-1; c++) {
            if(AbstractDungeon.actionManager.cardsPlayedThisTurn.get(c).isInnate) {
                innatePlayed = true;
                break;
            }
        }
        if (innatePlayed) {
            addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, block/2), block/2));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean innatePlayed = false;
        for(int c = 0; c<AbstractDungeon.actionManager.cardsPlayedThisTurn.size(); c++) {
            if(AbstractDungeon.actionManager.cardsPlayedThisTurn.get(c).isInnate) {
                innatePlayed = true;
                break;
            }
        }
        if (innatePlayed) {
            glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }
}
