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
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 3;
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

        if (DrawCountAndHarvestExtractPatch.drawCount > 0) {
            this.rawDescription = cardStrings.DESCRIPTION
                    + cardStrings.EXTENDED_DESCRIPTION[1]
                    + DrawCountAndHarvestExtractPatch.drawCount
                    + cardStrings.EXTENDED_DESCRIPTION[2];
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (DrawCountAndHarvestExtractPatch.drawCount > 0 && InsectGlaivePack.isHover(1)) {
            this.baseDamage += DrawCountAndHarvestExtractPatch.drawCount * this.baseMagicNumber;
            super.calculateCardDamage(mo);
            this.baseDamage -= DrawCountAndHarvestExtractPatch.drawCount * this.baseMagicNumber;
            this.isDamageModified = (this.damage != this.baseDamage);
        } else {
            super.calculateCardDamage(mo);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        if (InsectGlaivePack.isHover(1)) this.glowColor = GOLD_BORDER_GLOW_COLOR;
        else this.glowColor = BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (DrawCountAndHarvestExtractPatch.drawCount > 0 && InsectGlaivePack.isHover(2)) {
            this.baseDamage += DrawCountAndHarvestExtractPatch.drawCount * this.baseMagicNumber;
            super.calculateCardDamage(m);
            this.baseDamage -= DrawCountAndHarvestExtractPatch.drawCount * this.baseMagicNumber;
            this.isDamageModified = (this.damage != this.baseDamage);
        } else {
            super.calculateCardDamage(m);
        }

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
