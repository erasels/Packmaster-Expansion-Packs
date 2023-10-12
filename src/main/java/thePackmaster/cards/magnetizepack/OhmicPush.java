package thePackmaster.cards.magnetizepack;

import basemod.AutoAdd;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cardmodifiers.magnetizepack.MagnetizedModifier;
import thePackmaster.powers.distortionpack.DistortionPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class OhmicPush extends AbstractMagnetizeCard {
    public final static String ID = makeID(OhmicPush.class.getSimpleName());

    public OhmicPush() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 2;
        CardModifierManager.addModifier(this, new MagnetizedModifier(true));
        cardsToPreview = new Fuzz();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new DistortionPower(m, p, magicNumber));
        Wiz.applyToEnemy(m, new WeakPower(m, secondMagic, false));
        addToBot(new MakeTempCardInHandAction(cardsToPreview));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}