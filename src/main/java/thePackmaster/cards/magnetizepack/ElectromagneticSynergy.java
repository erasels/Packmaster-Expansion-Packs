package thePackmaster.cards.magnetizepack;

import basemod.AutoAdd;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.magnetizepack.ElectromagneticSynergyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class ElectromagneticSynergy extends AbstractMagnetizeCard {
    public final static String ID = makeID(ElectromagneticSynergy.class.getSimpleName());

    public ElectromagneticSynergy() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ElectromagneticSynergyPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }
}