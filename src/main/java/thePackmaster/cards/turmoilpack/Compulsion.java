package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.turmoilpack.AbandonAction;

public class Compulsion extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Compulsion");
    private static final int COST = 1;

    public Compulsion() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbandonAction(c -> c.cost == -2 || c.costForTurn >= 2, l -> {
            this.addToTop(new ApplyPowerAction(m, p, new DrawCardNextTurnPower(m, l.size())));
            this.addToTop(new DrawCardAction(l.size()));
        }));
    }
}
