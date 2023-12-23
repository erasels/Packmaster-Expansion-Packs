package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.cthulhupack.Lunacy;

import thePackmaster.stances.cthulhupack.NightmareStance;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class DeathDrop extends AbstractStanceDanceCard {
    public final static String ID = makeID("DeathDrop");

    public DeathDrop() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new NightmareStance()));
        Wiz.atb(new MakeTempCardInHandAction(new Lunacy(),magicNumber));
    }


    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

}


