package thePackmaster.cards.needleworkpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.needlework.CopyAndPastePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CopyAndPaste extends AbstractNeedleworkCard {
    public final static String ID = makeID("CopyAndPaste");

    public CopyAndPaste() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CopyAndPastePower(p, 1, upgraded)));
    }

    @Override
    public void upp() {
    }
}
