package thePackmaster.cards.discopack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.vfx.discopack.SpotlightFXtest;


import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class PointMove extends AbstractSmoothCard{
    public static final String ID = makeID("PointMove");


    public PointMove() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){atb(new DrawCardAction(p, 1));}
        atb(new DiscardPileToTopOfDeckAction(p));
        atb(new VFXAction(new SpotlightFXtest(p), 0.1f));
    }
    public void triggerOnManualDiscard() {
        att(new GainEnergyAction(1));
    }
    @Override
    public void upp() {

    }
}

