package thePackmaster.cards.needleworkpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cleanup extends AbstractNeedleworkCard {
    public final static String ID = makeID("Cleanup");

    public Cleanup() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);

        baseDamage = damage = 6;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        addToBot(new DiscardAction(p, p, magicNumber, false));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}
