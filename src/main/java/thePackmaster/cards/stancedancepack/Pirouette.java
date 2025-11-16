package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.actions.EasyXCostAction;

import thePackmaster.stances.downfallpack.AncientStance;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.stancedancepack.StanceDanceEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Pirouette extends AbstractStanceDanceCard {
    public final static String ID = makeID("Pirouette");

    public Pirouette() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new VFXAction(new StanceDanceEffect(AbstractDungeon.player, false, true, true), 0.1F));

        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                this.addToBot(new SFXAction("ATTACK_HEAVY"));
                this.addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));
                this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
            if (effect <= 0) {
                this.addToBot(new ChangeStanceAction(new AncientStance()));
            }
            return true;
        }));


    }


    @Override
    public void upp() {
        upgradeDamage(3);
    }

}


