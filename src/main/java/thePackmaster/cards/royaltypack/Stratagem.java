package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.royaltypack.NextTurnHiredSupportPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Stratagem extends AbstractRoyaltyCard {

    public final static String ID = makeID("Stratagem");
    private static final int STR_LOSS_AMOUNT = 9;
    private static final int UPGRADE_EXTRA_STR_LOSS = 3;

    public Stratagem() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = STR_LOSS_AMOUNT;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_EXTRA_STR_LOSS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p,
                new StrengthPower(m, -this.magicNumber),
                -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        if (!m.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(m, p,
                    new GainStrengthPower(m, this.magicNumber),
                    this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        this.addToBot(new ApplyPowerAction(p,p,
                new NextTurnHiredSupportPower(p, 1)));
    }
}
