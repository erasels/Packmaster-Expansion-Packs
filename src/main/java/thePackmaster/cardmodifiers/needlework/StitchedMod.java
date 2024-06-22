package thePackmaster.cardmodifiers.needlework;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.patches.needlework.StitchPatches;
import thePackmaster.util.TexLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StitchedMod extends AbstractCardModifier implements StitchPatches.CardModPreRenderPatch.PreCardRenderModifier {
    private static final Texture thread = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/vfx/needlework/thread.png");
    private static final int THREAD_SIZE = 7;
    private static final float DRAW_SIZE = THREAD_SIZE, DRAW_OFFSET = DRAW_SIZE / 2f, THINNING = 3f / DRAW_SIZE;
    private static final Color lineColor = Color.WHITE.cpy();

    private static final float HB_W = 300.0F * Settings.scale, HB_H = 420.0F * Settings.scale;

    private static final float STACK_OFFSET = 300f, STACK_SCALING = 0.83f, STITCH_OFFSET = 240, STITCH_ANGLE = 36f;

    public static final Set<StitchedMod> aliveMods = new HashSet<>(); //mods whose cards are being actively rendered

    private final transient List<AbstractCard> stitchedCards = new ArrayList<>(); //Cards attached to this card
    public final transient List<AbstractCard> detachedCards = new ArrayList<>(); //Cards temporarily not following (for visuals on play)

    public StitchedMod(AbstractCard... cards)
    {
        for (AbstractCard c : cards) {
            addCard(c);
        }
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        if (stitchedCards.isEmpty())
            return false;

        for (AbstractCardModifier modifier : CardModifierManager.modifiers(card)) {
            if (modifier instanceof StitchedMod) {
                for (AbstractCard c : stitchedCards) {
                    ((StitchedMod) modifier).addCard(c);
                }
                return false;
            }
        }
        return true;
    }

    private void addCard(AbstractCard c) {
        stitchedCards.add(c);
        StitchPatches.StitchedField.stitch.set(c, this);

        CardModifierManager.modifiers(c).removeIf((mod)->{
            if (mod instanceof StitchedMod) {
                for (AbstractCard nestedCard : ((StitchedMod) mod).stitchedCards)
                    addCard(nestedCard);
                detachedCards.addAll(((StitchedMod) mod).detachedCards);
                return true;
            }
            return false;
        });
    }

    @Override
    public void onApplyPowers(AbstractCard card) {
        for (AbstractCard c : stitchedCards) {
            if (detachedCards.contains(c))
                continue;
            c.applyPowers();
        }
    }

    @Override
    public void onCalculateCardDamage(AbstractCard card, AbstractMonster mo) {
        for (AbstractCard c : stitchedCards) {
            if (detachedCards.contains(c))
                continue;
            c.calculateCardDamage(mo);
        }
    }

    private float lastScale = 1, lastAngle = 0, lastX = 0, lastY = 0;
    @Override
    public void onUpdate(AbstractCard card) {
        aliveMods.add(this);

        lastScale = card.drawScale;
        lastAngle = card.angle;
        lastX = card.current_x;
        lastY = card.current_y;

        updatePositions(checkHoverability(card));
    }

    public void updatePositions(boolean canHover)
    {
        if (!detachedCards.isEmpty())
            testCardQueue();

        AbstractCard last = null;

        //current pos + 256*settings.scale*card.drawscale = top of card
        //add an additional 50 scaled pixels for offset -> +306*scaling
        //move to get center by subtracting 256*settings.scale*stitched card scale
        for (AbstractCard c : stitchedCards) {
            if (detachedCards.contains(c))
                continue;

            float offset = ((STACK_OFFSET * (last == null ? lastScale : last.drawScale)) - (256f * c.drawScale)) * Settings.scale;

            c.drawScale = (last == null ? lastScale : last.drawScale) * STACK_SCALING;
            c.angle = last == null ? lastAngle : last.angle;

            if (canHover) {
                c.hb.update();
                if (c.hb.hovered) {
                    canHover = false;
                    offset = AbstractCard.IMG_HEIGHT * c.drawScale;
                }
            }

            c.target_x = (last == null ? lastX : last.current_x) + MathUtils.sinDeg(-(last == null ? lastAngle : last.angle)) * offset;
            c.target_y = (last == null ? lastY : last.current_y) + MathUtils.cosDeg(-(last == null ? lastAngle : last.angle)) * offset;

            updateMovement(c);
            last = c;
        }
    }

    private void testCardQueue()
    {
        if (AbstractDungeon.actionManager.cardQueue.isEmpty() && AbstractDungeon.player.cardInUse == null) {
            for (AbstractCard c : detachedCards)
            {
                c.unfadeOut();
                c.lighten(true);
                //They remain in limbo.
                //This will only occur if cards were queued and then cancelled somehow.
                //They will be removed from limbo by AfterRenderLimboPatch if the mod is active.
                //Otherwise, they will stay in limbo until they move to their target position.
            }
            detachedCards.clear();
        }
    }

    private static void updateMovement(AbstractCard c)
    {
        if (Settings.FAST_MODE) {
            c.current_x = MathHelper.fadeLerpSnap(c.current_x, c.target_x);
            c.current_y = MathHelper.fadeLerpSnap(c.current_y, c.target_y);
        }

        c.current_x = MathHelper.fadeLerpSnap(c.current_x, c.target_x);
        c.current_y = MathHelper.fadeLerpSnap(c.current_y, c.target_y);

        c.hb.move(c.current_x, c.current_y);
        c.hb.resize(HB_W * c.drawScale, HB_H * c.drawScale);
    }

    float lastTransparency = -1f;

    @Override
    public void preRender(AbstractCard card, SpriteBatch sb) {
        lastTransparency = card.transparency;
        aliveMods.add(this);
        renderStitches(sb);
        lastTransparency = -1f;
    }

    public void renderStitches(SpriteBatch sb) {
        AbstractCard last = null;
        float lastX1 = 0, lastX2 = 0, lastY1 = 0, lastY2 = 0, x1, x2, y1, y2;

        for (int i = stitchedCards.size() - 1; i >= 0; --i) {
            AbstractCard c = stitchedCards.get(i);

            float dist = STITCH_OFFSET * Settings.scale * c.drawScale;
            x1 = c.current_x + MathUtils.sinDeg(-c.angle + STITCH_ANGLE) * dist;
            x2 = c.current_x + MathUtils.sinDeg(-c.angle - STITCH_ANGLE) * dist;
            y1 = c.current_y + MathUtils.cosDeg(-c.angle + STITCH_ANGLE) * dist;
            y2 = c.current_y + MathUtils.cosDeg(-c.angle - STITCH_ANGLE) * dist;

            if (last != null && (aliveMods.contains(this) || AbstractDungeon.player.limbo.contains(c))) {
                lineColor.a = last.transparency;
                sb.setColor(lineColor);

                float length = dist(x1, lastX1, y1, lastY1) / DRAW_SIZE;
                sb.draw(thread, (x1 + lastX1) / 2f - DRAW_OFFSET, (y1 + lastY1) / 2f - DRAW_OFFSET, DRAW_OFFSET, DRAW_OFFSET,
                        DRAW_SIZE, DRAW_SIZE, length, THINNING,
                        MathUtils.radiansToDegrees * MathUtils.atan2(lastY1 - y1, lastX1 - x1),
                        0, 0, THREAD_SIZE, THREAD_SIZE, false, false);

                length = dist(x2, lastX2, y2, lastY2) / DRAW_SIZE;
                sb.draw(thread, (x2 + lastX2) / 2f - DRAW_OFFSET, (y2 + lastY2) / 2f - DRAW_OFFSET, DRAW_OFFSET, DRAW_OFFSET,
                        DRAW_SIZE, DRAW_SIZE, length, THINNING,
                        MathUtils.radiansToDegrees * MathUtils.atan2(lastY2 - y2, lastX2 - x2),
                        0, 0, THREAD_SIZE, THREAD_SIZE, false, false);
            }

            if (lastTransparency != -1)
                c.transparency = lastTransparency;

            if (aliveMods.contains(this) || AbstractDungeon.player.limbo.contains(c)) {
                int actualCost = c.cost;
                c.cost = -2;
                c.render(sb, false);
                c.cost = actualCost;
            }

            last = c;
            lastX1 = x1;
            lastX2 = x2;
            lastY1 = y1;
            lastY2 = y2;
        }

        if (last != null) {
            float dist = STITCH_OFFSET * Settings.scale * lastScale;
            x1 = lastX + MathUtils.sinDeg(-lastAngle + STITCH_ANGLE) * dist;
            x2 = lastX + MathUtils.sinDeg(-lastAngle - STITCH_ANGLE) * dist;
            y1 = lastY + MathUtils.cosDeg(-lastAngle + STITCH_ANGLE) * dist;
            y2 = lastY + MathUtils.cosDeg(-lastAngle - STITCH_ANGLE) * dist;

            lineColor.a = last.transparency;
            sb.setColor(lineColor);

            float length = dist(x1, lastX1, y1, lastY1) / DRAW_SIZE;
            sb.draw(thread, (x1 + lastX1) / 2f - DRAW_OFFSET, (y1 + lastY1) / 2f - DRAW_OFFSET, DRAW_OFFSET, DRAW_OFFSET,
                    DRAW_SIZE, DRAW_SIZE, length, THINNING,
                    MathUtils.radiansToDegrees * MathUtils.atan2(lastY1 - y1, lastX1 - x1),
                    0, 0, THREAD_SIZE, THREAD_SIZE, false, false);

            length = dist(x2, lastX2, y2, lastY2) / DRAW_SIZE;
            sb.draw(thread, (x2 + lastX2) / 2f - DRAW_OFFSET, (y2 + lastY2) / 2f - DRAW_OFFSET, DRAW_OFFSET, DRAW_OFFSET,
                    DRAW_SIZE, DRAW_SIZE, length, THINNING,
                    MathUtils.radiansToDegrees * MathUtils.atan2(lastY2 - y2, lastX2 - x2),
                    0, 0, THREAD_SIZE, THREAD_SIZE, false, false);
        }
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        detachedCards.addAll(stitchedCards);

        for (int i = stitchedCards.size() - 1; i >= 0; --i) {
            AbstractCard c = stitchedCards.get(i);

            AbstractDungeon.player.limbo.addToTop(c);
            if (!(target instanceof AbstractMonster)) {
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, true, EnergyPanel.getCurrentEnergy(), false, true), true);
            }
            else {
                c.calculateCardDamage((AbstractMonster) target);
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, (AbstractMonster) target, EnergyPanel.getCurrentEnergy(), false, true), true);
            }
        }
    }

    /*
     * card used: Adds all stitched to limbo.
     * Card is played, goes wherever it goes after UseCardAction.
     * If not hand (persist), the card played will probably no longer be updated.
     * The stitched card will still be removed from limbo and from the detached cards when:
     *  the use card action occurs, or after limbo rendering if:
     *      the card is not detached and the mod is being updated
     *      the mod is not being updated (discard pile, exhaust) and the card has moved to where it thinks it should be
     */

    @Override
    public AbstractCardModifier makeCopy() {
        AbstractCard[] cards = new AbstractCard[stitchedCards.size()];
        for (int i = 0; i < cards.length; ++i)
            cards[i] = stitchedCards.get(i).makeSameInstanceOf();
        return new StitchedMod(cards);
    }

    public void played(AbstractCard card) {
        detachedCards.remove(card);
    }

    public static float dist(float x1, float x2, float y1, float y2)
    {
        return (float) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    private static boolean checkHoverability(AbstractCard card) {
        if (AbstractDungeon.player == null) return false;

        switch (AbstractDungeon.screen) {
            case NONE:
                return AbstractDungeon.player.hoveredCard == null;
            case MASTER_DECK_VIEW:
                return AbstractDungeon.player.masterDeck.contains(card) && !card.hb.hovered;
            case GAME_DECK_VIEW:
                return AbstractDungeon.player.drawPile.contains(card) && !card.hb.hovered;
            case EXHAUST_VIEW:
                return AbstractDungeon.player.exhaustPile.contains(card) && !card.hb.hovered;
            case DISCARD_VIEW:
                return AbstractDungeon.player.discardPile.contains(card) && !card.hb.hovered;
        }
        return false;
    }

}
