package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.magnetizepack.RepulsionFieldPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RepulsionField extends AbstractMagnetizeCard {
    public final static String ID = makeID(RepulsionField.class.getSimpleName());

    public RepulsionField() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RepulsionFieldPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}