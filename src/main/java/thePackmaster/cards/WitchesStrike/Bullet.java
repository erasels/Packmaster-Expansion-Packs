package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.green.SuckerPunch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.cardmodifiers.InfestModifier;
import thePackmaster.cardmodifiers.witchesstrike.WickedModifier;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Bullet extends AbstractWitchStrikeCard {
    public final static String ID = makeID("Bullet");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Bullet() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        baseDamage = 4;
        CardModifierManager.addModifier(this, new WickedModifier(1));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            int orbs = 0;
            for (AbstractOrb o : p.orbs) {
                if (!(o instanceof EmptyOrbSlot)) {
                    orbs++;
                }
            }
            return orbs >= 1;
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new EvokeOrbAction(1));
        Wiz.atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upp() {
        upgradeDamage(6);
    }

    @Override
    public String cardArtCopy() {
        return SuckerPunch.ID;
    }
}
