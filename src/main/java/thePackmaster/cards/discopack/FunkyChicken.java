package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;
import static thePackmaster.util.Wiz.atb;

public class FunkyChicken extends AbstractSmoothCard {
    public static final String ID = makeID("FunkyChicken");

    public FunkyChicken() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseSecondMagic = secondMagic = 2;
        this.baseMagicNumber = magicNumber = 3;
        this.baseBlock = block = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber));
        this.addToBot(new DiscardAction(p, p, 1, false));
        CardCrawlGame.sound.play(modID + ":Chicken", 0.1F);
    }

    public void triggerOnManualDiscard() {
        this.addToBot(new DrawCardAction(AbstractDungeon.player, secondMagic));
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(0);
    }
}