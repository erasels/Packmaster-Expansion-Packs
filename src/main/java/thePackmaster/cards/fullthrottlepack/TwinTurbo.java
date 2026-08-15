package thePackmaster.cards.fullthrottlepack;


import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class TwinTurbo extends AbstractFullThrottleCard implements AddToHandPatches.AddToHandInterface {
    public final static String ID = makeID("TwinTurbo");

    public TwinTurbo() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 8;
        this.block = this.baseBlock;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, block));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void onAddCard(CardGroup group, AbstractCard card) {
        if (card == this) {
            atb(new GainEnergyAction(1));
        }
    }
}


