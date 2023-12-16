package thePackmaster.cards.royaltypack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NobleSlash extends AbstractRoyaltyCard {

    public final static String ID = makeID("NobleSlash");

    public NobleSlash(){
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 14;
    }

    @Override
    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.atb(new DiscardAction(abstractPlayer, abstractPlayer, 1, true));
        DrawReductionPower drawReductionPower = new DrawReductionPower(abstractPlayer, 1);
        ReflectionHacks.setPrivate(drawReductionPower, DrawReductionPower.class, "justApplied", false);
        Wiz.atb(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                drawReductionPower));
    }
}
