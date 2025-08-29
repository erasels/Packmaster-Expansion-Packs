package thePackmaster.cards.runicpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.runicpack.ObeliskPower;
import thePackmaster.stances.runicpack.RunicStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AncientObelisk extends AbstractRunicCard {

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
    public final static String ID = makeID("AncientObelisk");


    public AncientObelisk() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new ObeliskPower(abstractPlayer, magicNumber), magicNumber));
        this.addToBot(new ChangeStanceAction(new RunicStance()));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
