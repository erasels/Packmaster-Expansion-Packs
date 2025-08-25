package thePackmaster.cards.doppelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.SummonAction;
import thePackmaster.orbs.doppelpack.AbstractDoppel;

public class Tides extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Tides.class.getSimpleName());

    public Tides() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void applyPowers() {
        int oldBaseDamage = baseDamage;
        baseDamage += baseMagicNumber * getOrbCount();
        super.applyPowers();
        baseDamage = oldBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int oldBaseDamage = baseDamage;
        baseDamage += baseMagicNumber * getOrbCount();
        super.calculateCardDamage(mo);
        baseDamage = oldBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            addToBot(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    private static int getOrbCount() {
        return (int) AbstractDungeon.player.orbs.stream().filter(o -> !(o instanceof EmptyOrbSlot)).count();
    }
}
