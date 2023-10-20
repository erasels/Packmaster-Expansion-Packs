package thePackmaster.cards.dancepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.cardmodifiers.dancepack.DefaultDanceMod;
import thePackmaster.patches.dancepack.FollowUpInterface;
import thePackmaster.util.Wiz;
import thePackmaster.util.dancepack.JediUtil;

public class Toprock extends AbstractDanceCard implements FollowUpInterface {

    public final static String ID = makeID(Toprock.class.getSimpleName());

    public Toprock() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        CardModifierManager.addModifier(this, new DefaultDanceMod());
        baseSecondMagic = secondMagic = 5;
        setMN(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new VigorPower(p, secondMagic));
    }

    @Override
    public CardType getFollowUpCard() {
        return CardType.POWER;
    }

    @Override
    public void followUpEffect(AbstractMonster target) {
        Wiz.applyToSelf(new StrengthPower(Wiz.p(), magicNumber));
    }
}
