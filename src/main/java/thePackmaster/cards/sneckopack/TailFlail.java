package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.sneckopack.SheddingAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TailFlail extends AbstractSneckoCard {


    public final static String ID = makeID("TailFlail");

    public TailFlail() {
        super(ID, 2, AbstractCard.CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        baseSecondDamage = 16;
        baseMagicNumber = magicNumber = 2;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int rolledDamage = baseDamage;
        if(baseSecondDamage > baseDamage) {
            rolledDamage = AbstractDungeon.cardRandomRng.random(baseDamage, baseSecondDamage);
        }
        int[] dmg = DamageInfo.createDamageMatrix(rolledDamage);
        addToBot(new DamageAllEnemiesAction(p, dmg, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new SheddingAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(6);
        upgradeSecondDamage(2);
    }
}
