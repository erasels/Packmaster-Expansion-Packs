package thePackmaster.cardmodifiers.magnetizepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.combat.LightningOrbPassiveEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.magnetizepack.MagnetizeAction;
import thePackmaster.powers.magnetizepack.PolarityPower;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.magnetizepack.MagnetizeParticleEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MagnetizedModifier extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID(MagnetizedModifier.class.getSimpleName());
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);

    private static final float VFX_MIN = 0.3f;
    private static final float VFX_MAX = 0.5f;

    private boolean isInherent;
    private float vfxTimer;
    private ArrayList<MagnetizeParticleEffect> particleEffects = new ArrayList<>();
    private static final Texture img = TexLoader.getTexture("anniv5Resources/images/ui/magnetizeIcon.png");

    public MagnetizedModifier(boolean isInherent) {
        this.isInherent = isInherent;
        vfxTimer = VFX_MIN;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return isInherent;
    }

    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, MagnetizedModifier.ID) && card.cost != -2;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return uiStrings.TEXT[1] + rawDescription;
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        return Collections.singletonList(new TooltipInfo(uiStrings.EXTRA_TEXT[0], uiStrings.EXTRA_TEXT[1]));
    }

    public void onDiscarded(AbstractCard card) {
        spread(card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        spread(card);
    }

    private void spread(AbstractCard card) {
        List<AbstractCard> candidates = AbstractDungeon.player.hand.group.stream().filter(c -> c != card && shouldApply(c)).collect(Collectors.toList());
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                List<AbstractCard> newCandidates = candidates.stream().filter(c -> AbstractDungeon.player.hand.contains(c)).collect(Collectors.toList());
                if (newCandidates.size() > 0) {
                    AbstractCard newCard = newCandidates.get(AbstractDungeon.cardRandomRng.random(newCandidates.size() - 1));
                    Wiz.att(new MagnetizeAction(newCard));
                }
                isDone = true;
            }
        });
        Wiz.applyToSelf(new PolarityPower(Wiz.adp(), 1));
    }

    private static Vector2 rotatePoint(Vector2 p, float angle) {
        float s = MathUtils.sin(0.017453292F * angle);
        float c = MathUtils.cos(0.017453292F * angle);
        p.x = p.x * c - p.y * s;
        p.y = p.x * s + p.y * c;
        return p;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(img).render(card);
    }

    private class MagnetizedEffect extends LightningOrbPassiveEffect {
        public MagnetizedEffect(float x, float y) {
            super(x, y);
            this.scale = 4f;
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MagnetizedModifier(isInherent);
    }

}
