package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.stances.stancedancepack.Weaver;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.stancedancepack.StanceDanceEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Choreography extends AbstractStanceDanceCard {
    public final static String ID = makeID("Choreography");

    public Choreography() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.useJumpAnimation();
        Wiz.atb(new VFXAction(new StanceDanceEffect(p, false, false, false), 0.5F));
        blck();
        this.addToBot(new ChangeStanceAction(new Weaver(this)));
    }


    @Override
    public void upp() {
        upgradeBlock(3);
    }

}


