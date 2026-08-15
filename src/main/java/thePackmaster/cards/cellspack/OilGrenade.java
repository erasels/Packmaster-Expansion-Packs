package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.util.Wiz.atb;

public class OilGrenade extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("OilGrenade");
    private static final int COST = 0;
    private static final int IGNITE = 2;
    private static final int DRAW = 1;

    public OilGrenade() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        this.magicNumber = this.baseMagicNumber = IGNITE;
        this.secondMagic = this.baseSecondMagic = DRAW;
        this.exhaust = true;
    }

    @Override
    public void upp() {}

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber)));
        atb(new DrawCardAction(DRAW));

    }
}