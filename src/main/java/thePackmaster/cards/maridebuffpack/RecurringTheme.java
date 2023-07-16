package thePackmaster.cards.maridebuffpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.maridebuffpack.RecurringThemePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class RecurringTheme extends AbstractMariDebuffCard {
    public final static String ID = makeID("RecurringTheme");

    public RecurringTheme(){
        this(false);
    }
    public RecurringTheme(boolean upgraded) {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 4;
        this.exhaust = true;
        if(upgraded){
            this.upgrade();
        }
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        atb(new ApplyPowerAction(p, p, new RecurringThemePower(p, 1, this.upgraded), 1));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}


