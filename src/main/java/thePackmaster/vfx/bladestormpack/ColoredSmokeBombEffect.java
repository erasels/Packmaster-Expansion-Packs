package thePackmaster.vfx.bladestormpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

//REFS: SmokeBombEffect (base game).
public class ColoredSmokeBombEffect extends SmokeBombEffect {
    private final float x, y;

    public ColoredSmokeBombEffect(float x, float y, Color c) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.color = c.cpy();
    }

    @Override
    public void update() {
        if (this.duration == 0.2F) {
            CardCrawlGame.sound.play("ATTACK_WHIFF_2");

            for(int i = 0; i < 90; ++i) {
                AbstractDungeon.effectsQueue.add(new ColoredSmokeBlurEffect(this.x, this.y, color));
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            CardCrawlGame.sound.play("APPEAR");
            this.isDone = true;
        }
    }
}
