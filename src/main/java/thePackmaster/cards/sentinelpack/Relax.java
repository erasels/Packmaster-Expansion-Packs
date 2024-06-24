package thePackmaster.cards.sentinelpack;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.CalmStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Relax extends AbstractSentinelCard {
    public final static String ID = makeID("Relax");
    private final static int BLOCK = 2;

    public Relax() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new CalmStance()));
        this.addToBot(new GainBlockAction(p, this.block));
    }


    @Override
    public void upp() {
        this.selfRetain = true;
    }


}


