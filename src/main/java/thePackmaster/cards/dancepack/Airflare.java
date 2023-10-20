package thePackmaster.cards.dancepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.dancepack.DefaultDanceMod;
import thePackmaster.patches.dancepack.FollowUpInterface;
import thePackmaster.util.dancepack.JediUtil;

public class Airflare extends AbstractDanceCard implements FollowUpInterface {

    public final static String ID = makeID(Airflare.class.getSimpleName());

    public Airflare() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        CardModifierManager.addModifier(this, new DefaultDanceMod());
        setDamage(8);
    }

    @Override
    public void upp() {
        shuffleBackIntoDrawPile = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public CardType getFollowUpCard() {
        return CardType.ATTACK;
    }

    @Override
    public void followUpEffect(AbstractMonster target) {
        addToBot(new ModifyDamageAction(uuid, JediUtil.getDanceSize()));
    }
}
