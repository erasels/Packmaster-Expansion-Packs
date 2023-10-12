package thePackmaster.cards.sentinelpack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.stances.sentinelpack.Angry;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Fume extends AbstractSentinelCard {
    public final static String ID = makeID("Fume");
    private final static int VIGOR = 2;

    public Fume() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = VIGOR;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new Angry()));
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.magicNumber)));
    }


    @Override
    public void upp() {
        this.selfRetain = true;
    }

}


