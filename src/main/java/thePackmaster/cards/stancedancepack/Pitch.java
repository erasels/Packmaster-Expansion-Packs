package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.stances.serpentinepack.VenemousStance;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.stancedancepack.StanceDanceEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Pitch extends AbstractStanceDanceCard {
    public final static String ID = makeID("Pitch");

    public Pitch() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new VenemousStance()));

        this.addToBot(new MakeTempCardInHandAction(new Shiv(), this.magicNumber));
        p.useHopAnimation();
        Wiz.atb(new VFXAction(new StanceDanceEffect(p, false, false, false), 0.5F));
    }


    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

}


