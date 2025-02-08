package thePackmaster.cards.bladestormpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.needlework.BindPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.bladestormpack.FlavorConstants.*;

//REFS: DummyStrikeJr (strikepack), Knot (needleworkpack), PiercingWail (base game), Decapitate (aggressionpack).
public class FallOver extends AbstractBladeStormCard {
    public final static String ID = makeID("FallOver");
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;
    private static final int STRENGTH_GAIN = 1;
    private static final int BIND = 3;
    private static final int UPG_BIND = 1;

    public FallOver() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = BIND;
        baseSecondMagic = secondMagic = STRENGTH_GAIN;
        exhaust = true;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        Wiz.applyToSelf(new StrengthPower(p, secondMagic));

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDead && !mo.escaped) {
                addToBot(new ApplyPowerAction(mo, p, new BindPower(mo, magicNumber), magicNumber, true));
            }
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_BIND);
    }
}
