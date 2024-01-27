package thePackmaster.stances.stancedancepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.CalmStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import thePackmaster.cards.stancedancepack.WovenCard;
import thePackmaster.powers.stancedancepack.NextWovenCheaper;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.downfallpack.AncientStanceParticleEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Weaver extends AbstractStance {
    public static final String STANCE_ID = makeID("Weaver");
    private static long sfxId = -1L;

    public ArrayList<AbstractCard> storedcards = new ArrayList<>();
    private AbstractCard sourceCard;

    public Weaver() {
        this.ID = STANCE_ID;
        this.name = CardCrawlGame.languagePack.getStanceString(STANCE_ID).NAME;
        this.updateDescription();
    }

    public Weaver(AbstractCard source) {
        this.ID = STANCE_ID;
        this.name = CardCrawlGame.languagePack.getStanceString(STANCE_ID).NAME;
        this.updateDescription();
        sourceCard = source;
    }

    @Override
    public void updateAnimation() {
            if (!Settings.DISABLE_EFFECTS) {
                this.particleTimer -= Gdx.graphics.getDeltaTime();
                if (this.particleTimer < 0.0F) {
                    this.particleTimer = 0.04F;
                    //TODO: New particles
                    AbstractDungeon.effectsQueue.add(new AncientStanceParticleEffect(new Color(1F, .5F, .5F, 0.0F)));
                }
            }

            this.particleTimer2 -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer2 < 0.0F) {
                this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
                AbstractDungeon.effectsQueue.add(new StanceAuraEffect(""));
            }
    }

    @Override
    public void onEnterStance() {
        if (sfxId != -1L) {
            stopIdleSfx();
        }

        CardCrawlGame.sound.play("POWER_FLIGHT");
        for (int i = 0; i < 30; i++) {
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect(CalmStance.STANCE_ID));
        }
    }

    @Override
    public void onExitStance() {
        if (storedcards.size() > 0){
            WovenCard c = new WovenCard(this);
            Wiz.atb(new MakeTempCardInHandAction(c));
        }
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        super.onPlayCard(card);
        if (card != sourceCard) {

            if (storedcards.size() < 3) {
                storedcards.add(card.makeStatEquivalentCopy());
                if (storedcards.size() >= 3) {
                    Wiz.atb(new ChangeStanceAction(NeutralStance.STANCE_ID));
                }

            } else {
                Wiz.atb(new ChangeStanceAction(NeutralStance.STANCE_ID));
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = CardCrawlGame.languagePack.getStanceString(STANCE_ID).DESCRIPTION[0];
    }


    }
