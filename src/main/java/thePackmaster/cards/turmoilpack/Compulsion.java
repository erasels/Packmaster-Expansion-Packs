package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.EnlightenmentAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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
        this.addToBot(new EnlightenmentAction(true));
        this.addToBot(new AbandonAction(c -> c.costForTurn >= 2, l -> {
            this.addToTop(new DrawCardAction(l.size()));
        }));
    }
}
