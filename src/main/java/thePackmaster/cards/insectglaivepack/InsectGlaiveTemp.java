package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;

public class InsectGlaiveTemp extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(InsectGlaiveTemp.class.getSimpleName());

    public InsectGlaiveTemp() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        this.block = this.baseBlock = 6;
    }

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        if (InsectGlaivePack.isHover(1)) this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public void upp() {
    }
}
