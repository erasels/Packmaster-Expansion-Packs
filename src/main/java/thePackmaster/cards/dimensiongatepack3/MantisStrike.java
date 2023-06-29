package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardInscryp;
import thePackmaster.powers.dimensiongatepack3.MantisStrikePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MantisStrike extends AbstractDimensionalCardInscryp {
    public final static String ID = makeID("MantisStrike");

    public MantisStrike() {
        super(ID, 1, CardRarity.COMMON, CardType.ATTACK, CardTarget.ALL_ENEMY);
        baseDamage = 4;
        tags.add(CardTags.STRIKE);

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new MantisStrikePower(p, damage));
        addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));

    }

    public void upp() {
        upgradeDamage(2);
    }
}