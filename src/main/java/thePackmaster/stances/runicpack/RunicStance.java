package thePackmaster.stances.runicpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;
import thePackmaster.ExpansionPacks;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.stances.OnAttackStance;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.downfallpack.AncientStanceParticleEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class RunicStance extends AbstractStance {
    public static final String STANCE_ID = makeID("Runic");
    private static long sfxId = -1L;

    public RunicStance() {
        this.ID = STANCE_ID;
        this.name = CardCrawlGame.languagePack.getStanceString(STANCE_ID).NAME;
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        AbstractCard newChannelCard = ExpansionPacks.channelCards.get(AbstractDungeon.cardRng.random(0, ExpansionPacks.channelCards.size()-1)).makeCopy();
        Wiz.atb(new SimpleAddModifierAction(new EtherealMod(), newChannelCard));
        Wiz.atb(new SimpleAddModifierAction(new ExhaustMod(), newChannelCard));
        Wiz.atb(new MakeTempCardInHandAction(newChannelCard));
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.3F;
                AbstractDungeon.effectsQueue.add(new RuneParticle(Color.WHITE.cpy()));
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new RunicAura());
        }
    }

    @Override
    public void onEnterStance() {
        if (sfxId != -1L) {
            stopIdleSfx();
        }
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.CYAN, true));
        CardCrawlGame.sound.playV("SINGING_BOWL", 1.5f);
    }

    @Override
    public void onExitStance() {applyToSelf(new FocusPower(Wiz.p(), 1));}

    @Override
    public void updateDescription() {
        this.description = CardCrawlGame.languagePack.getStanceString(STANCE_ID).DESCRIPTION[0];
    }

}
