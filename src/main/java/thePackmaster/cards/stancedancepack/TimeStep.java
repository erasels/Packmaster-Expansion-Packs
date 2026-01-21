package thePackmaster.cards.stancedancepack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.NeutralStance;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Set;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class TimeStep extends AbstractStanceDanceCard {
    public final static String ID = makeID("TimeStep");

    public TimeStep() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrengthPower(p, numUniqueStancesButNotNeutralStance() + magicNumber));
    }

    private static int numUniqueStancesButNotNeutralStance() {
        Set<String> result = AbstractDungeon.actionManager.uniqueStancesThisCombat.keySet();
        if (result.contains(NeutralStance.STANCE_ID))
            result.remove(NeutralStance.STANCE_ID);
        return result.size();
    }

    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + (numUniqueStancesButNotNeutralStance() + magicNumber) + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}


