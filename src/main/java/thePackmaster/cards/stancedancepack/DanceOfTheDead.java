package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.cthulhupack.Lunacy;

import thePackmaster.powers.stancedancepack.DanceOfTheDeadPower;
import thePackmaster.stances.cthulhupack.NightmareStance;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class DanceOfTheDead extends AbstractStanceDanceCard {
    public final static String ID = makeID("DanceOfTheDead");

    public DanceOfTheDead() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new LoseHPAction(m, p, 4));
        Wiz.applyToEnemy(m, new DanceOfTheDeadPower(m, magicNumber));

    }


    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

}


