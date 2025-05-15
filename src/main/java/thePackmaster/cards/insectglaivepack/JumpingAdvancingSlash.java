package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;

public class JumpingAdvancingSlash extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(JumpingAdvancingSlash.class.getSimpleName());

    public JumpingAdvancingSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 2;
        this.cardsToPreview = new VaultingDance();
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
        if (InsectGlaivePack.isHover(2)) addToBot(new MakeTempCardInHandAction(new VaultingDance()));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
