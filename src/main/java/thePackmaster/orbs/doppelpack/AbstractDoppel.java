package thePackmaster.orbs.doppelpack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.DoppelPack;
import thePackmaster.powers.doppelpack.WormHolePower;
import thePackmaster.skins.AbstractSkin;
import thePackmaster.skins.SkinManager;
import thePackmaster.skins.instances.PackmasterSkin;

public abstract class AbstractDoppel extends AbstractOrb {

    public final AbstractCard card;
    public boolean showCharacter = true;

    private static Skeleton cachedSkeleton;
    private static AbstractSkin cachedSkin;
    private static TextureAtlas cachedAtlas;

    private final Skeleton skeleton;
    private final AnimationState state;
    public float sX;
    public float sY;
    public float animationTimer;
    protected float animX;
    private final Color color = Color.GRAY.cpy();

    public AbstractDoppel(AbstractCard card) {
        this.baseEvokeAmount = 0;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 0;
        this.passiveAmount = this.basePassiveAmount;
        this.angle = MathUtils.random(360.0F);
        this.channelAnimTimer = 0.5F;
        this.card = card;

        this.skeleton = loadSkeleton();
        AnimationStateData stateData = new AnimationStateData(skeleton.getData());
        this.state = new AnimationState(stateData);

        AnimationState.TrackEntry idle = state.setAnimation(0, "Idle", true);
        idle.setTime(idle.getEndTime() * MathUtils.random());

        AbstractPlayer player = AbstractDungeon.player;
        if (player != null) {
            sX = player.drawX;
            sY = player.drawY;
        }
    }

    protected abstract void applyPowers(int numFocus, int numWormHole);

    @Override
    public void applyFocus() {
        if (AbstractDungeon.player == null) {
            applyPowers(0, 0);
            return;
        }

        super.applyFocus();
        AbstractPower focusPower = AbstractDungeon.player.getPower(FocusPower.POWER_ID);
        int numFocus = (focusPower != null ? focusPower.amount : 0);
        AbstractPower wormHolePower = AbstractDungeon.player.getPower(WormHolePower.ID);
        int numWormHole = (wormHolePower != null ? wormHolePower.amount : 0);
        applyPowers(numFocus, numWormHole);
    }

    @Override
    public void onEvoke() {
        this.card.setCostForTurn(0);
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(this.card, true, true));
    }

    @Override
    public void updateAnimation() {
        if (AbstractDungeon.player != null) {
            super.updateAnimation();
        }

        this.angle += Gdx.graphics.getDeltaTime() * 180.0F;
        this.state.update(Gdx.graphics.getDeltaTime());

        this.sX = MathHelper.orbLerpSnap(this.sX, this.tX);
        this.sY = MathHelper.orbLerpSnap(this.sY, this.tY - 265f * Settings.renderScale);

        if (this.animationTimer > 0) {
            this.animationTimer -= Gdx.graphics.getDeltaTime();
        }

        float volume;
        if (this.animationTimer > 0.2) {
            volume = 1;
        } else if (this.animationTimer < 0) {
            volume = 0.5f;
        } else {
            volume = Interpolation.fade.apply(this.animationTimer * 5) * 0.5f + 0.5f;
        }
        this.color.set(volume, volume, volume, 1);
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1F);
    }

    @Override
    public void setSlot(int slotNum, int maxOrbs) {
        if (maxOrbs == 1) {
            this.tX = AbstractDungeon.player.drawX + 30.0F * Settings.scale;
            this.tY = AbstractDungeon.player.drawY + 160.0F * Settings.yScale;
        } else if (maxOrbs < 6) {
            float dist = 160.0F * Settings.scale + maxOrbs * 10.0F * Settings.scale;
            this.tX = AbstractDungeon.player.drawX + dist - (dist * 2) * slotNum / (maxOrbs - 1) + 20.0F * Settings.scale;
            this.tY = AbstractDungeon.player.drawY + (160 + 50 * MathUtils.sinDeg(180f * slotNum / (maxOrbs - 1))) * Settings.yScale;
        } else {
            float dist = 160.0F * Settings.scale + maxOrbs * 10.0F * Settings.scale;
            this.tX = AbstractDungeon.player.drawX + dist - (dist * 2) * slotNum / (maxOrbs - 1) + 20.0F * Settings.scale;
            this.tY = AbstractDungeon.player.drawY + (170 + 40 * MathUtils.sinDeg(180f * slotNum / (maxOrbs - 1))) * Settings.yScale
                + ((slotNum % 2) * 2 - 1) * 30 * Settings.yScale;
        }

        this.tX += MathUtils.random(-1f, 1f) * 10 * Settings.scale;
        this.tY += MathUtils.random(-1f, 1f) * 5 * Settings.scale;
        this.tY += AbstractDungeon.player.hb_h / Settings.xScale * Settings.renderScale / 2.0F;
        this.hb.move(this.tX, this.tY);
    }

    protected int getDoppelDamage(AbstractCard card) {
        if (card.type != AbstractCard.CardType.ATTACK) {
            return 0;
        }
        int cost = card.cost;
        return Math.max(1, card.baseDamage / Math.max(cost + 1, 2));
    }

    protected int getDoppelDefend(AbstractCard card, int minDefend) {
        if (card.type == AbstractCard.CardType.ATTACK && card.baseBlock <= 0) {
            return 0;
        }
        int cost = card.cost;
        return Math.max(minDefend, card.baseBlock / Math.max(cost + 1, 2));
    }

    protected String getCardName() {
        return CardModifierManager.onRenderTitle(this.card, this.card.name).replace(" ", " #y");
    }

    public void renderSkeleton(SpriteBatch sb) {
        if (!showCharacter) {
            return;
        }

        AbstractPlayer player = AbstractDungeon.player;
        this.state.apply(this.skeleton);
        this.skeleton.updateWorldTransform();
        this.skeleton.setPosition(this.sX + this.animX, this.sY);
        this.skeleton.setColor(color);
        this.skeleton.setFlip(player.flipHorizontal, player.flipVertical);
        sb.end();
        CardCrawlGame.psb.begin();
        AbstractCreature.sr.draw(CardCrawlGame.psb, this.skeleton);
        CardCrawlGame.psb.end();
        sb.begin();
    }

    private Skeleton loadSkeleton() {
        Skeleton cached = cachedSkeleton;

        AbstractPlayer player = AbstractDungeon.player;
        AbstractSkin currentSkin = player instanceof ThePackmaster ? SkinManager.getInstance().curSkin() : PackmasterSkin.getInstance();
        if (cached != null && cachedSkin == currentSkin) {
            return cached;
        }

        if (cachedAtlas != null) {
            cachedAtlas.dispose();
        }

        String atlasUrl = currentSkin.getSkeletonAtlasPath();
        String skeletonUrl = currentSkin.getSkeletonJSONPath();

        cachedAtlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
        SkeletonJson json = new SkeletonJson(cachedAtlas);

        json.setScale(Settings.renderScale);
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
        Skeleton skeleton = new Skeleton(skeletonData);

        cachedSkeleton = skeleton;
        cachedSkin = currentSkin;
        return skeleton;
    }

    public static AbstractDoppel createDoppel(AbstractCard card) {
        DoppelPack.CardProperty cardProperty = DoppelPack.getCardProperty(card);
        if (card.type == AbstractCard.CardType.ATTACK) {
            if (card.baseBlock > 0 && !cardProperty.noBlock) {
                return new AttackDefendDoppel(card, cardProperty);
            } else {
                return new AttackDoppel(card, cardProperty);
            }
        } else if (card.type == AbstractCard.CardType.SKILL) {
            if (card.baseBlock > 0) {
                return new DefendDoppel(card);
            } else if (cardProperty.hasEnergy) {
                return new GainEnergyDoppel(card);
            } else if (cardProperty.hasDraw) {
                return new DrawCardDoppel(card);
            }
        } else if (card.type == AbstractCard.CardType.STATUS || card.type == AbstractCard.CardType.CURSE) {
            return new StatusDoppel(card);
        }

        return new DefendDoppel(card);
    }
}
