package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.packs.RunicPack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RunicSpear extends AbstractRunicCard {

    private static final int COST = 2;
    private static final int DAMAGE = 17;
    private static final int UPG_DMG = 6;
    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = 2;
    public final static String ID = makeID("RunicSpear");


    public RunicSpear() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
            int realBaseDamage = this.baseDamage;
            if (AbstractDungeon.player.hasPower(FocusPower.POWER_ID)) {
                this.baseDamage += AbstractDungeon.player.getPower(FocusPower.POWER_ID).amount * magicNumber;
            }
            if (mo.hasPower(LockOnPower.POWER_ID)) {
                AbstractOrb.applyLockOn(mo, baseDamage);
            }
            super.calculateCardDamage(mo);
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DMG);
        upgradeMagicNumber(UPG_MAGIC);
    }
}
