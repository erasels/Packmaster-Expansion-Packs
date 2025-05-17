package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;

public class Evade extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Evade.class.getSimpleName());

    public Evade() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        this.block = this.baseBlock = 8;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        if (InsectGlaivePack.isHover(1)) this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        if (InsectGlaivePack.isHover(2))
            addToBot(new DrawCardAction(this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}
