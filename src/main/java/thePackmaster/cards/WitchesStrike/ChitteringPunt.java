package thePackmaster.cards.WitchesStrike;

import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.SuckerPunch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.witchesstrike.StarEffect;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ChitteringPunt extends AbstractWitchStrikeCard {
    public final static String ID = makeID("ChitteringPunt");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ChitteringPunt() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Bullet();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!mo.isDeadOrEscaped()){
                Wiz.vfx(new SmallLaserEffect(Wiz.p().hb.cX,Wiz.p().hb.cY,mo.hb.cX,mo.hb.cY));
                Wiz.atb(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, damage,
                        DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                        AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                        AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                        isDone= true;
                    }
                });
                if (upgraded){
                    Wiz.vfx(new SmallLaserEffect(Wiz.p().hb.cX,Wiz.p().hb.cY,mo.hb.cX,mo.hb.cY));
                    Wiz.atb(new DamageAction(mo, new DamageInfo(AbstractDungeon.player, damage,
                            DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                    Wiz.atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                            AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                            AbstractDungeon.effectList.add(new StarEffect(mo.hb.cX,mo.hb.cY,Color.CYAN,1.2f));
                            isDone= true;
                        }
                    });
                }
            }
        }
        Wiz.atb(new DrawPileToHandAction(1,CardType.SKILL));
    }

    public void upp() {
    }

    @Override
    public String cardArtCopy() {
        return SuckerPunch.ID;
    }
}