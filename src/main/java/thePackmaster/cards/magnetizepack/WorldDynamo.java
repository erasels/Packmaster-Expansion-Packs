package thePackmaster.cards.magnetizepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.magnetizepack.InduceCurrentPower;
import thePackmaster.powers.magnetizepack.WorldDynamoPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WorldDynamo extends AbstractMagnetizeCard {
    public final static String ID = makeID(WorldDynamo.class.getSimpleName());

    public WorldDynamo() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WorldDynamoPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}