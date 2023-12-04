package thePackmaster.cards.quantapack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MAXPOWER extends AbstractQuantaCard {
    public final static String ID = makeID("MAXPOWER");

    public MAXPOWER() {
        super(ID, 4, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 11;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int str = Wiz.getLogicalPowerAmount(p, StrengthPower.POWER_ID);
        str = this.magicNumber - str;
        if (str > 0)
            Wiz.applyToSelf(new StrengthPower(p, str));
    }

    public void upp() {
        upgradeBaseCost(3);
    }
}
