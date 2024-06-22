package thePackmaster.cards.needleworkpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.upgradespack.SuperUpgradeAction;
import thePackmaster.powers.needlework.ReadingPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Reading extends AbstractNeedleworkCard {
    public final static String ID = makeID("Reading");

    public Reading() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);

        this.cardsToPreview = new Read();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ReadingPower(p, 1 + timesUpgraded)));
    }

    @Override
    public void upp() {
        if (this.cardsToPreview.timesUpgraded < this.timesUpgraded) {
            SuperUpgradeAction.forceUpgrade(this.cardsToPreview, false);
        }
    }

    @Override
    protected void uDesc() {
        this.rawDescription = String.format(this.cardStrings.UPGRADE_DESCRIPTION, cardsToPreview.name);
        this.initializeDescription();
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard c = super.makeStatEquivalentCopy();
        if (c.cardsToPreview != null) {
            while(c.cardsToPreview.timesUpgraded < this.timesUpgraded) {
                SuperUpgradeAction.forceUpgrade(c.cardsToPreview, false);
            }
        }

        return c;
    }
}
