package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import thePackmaster.util.Wiz;

//REF: Static (distortionpack)
public class BallisticStrikeAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final AbstractCard card;

    public BallisticStrikeAction(AbstractPlayer p, AbstractCard c) {
        this.p = p;
        this.card = c;
    }

    @Override
    public void update() {
        this.isDone = true;
        if (p.isDying)
            return;

        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (m == null)
            return;

        this.card.calculateCardDamage(m);
        //this.addToTop(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.01F));

        //m.damage(new DamageInfo(p, isRetaliatory(m)? this.card.damage *2 : this.card.damage, this.card.damageTypeForTurn));
        this.addToTop(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY), 0.35F));
        if (isRetaliatory(m)){
            /*if (p.hasPower(ShellPower.POWER_ID)) {
                this.addToTop(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.01F));
            }*/
            //m.damage(new DamageInfo(p, this.card.damage * 2, this.card.damageTypeForTurn));
            Wiz.doDmg(m, this.card.damage * 2, this.card.damageTypeForTurn, AttackEffect.SMASH);
        } else {
            /*if (p.hasPower(ShellPower.POWER_ID)) {
                this.addToTop(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.01F));
            }*/
            //m.damage(new DamageInfo(p, this.card.damage, this.card.damageTypeForTurn));
            //Wiz.doDmg(m, this.card.damage, this.card.damageTypeForTurn, AttackEffect.SLASH_HEAVY);
            Wiz.doDmg(m, this.card.damage, this.card.damageTypeForTurn, AttackEffect.BLUNT_HEAVY);
        }
        tickDuration();     //Might be useless.

        //REF: Applies distortion based on unblocked damage.
        /*if (m.lastDamageTaken > 0) {
            ApplyPowerAction instant = new ApplyPowerAction(m, p, new DistortionPower(m, p, m.lastDamageTaken), m.lastDamageTaken);
            ReflectionHacks.setPrivate(instant, ApplyPowerAction.class, "startingDuration", 0.01f);
            ReflectionHacks.setPrivate(instant, AbstractGameAction.class, "duration", 0.01f);
            this.addToTop(new ImproveAction(m, m.lastDamageTaken, instant));
            this.addToTop(instant);
        }*/

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
    }

    public boolean isRetaliatory(AbstractMonster m)
    {return m != null && !m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0;}
}
