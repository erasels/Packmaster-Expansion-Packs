package thePackmaster.cards.cosmoscommandpack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.BindingHelper;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import thePackmaster.cardmodifiers.cosmoscommand.DistortionApplicationPostDamageModifier;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.vfx;

public class RefractEnergy extends AbstractCosmosCard implements AmplifyCard {
    public final static String ID = makeID("RefractEnergy");

    public RefractEnergy() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        damage = baseDamage = 10;
        this.isMultiDamage = true;
        DamageModifierManager.addModifier(this, new DistortionApplicationPostDamageModifier(false, 0.5f));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal));
        Wiz.atb(new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public boolean skipUseOnAmplify() {
        return true;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        vfx(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal));
        Wiz.atb(BindingHelper.makeAction(this, new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE)));
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }

    public void upp() {
        upgradeDamage(3);
    }
}