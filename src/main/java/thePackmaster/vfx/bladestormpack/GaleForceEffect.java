package thePackmaster.vfx.bladestormpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WindyParticleEffect;

//REFS: WhirlwindEffect (base game), EssenceGraspEffect (calamitypack)
public class GaleForceEffect  extends AbstractGameEffect {
    private static final int COUNT_LIMIT = 4;   //was 18
    private static final float DELAY_BETWEEN_PARTICLES = 0.03f; //was 0.05f

    private int count = 0;
    private float timer = 0.0F;
    private final boolean reverse;

    public GaleForceEffect(Color setColor, boolean reverse) {
        this.color = setColor.cpy();
        this.reverse = reverse;
    }

    public void update() {
        this.timer -= Gdx.graphics.getDeltaTime();
        if (this.timer < 0.0F) {
            this.timer += DELAY_BETWEEN_PARTICLES;

            AbstractDungeon.effectsQueue.add(new WindyParticleEffect(this.color, this.reverse));

            this.count++;
            if (this.count == COUNT_LIMIT) {
                this.isDone = true;
            }
        }
    }

    public void render(SpriteBatch sb) {}

    public void dispose() {}
}