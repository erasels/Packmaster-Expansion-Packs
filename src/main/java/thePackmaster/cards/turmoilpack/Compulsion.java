package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.turmoilpack.AbandonAction;

public class Compulsion extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Compulsion");
    private static final int COST = 1;
    private static final int DRAW = 1;
    private static final int UPGRADE_DRAW = 1;

    public Compulsion() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbandonAction(c -> c.costForTurn >= 2, l -> {
            for (AbstractCard c : l) {
                if (c.costForTurn > 1) {
                    c.costForTurn = 1;
                    c.isCostModifiedForTurn = true;
                }
                if (c.cost > 1) {
                    c.cost = 1;
                    c.isCostModified = true;
                }
            }
            this.addToTop(new DrawCardAction(l.size() * this.magicNumber));
        }));
    }
}
