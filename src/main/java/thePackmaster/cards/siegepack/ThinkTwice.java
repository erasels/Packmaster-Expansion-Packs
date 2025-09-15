package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;
import static thePackmaster.util.Wiz.atb;

//REFS : PlotArmor (grandopeningpack), Subdue (bitingcoldpack)
public class ThinkTwice extends AbstractSiegeCard {
    public final static String ID = makeID("ThinkTwice");
    private static final int COST = 2;
    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 3;
    private static final int VIGOR_GAIN = 4;
    private static final int UPG_VIGOR_GAIN = 1;

    public ThinkTwice() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = VIGOR_GAIN;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower currentVigor = p.getPower(VigorPower.POWER_ID);
        if (currentVigor != null && currentVigor.amount > 0) {
            atb(new GainBlockAction(p, block + currentVigor.amount));
            Wiz.removePower(currentVigor);
        } else {
            blck();
        }

        Wiz.applyToSelf(new VigorPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
        upgradeMagicNumber(UPG_VIGOR_GAIN);
    }
}