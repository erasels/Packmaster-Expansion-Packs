package thePackmaster.orbs.WitchesStrike;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.*;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.witchesstrike.MagicRingEffect;
import thePackmaster.vfx.witchesstrike.StarEffect;

import static thePackmaster.SpireAnniversary5Mod.makePath;

public class Arcane extends CustomOrb implements PackmasterOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID("Arcane");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    private static final int PASSIVE_AMOUNT = 3;
    private static final int EVOKE_AMOUNT = 1;

    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    private float vfxTimer = 2.5f;
    private float vfxIntervalMin = 1.4f;
    private float vfxIntervalMax = 2.7f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;
    private static final String IMG_PATH = makePath("/images/orbs/witchesstrike/arcane.png");
    boolean evoking = false;

    public Arcane() {
        super(ORB_ID, orbString.NAME, PASSIVE_AMOUNT, EVOKE_AMOUNT, DESCRIPTIONS[0], DESCRIPTIONS[0], IMG_PATH);
        updateDescription();
        angle = MathUtils.random(360.0f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }

    @Override
    public void onEvoke() {
        applyFocus();
        AbstractDungeon.actionManager.addToBottom(// 2.This orb will have a flare effect
                new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f));
        Wiz.vfx(new MagicRingEffect(hb.cX,hb.cY,Color.NAVY,Color.SKY));
        int arcanecount = 1;
        for (AbstractOrb o : Wiz.p().orbs){
            if (o instanceof Arcane && !(o == this)){
                arcanecount++;
            }
        }
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                Wiz.vfx(new SmallLaserEffect(hb.cX,hb.cY,mo.hb.cX,mo.hb.cY));
                Wiz.atb(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, evokeAmount*arcanecount,
                        DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                        AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                        AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                        isDone= true;
                    }
                });
            }
        }
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        applyFocus(); // Apply Focus (Look at the next method)
        int arcanecount = 1;
        for (AbstractOrb o : Wiz.p().orbs){
            if (o instanceof Arcane && !(o == this)){
                arcanecount++;
            }
        }
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + DESCRIPTIONS[2] + (evokeAmount*arcanecount) + DESCRIPTIONS[3];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Arcane();
    }

    public void passiveEffect(){
        applyFocus();
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractMonster m = Wiz.getRandomEnemy();
                if (m != null){
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            AbstractDungeon.effectList.add(new OrbFlareEffect(Arcane.this, OrbFlareEffect.OrbFlareColor.FROST));
                            AbstractDungeon.effectList.add(new SmallLaserEffect(hb.cX,hb.cY,m.hb.cX,m.hb.cY));
                            isDone= true;
                        }
                    });
                    Wiz.atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, applyLockOn(m, passiveAmount),
                            DamageInfo.DamageType.THORNS), AttackEffect.BLUNT_HEAVY));
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            AbstractDungeon.effectList.add(new StarEffect(m.hb.cX,m.hb.cY,Color.CYAN,1.2f));
                            AbstractDungeon.effectList.add(new StarEffect(m.hb.cX,m.hb.cY,Color.CYAN,1.2f));
                            AbstractDungeon.effectList.add(new StarEffect(m.hb.cX,m.hb.cY,Color.CYAN,1.2f));
                            AbstractDungeon.effectList.add(new StarEffect(m.hb.cX,m.hb.cY,Color.CYAN,1.2f));
                            AbstractDungeon.effectList.add(new StarEffect(m.hb.cX,m.hb.cY,Color.CYAN,1.2f));
                            AbstractDungeon.effectList.add(new StarEffect(m.hb.cX,m.hb.cY,Color.CYAN,1.2f));
                            isDone= true;
                        }
                    });
                }
                isDone = true;
            }
        });
        updateDescription();
    }
    @Override
    public void playChannelSFX() {
    }

    @Override
    public void applyFocus() {
        AbstractPower power = AbstractDungeon.player.getPower("Focus");
        if (power != null) {
            this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
            this.evokeAmount =  Math.max(0,this.baseEvokeAmount + power.amount);
        } else {
            this.passiveAmount = this.basePassiveAmount;
            this.evokeAmount = this.baseEvokeAmount;
        }
    }

    @Override
    public void updateAnimation() {// You can totally leave this as is.
        // If you want to create a whole new orb effect - take a look at conspire's WaterDamage Orb. It includes a custom sound, too!
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 25.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new MagicRingEffect(cX,cY,Color.BLUE, Color.SKY));
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, c.a / 2.0f));
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, angle, 0, 0, 96, 96, false, false);
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
        renderText(sb);
        hb.render(sb);
    }
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, this.c, this.fontScale);
    }
}
