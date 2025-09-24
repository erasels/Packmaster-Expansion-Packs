package thePackmaster.cards.doppelpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.actions.doppelpack.SummonAction;

public class Arrive extends AbstractDoppelCard {
    public static final String ID = SpireAnniversary5Mod.makeID(Arrive.class.getSimpleName());

    public Arrive() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
        previewDoppel = true;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (amount, params) -> {
            if (upgraded) {
                amount += 1;
            }
            if (amount > 0) {
                addToBot(new SummonAction(amount));
            }
            return true;
        }));
    }
}
