package thePackmaster.vfx.discopack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningOrbPassiveEffect;
import thePackmaster.SpireAnniversary5Mod;

public class ElectricFX extends AbstractGameEffect {
    private float lightningDelay = 1f;
    private float x;
    private float y;
    private float lightningDelayer;
    private AbstractCreature c;
    private boolean isDamage;
    private int staticAmount;
    private int soundTracker;

    public ElectricFX(AbstractCreature c, float DUR, boolean isDamage, int staticMultiplier) {
        //SpireAnniversary5Mod.logger.info("Electric FX started.");
        this.renderBehind = MathUtils.randomBoolean();
        this.x = c.hb.x;
        this.y = c.hb.y;
        this.c = c;
        this.staticAmount = staticMultiplier;
        this.isDamage = isDamage;
        this.duration = DUR;
        this.startingDuration = DUR;
        CardCrawlGame.sound.play(SpireAnniversary5Mod.makeID("Static"), 0.1f);
    }
 private void lightningFX(){
     Float lx = MathUtils.random(x, x + c.hb.width);
     Float ly = MathUtils.random(y, y + c.hb.height);
     AbstractDungeon.effectsQueue.add(new LightningOrbPassiveEffect(lx, ly));
 }

    public void update() {
        soundTracker = (int)duration;
        this.duration -= Gdx.graphics.getDeltaTime();
        lightningDelayer = startingDuration - duration;
        if(lightningDelay >= 1f){
            for(int i = 0; i < staticAmount; i++){lightningFX();}
            lightningDelay = 0f;
            }else
            {
                lightningDelay += lightningDelayer/startingDuration;

            }
        if((float) soundTracker/2.5 >= duration/2.5){
            CardCrawlGame.sound.play(SpireAnniversary5Mod.makeID("Static"), 0.1f);
            SpireAnniversary5Mod.logger.info("static trigger");
        }
        if(this.duration <= 0f){
            if (isDamage){
                AbstractDungeon.effectsQueue.add(new LightningEffect(c.hb.cX, y));
                CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE", 0.1F);
            }
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }
    public void dispose() {
    }
}
