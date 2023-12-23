package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.stances.stancedancepack.Weaver;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Choreography extends AbstractStanceDanceCard {
    public final static String ID = makeID("Choreography");

    public Choreography() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ChangeStanceAction(new Weaver()));
    }


    @Override
    public void upp() {
        upgradeBlock(3);
    }

}


