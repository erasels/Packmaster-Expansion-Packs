package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import thePackmaster.actions.discopack.SpecificToHandFromDiscardAction;
import thePackmaster.vfx.discopack.DarkenFX;
import thePackmaster.vfx.discopack.SpotlightFXtest;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class DanceTransition extends AbstractSmoothCard{
    public static final String ID = makeID("DanceTransition");
public int oldHandSize;
public int realBaseDamage;
    public DanceTransition() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = this.damage = 4;
        this.baseMagicNumber = this.magicNumber = 4;
        this.isMultiDamage = true;
    }
    public void spotlightFX(boolean isDiscard){
        ArrayList<AbstractMonster> monsterlist = new ArrayList<>();
        for (int i = 0; i < AbstractDungeon.getMonsters().monsters.size(); i++) {
            if(!AbstractDungeon.getMonsters().monsters.get(i).isDeadOrEscaped()){
                monsterlist.add(AbstractDungeon.getMonsters().monsters.get(i));
            }
        }
        if (!isDiscard) {
            for (int i = 0; i < monsterlist.size(); i++) {
                AbstractMonster mo = monsterlist.get(i);
                this.addToBot(new VFXAction(new SpotlightFXtest(mo)));
            }
            if (!monsterlist.isEmpty() && !monsterlist.get(0).isDeadOrEscaped()){
                this.addToBot(new VFXAction(new DarkenFX(2f), 1f));
            }

        }
        for (int i = 0; i < monsterlist.size(); i++) {
            AbstractMonster mo = monsterlist.get(i);
            this.addToBot(new VFXAction(new ExplosionSmallEffect(mo.hb.cX, mo.hb.cY)));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        spotlightFX(false);
        oldHandSize = p().hand.size() - 1;
        int realDamage = realBaseDamage + (oldHandSize * magicNumber);
        atb(new DamageAllEnemiesAction(p, realDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        atb(new DiscardAction(p, p, oldHandSize, true));
    }
    public void triggerOnManualDiscard() {
        att(new SpecificToHandFromDiscardAction(this));
    }

    public void applyPowers() {
        realBaseDamage = this.baseDamage;
        oldHandSize = p().hand.size() - 1;
        this.baseDamage += oldHandSize * magicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        realBaseDamage = this.baseDamage;
        oldHandSize = p().hand.size()-1;
        this.baseDamage += oldHandSize * magicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(1);
        this.upgradeDamage(1);
    }
}

