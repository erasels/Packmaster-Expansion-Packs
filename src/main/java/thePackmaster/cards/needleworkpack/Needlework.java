package thePackmaster.cards.needleworkpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MultiGroupMoveAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.instadeathpack.AbstractInstadeathCard;
import thePackmaster.powers.instadeathpack.CloudPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Needlework extends AbstractNeedleworkCard {
    public final static String ID = makeID("Needlework");

    public Needlework() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);

        PersistFields.setBaseValue(this, 2);
        baseDamage = damage = 9;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
