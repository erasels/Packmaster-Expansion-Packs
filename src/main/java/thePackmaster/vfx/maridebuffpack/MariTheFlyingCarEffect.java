package thePackmaster.vfx.maridebuffpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;

public class MariTheFlyingCarEffect extends AbstractGameEffect {
    public static final Logger logger = LogManager.getLogger(MariTheFlyingCarEffect.class.getName());

    private final Texture CAR_TEXTURE = TexLoader.getTexture(SpireAnniversary5Mod.makePath("images/vfx/maridebuffpack/MariTheFlyingCar.png"));
    private final Texture BEAM_TEXTURE = TexLoader.getTexture(SpireAnniversary5Mod.makePath("images/vfx/maridebuffpack/MariTheFlyingCarBeam.png"));
    private final AtlasRegion imgCar = ImageMaster.vfxAtlas.addRegion("MariTheFlyingCar", CAR_TEXTURE, 0,0,300,145);
    private final AtlasRegion imgBeam = ImageMaster.vfxAtlas.addRegion("MariTheFlyingCarBeam", BEAM_TEXTURE, 0,0,286,286);

    private static final float EFFECT_DUR = 0.5F;
    private float cX;
    private float cY;
    private float sX;
    private float sY;
    private float dX;
    private float dY;
    private static final float ANIMATION_DURATION = 1.0F;
    private static final float MAX_DURATION = 1.5F;
    private Vector2 speedVector;
    private float speed;
    private float beamLengthScale;
    private float beamHeightScale;
    private float beamScale;

    private float originX;
    private float originY;
    private float scaledOriginX;
    private float scaledOriginY;

    private boolean playedSound;
    public MariTheFlyingCarEffect(float x, float y) {
        this.duration = MAX_DURATION;
        this.startingDuration = MAX_DURATION;
        this.sX = (float) Settings.WIDTH/2.0F;
        this.sY = (float) Settings.HEIGHT*2.0F;
        this.cX = sX;
        this.cY = sY;
        this.dX = x;
        this.dY = y;
        this.speed = MathUtils.random(20.0F * Settings.scale, 40.0F * Settings.scale);
        this.speedVector = new Vector2(dX-sX, dY-sY);
        this.speedVector.nor();
        this.speedVector.angle();
        this.rotation = this.speedVector.angle();
        this.beamLengthScale = 1.0F;
        this.beamScale = 0.0F;
        Vector2 var10000 = this.speedVector;
        var10000.x *= this.speed;
        var10000 = this.speedVector;
        var10000.y *= this.speed;
        this.scale = Settings.scale * 1.5F;

        this.originX = (float)this.imgCar.packedWidth*0.8227F;
        this.originY = (float)this.imgCar.packedHeight*0.3525F;

        this.playedSound = false;

    }

    public void update() {

        if(!this.playedSound){
            CardCrawlGame.screenShake.rumble(2.0F);
            CardCrawlGame.sound.play(SpireAnniversary5Mod.makeID("MariDebuffPack_TheFLYINGCAR"), 0);
            this.playedSound = true;
        }

        if(this.duration <= ANIMATION_DURATION) {

            this.cX = Interpolation.Pow.pow3Out.apply(this.dX, this.sX, this.duration / ANIMATION_DURATION);
            this.cY = Interpolation.Pow.pow3Out.apply(this.dY, this.sY, this.duration / ANIMATION_DURATION);
            this.beamScale = 1.0F - this.duration / ANIMATION_DURATION;
        }
        this.speed -= Gdx.graphics.getDeltaTime() * 60.0F;
        this.speedVector.nor();
        //this.beamLengthScale = this.duration / MAX_DURATION * 1.2F + 0.6F;
        //this.beamHeightScale = 1.0F - this.duration / MAX_DURATION * 0.5F;
        Vector2 var10000 = this.speedVector;
        var10000.x *= this.speed * Gdx.graphics.getDeltaTime() * 60.0F;
        var10000 = this.speedVector;
        var10000.y *= this.speed * Gdx.graphics.getDeltaTime() * 60.0F;
        scaledOriginX = originX * beamLengthScale;
        scaledOriginY = originY * beamHeightScale;
        this.color = new Color(1.0F,1.0F,1.0F,1.0F);
        super.update();
    }

    public void render(SpriteBatch sb) {
        if (!this.isDone) {
            //sb.setColor(this.color);
            sb.setColor(Color.WHITE.cpy());
            sb.draw(this.imgCar, this.cX, this.cY, 0, (float)this.imgCar.packedHeight * 0.4F, (float)this.imgCar.packedWidth, (float)this.imgCar.packedHeight, this.scale, this.scale, this.rotation);

            sb.draw(this.imgBeam, this.dX-(float)this.imgBeam.packedWidth * 0.5F, this.dY-(float)this.imgBeam.packedHeight * 0.5F, (float)this.imgBeam.packedWidth * 0.5F, (float)this.imgBeam.packedHeight * 0.5F, (float)this.imgBeam.packedWidth, (float)this.imgBeam.packedHeight, this.beamScale, this.beamScale, this.rotation);
            //sb.setColor(this.color);
            //sb.draw(this.imgBeam, (float)(this.dX - Math.cos(360.0F-this.rotation)*this.scaledOriginX - Math.sin(360.0F-this.rotation)*this.scaledOriginY*2)-this.scaledOriginX/2.0F, (float)(this.dY + Math.sin(360.0F - this.rotation)*this.scaledOriginX - Math.cos(360.0F - this.rotation)*this.scaledOriginY), this.scaledOriginX , this.scaledOriginY, (float)this.imgBeam.packedWidth, (float)this.imgBeam.packedHeight, this.beamLengthScale, this.beamHeightScale, this.rotation);
            //logger.info("destination: " + this.dX + "/// first section: " + (Math.cos(360.0F-this.rotation)*this.scaledOriginX) + "/// second section: " + (Math.sin(360.0F-this.rotation)*this.scaledOriginY));

        }

    }

    public void dispose() {
    }
}