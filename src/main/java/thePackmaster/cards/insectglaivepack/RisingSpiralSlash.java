package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceRed;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceWhite;
import thePackmaster.powers.insectglaivepack.ExtractedEssenceYellow;

public class RisingSpiralSlash extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(RisingSpiralSlash.class.getSimpleName());

    public RisingSpiralSlash() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.damage = this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 5;
        this.isMultiDamage = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) && p.hasPower(ExtractedEssenceWhite.ID)
                && p.hasPower(ExtractedEssenceYellow.ID)
                && p.hasPower(ExtractedEssenceRed.ID);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i) {
            addToBot(new SFXAction("ATTACK_HEAVY"));
            addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
            addToBot(new DrawCardAction(1));
        }
        addToBot(new RemoveSpecificPowerAction(p, p, ExtractedEssenceWhite.ID));
        addToBot(new RemoveSpecificPowerAction(p, p, ExtractedEssenceYellow.ID));
        addToBot(new RemoveSpecificPowerAction(p, p, ExtractedEssenceRed.ID));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}
