package thePackmaster.vfx.discopack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;

public class ymcaFlash extends AbstractGameEffect{

    public ymcaFlash() {
        duration = 0.2f;
    }

    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        if(duration <= 0f){
        CardCrawlGame.sound.stop(SpireAnniversary5Mod.makeID("YMCA"));
        this.isDone = true;}
    }

    public void render(SpriteBatch sb) {

    }// 44

    public void dispose() {
    }// 48

}

