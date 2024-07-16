package thePackmaster.actions.siegepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.DarkSmokePuffEffect;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import thePackmaster.util.Wiz;

//REF: Alchyr code, StaticAction (distortionpack), others
public class BallisticStrikeAction extends AbstractGameAction {
    private final AbstractCard card;
    private static final float VFX_X_OFFSET = 120F;   //it's seemingly pixels.
    private static final float VFX_Y_OFFSET = 180F;

    public BallisticStrikeAction(AbstractCard c) {
        this.card = c;
    }

    @Override
    public void update() {
        this.target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (target == null) {
            isDone = true;
            return;
        }

        AbstractPlayer p = AbstractDungeon.player;
        card.calculateCardDamage((AbstractMonster) this.target); //override calculateCardDamage for 2x damage if enemy is attacking

        // Adding to TOP results in reverse order. But this is also what fixes the bugs!
        addToTop(new DamageAction(target, new DamageInfo(p, card.damage, card.damageTypeForTurn), Wiz.isAttacking(target) ? AttackEffect.BLUNT_HEAVY : AttackEffect.NONE, true, false));
        addToTop(new VFXAction(new WeightyImpactEffect(target.hb.cX, target.hb.cY), 0.4f));
        addToTop(new VFXAction(new DarkSmokePuffEffect(p.hb.cX + VFX_X_OFFSET, p.hb.cY + VFX_Y_OFFSET), 0.12F));
        addToTop(new VFXAction(new ExplosionSmallEffect(p.hb.cX + VFX_X_OFFSET, p.hb.cY + VFX_Y_OFFSET), 0.04F));

        this.isDone = true;
    }
}
