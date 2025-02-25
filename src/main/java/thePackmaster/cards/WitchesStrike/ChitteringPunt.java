package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.green.SuckerPunch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import thePackmaster.util.Wiz;


import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

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
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                    if (!mo.isDeadOrEscaped()){
                        AbstractDungeon.effectsQueue.add(new SmallLaserEffect(Wiz.p().hb.cX,Wiz.p().hb.cY,mo.hb.cX,mo.hb.cY));
                    }
                }
                isDone = true;
            }
        });
        Wiz.doAllDmg(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT,false);

        if (upgraded){
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                        if (!mo.isDeadOrEscaped()){
                            AbstractDungeon.effectsQueue.add(new SmallLaserEffect(Wiz.p().hb.cX,Wiz.p().hb.cY,mo.hb.cX,mo.hb.cY));
                        }
                    }
                    isDone = true;
                }
            });
            Wiz.doAllDmg(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT,false);
        }
        atb(new DrawPileToHandAction(1,CardType.SKILL));
    }

    public void upp() {
    }

    @Override
    public String cardArtCopy() {
        return SuckerPunch.ID;
    }
}