package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.InsectGlaivePack;
import thePackmaster.patches.insectglaivepack.DrawCountAndHarvestExtractPatch;

public class DescendingSlash extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(DescendingSlash.class.getSimpleName());

    public DescendingSlash() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void applyPowers() {
        if (InsectGlaivePack.isHover(1)) {
            this.name = this.cardStrings.EXTENDED_DESCRIPTION[0];
            upgradeName();
            //改卡图

            this.baseDamage += DrawCountAndHarvestExtractPatch.drawCount * this.baseMagicNumber;
            super.applyPowers();
            this.baseDamage -= DrawCountAndHarvestExtractPatch.drawCount * this.baseMagicNumber;
        } else {
            this.name = this.cardStrings.EXTENDED_DESCRIPTION[0];
            upgradeName();

            super.applyPowers();
        }

        if (DrawCountAndHarvestExtractPatch.drawCount > 0) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        initializeDescription();
    }

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        if (InsectGlaivePack.isHover(1)) this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameAction.AttackEffect effect = AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
        if (this.damage > 15)
            effect = AbstractGameAction.AttackEffect.SLASH_HEAVY;
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), effect));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}
