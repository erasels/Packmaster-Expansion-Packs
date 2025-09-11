package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.stances.runicpack.RunicStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ReadRunes extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 3;
    public final static String ID = makeID("ReadRunes");


    public ReadRunes() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
        this.addToBot(new ChangeStanceAction(new RunicStance()));
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}
