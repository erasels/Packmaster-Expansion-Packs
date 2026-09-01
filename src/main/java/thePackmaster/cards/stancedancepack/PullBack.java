package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.CalmStance;

import thePackmaster.powers.stancedancepack.NextTurnAggression;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class PullBack extends AbstractStanceDanceCard {
    public final static String ID = makeID("PullBack");

    public PullBack() {
        super(ID, 3, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ChangeStanceAction(new CalmStance()));
        if (!p.hasPower(NextTurnAggression.POWER_ID)) {
            Wiz.applyToSelf(new NextTurnAggression(p, 1));
        }
    }


    @Override
    public void upp() {
        upgradeBlock(4);
    }

}


