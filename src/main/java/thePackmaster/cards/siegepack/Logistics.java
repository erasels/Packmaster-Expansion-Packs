package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.powers.siegepack.ShellPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

public class Logistics extends AbstractSiegeCard {
    public final static String ID = makeID("Logistics");
    private static final int COST = 1;
    private static final int BASE_EFFECTS_AND_DRAW = 1;
    private static final int CARD_DRAW = 1;
    private static final int UPGRADE_CARD_DRAW = 1;

    public Logistics() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = BASE_EFFECTS_AND_DRAW;
        baseSecondMagic = secondMagic = magicNumber + CARD_DRAW;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        removeCommonDebuffInThatOrder(p);

        Wiz.applyToSelf(new ShellPower(p, magicNumber));
        addToBot(new DrawCardAction(secondMagic));
    }

    private void removeCommonDebuffInThatOrder(AbstractPlayer p) {
        //Remove any Vulnerable on player. If none, same for Frail. If none, same for Weak.
        if (p.hasPower(VulnerablePower.POWER_ID)) {
            atb(new RemoveSpecificPowerAction(p, p, VulnerablePower.POWER_ID));
            return;
        }
        if (p.hasPower(FrailPower.POWER_ID)) {
            atb(new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
            return;
        }
        atb(new RemoveSpecificPowerAction(p, p, WeakPower.POWER_ID));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (player.hasPower(WeakPower.POWER_ID) || player.hasPower(FrailPower.POWER_ID) || player.hasPower(VulnerablePower.POWER_ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void upp() {
        upgradeSecondMagic(UPGRADE_CARD_DRAW);
        upgradedSecondMagic = true;
        initializeDescription();
    }
}
