package thePackmaster.cards.insectglaivepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import thePackmaster.SpireAnniversary5Mod;

public class RisingSlashCombo extends AbstractInsectGlaiveCard {
    public static final String ID = SpireAnniversary5Mod.makeID(RisingSlashCombo.class.getSimpleName());

    public RisingSlashCombo() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public float getTitleFontSize() {
        if(Settings.language== Settings.GameLanguage.ZHS){
            return -1.0F;
        }
        else {
            return 20.0f;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}
