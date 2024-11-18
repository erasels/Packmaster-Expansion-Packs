package thePackmaster.cards.doppelpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.doppelpack.SummonAction;
import thePackmaster.orbs.doppelpack.AbstractDoppel;

public class Tides extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Tides.class.getSimpleName());

    public Tides() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        previewDoppel = true;
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = (int) p.orbs.stream().filter(o -> o instanceof AbstractDoppel).count();
        for (int i = -2; i < count; i++) {
            addToBot(
                    new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        addToBot(new SummonAction());
    }
}
