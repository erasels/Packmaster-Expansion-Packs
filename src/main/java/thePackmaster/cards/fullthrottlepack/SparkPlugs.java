package thePackmaster.cards.fullthrottlepack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.downfallpack.AbstractDownfallCard;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;
import thePackmaster.powers.downfallpack.AwakenDeathPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class SparkPlugs extends AbstractFullThrottleCard implements AddToHandPatches.AddToHandInterface {
    public final static String ID = makeID("SparkPlugs");

    public SparkPlugs() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.damage=this.baseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            atb(new DamageAction(m, new DamageInfo(p, damage), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(1);
    }

    @Override
    public void onAddCard(CardGroup group, AbstractCard card) {
        if (card == this) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    card.setCostForTurn(0);
                    this.isDone = true;
                }
            });
        }
    }
}


