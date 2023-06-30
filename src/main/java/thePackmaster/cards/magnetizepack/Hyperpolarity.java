package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.magnetizepack.HyperpolarityPower;
import thePackmaster.powers.magnetizepack.InduceCurrentPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Hyperpolarity extends AbstractMagnetizeCard {
    public final static String ID = makeID(Hyperpolarity.class.getSimpleName());

    public Hyperpolarity() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HyperpolarityPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        exhaust = false;
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}