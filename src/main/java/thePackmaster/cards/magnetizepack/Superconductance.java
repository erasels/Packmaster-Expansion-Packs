package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.magnetizepack.SuperconductancePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Superconductance extends AbstractMagnetizeCard {
    public final static String ID = makeID(Superconductance.class.getSimpleName());

    public Superconductance() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SuperconductancePower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(2);
    }
}