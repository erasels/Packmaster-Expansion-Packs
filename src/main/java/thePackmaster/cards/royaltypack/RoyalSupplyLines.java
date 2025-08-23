package thePackmaster.cards.royaltypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.royaltypack.RoyalSupplyLinesPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RoyalSupplyLines extends AbstractRoyaltyCard {

    public final static String ID = makeID("RoyalSupplyLines");

    public RoyalSupplyLines() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.atb(new ApplyPowerAction(AbstractDungeon.player,
                        AbstractDungeon.player,
                        new RoyalSupplyLinesPower(AbstractDungeon.player, 1)
                )
        );
    }
}
