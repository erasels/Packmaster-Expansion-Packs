package thePackmaster.cards.dancepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.dancepack.DefaultDanceMod;
import thePackmaster.patches.dancepack.FollowUpInterface;
import thePackmaster.util.Wiz;

public class Floorwork extends AbstractDanceCard implements FollowUpInterface {

    public final static String ID = makeID(Floorwork.class.getSimpleName());

    public Floorwork() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        CardModifierManager.addModifier(this, new DefaultDanceMod());
        isMultiDamage = true;
        setDamage(12);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public CardType getFollowUpCard() {
        return CardType.SKILL;
    }

    @Override
    public void followUpEffect(AbstractMonster target) {
        addToBot(new DamageAllEnemiesAction(Wiz.p(), multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
}
