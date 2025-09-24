package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

public class Logistics extends AbstractSiegeCard {
    public final static String ID = makeID("Logistics");
    private static final int COST = 1;
    private static final int DEBUFF_REMOVAL = 1;
    private static final int VIGOR_GAIN = 5;
    private static final int CARD_DRAW = 1;
    private static final int UPGRADE_CARD_DRAW = 1;

    public Logistics() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = VIGOR_GAIN;
        baseSecondMagic = secondMagic = CARD_DRAW;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        reduceCommonDebuffs(p);

        Wiz.applyToSelf(new VigorPower(p, magicNumber));
        addToBot(new DrawCardAction(secondMagic));
    }

    private void reduceCommonDebuffs(AbstractPlayer p) {
        atb(new ReducePowerAction(p, p, WeakPower.POWER_ID, DEBUFF_REMOVAL));
        atb(new ReducePowerAction(p, p, FrailPower.POWER_ID, DEBUFF_REMOVAL));
        atb(new ReducePowerAction(p, p, VulnerablePower.POWER_ID, DEBUFF_REMOVAL));
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
