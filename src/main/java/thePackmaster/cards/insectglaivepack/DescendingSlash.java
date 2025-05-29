package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;

public class DescendingSlash extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(DescendingSlash.class.getSimpleName());

    public DescendingSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.damage = this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    private void changeName(String name) {
        this.name = name;
        if (upgraded) {
            this.name = this.name + "+";
            this.initializeTitle();
        }
    }

    @Override
    public void applyPowers() {
        if (InsectGlaivePack.isHover(1)) {
            changeName(cardStrings.EXTENDED_DESCRIPTION[0]);
        } else {
            changeName(cardStrings.NAME);
        }

        super.applyPowers();
    }

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        if (InsectGlaivePack.isHover(1)) this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (InsectGlaivePack.isHover(2))
            addToBot(new GainEnergyAction(this.magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }
}
