package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TheBusStop extends AbstractSmoothCard{
    public static final String ID = makeID("TheBusStop");

    public TheBusStop() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = 8;
        this.baseSecondBlock = this.secondBlock = 5;

    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
        this.addToBot(new DiscardAction(p, p, 1, false));
    }
    public void triggerOnManualDiscard() {
        this.addToTop(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, secondBlock));
    }
    @Override
    public void upp() {
    this.upgradeBlock(4);
    this.upgradeSecondBlock(3);
    }
}
