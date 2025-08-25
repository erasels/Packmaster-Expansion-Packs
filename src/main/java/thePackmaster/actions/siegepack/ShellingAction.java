package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.FlyingOrbEffect;
import thePackmaster.powers.siegepack.NextTurnGainVigorPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//REFS: VampireDamageAllEnemiesAction (base game), BallisticStrikeAction (siegepack), PetraPower (conjurerpack).
// HeavyBlade (base game) if "half-affected by Vigor" needed.
public class ShellingAction extends AbstractGameAction {
    public int[] damage;

    public ShellingAction (AbstractCreature source, int[] amount, DamageInfo.DamageType type, AbstractGameAction.AttackEffect effect) {
        setValues(null, source, amount[0]);
        this.damage = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        // ======== Based on Ironclad's Reaper ========

        //SFX-VFX only.
        if (this.duration == Settings.ACTION_DUR_FAST) {
            boolean playedMusic = false;
            int temp = Wiz.getEnemies().size();
            for (int i = 0; i < temp; i++) {

                AbstractMonster victim = Wiz.getEnemies().get(i);
                if (playedMusic) {
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                            victim.hb.cX,
                            victim.hb.cY, this.attackEffect, true));
                } else {
                    playedMusic = true;
                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(

                            victim.hb.cX,
                            victim.hb.cY, this.attackEffect));
                }
            }
        }

        tickDuration();

        //Damage and buffing
        if (this.isDone) {
            for (AbstractPower p : player.powers) {
                p.onDamageAllEnemies(this.damage);
            }

            int generatedVigorAmount = 0;

            for (int i = 0; i < (AbstractDungeon.getCurrRoom()).monsters.monsters.size(); i++) {
                AbstractMonster target = (AbstractDungeon.getCurrRoom()).monsters.monsters.get(i);

                if (!target.isDying && target.currentHealth > 0 && !target.isEscaping) {
                    target.damage(new DamageInfo(this.source, this.damage[i], this.damageType));

                    if (target.lastDamageTaken > 0) {
                        generatedVigorAmount += target.lastDamageTaken;     //(Only unblocked damage, limited by enemy HP.)
                        for (int j = 0; j < target.lastDamageTaken / 4 && j < 6; j++) {
                            addToBot(new VFXAction(new FlyingOrbEffect(target.hb.cX, target.hb.cY)));
                        }
                    }
                }
            }

            //Buffing
            generatedVigorAmount /= 2;
            if (generatedVigorAmount > 0) {
                if (!Settings.FAST_MODE) {
                    addToBot(new WaitAction(0.3F));
                }
                addToBot(new ApplyPowerAction(source, source, new NextTurnGainVigorPower(source, generatedVigorAmount), generatedVigorAmount));
            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            addToTop(new WaitAction(0.1F));
        }
    }
}
