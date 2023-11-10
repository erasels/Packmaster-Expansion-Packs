package thePackmaster.cards.discopack;


import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.discopack.FastMovementsPower;
import thePackmaster.powers.discopack.InTheRhythmPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class InTheRhythm extends AbstractSmoothCard{
    public static final String ID = makeID("InTheRhythm");

    public InTheRhythm() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = magicNumber = 1;
        this.baseSecondMagic = secondMagic = 4;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyToSelf(new InTheRhythmPower(p, magicNumber));
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}