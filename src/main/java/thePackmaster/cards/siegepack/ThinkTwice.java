package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

//REFS : PlotArmor (grandopeningpack), Subdue (bitingcoldpack),
// HuntingInstincts (monsterhunterpack), BlockVial (womaninbluepack).
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
        blck();

        AbstractPower currentVigor = p.getPower(VigorPower.POWER_ID);
        if (currentVigor != null) {
            Wiz.removePower(currentVigor);
        }

        Wiz.applyToSelf(new VigorPower(p, magicNumber));
    }

    @Override
    public void applyPowersToBlock() {      //Updates the block displayed on the card while in hand, hovered or not.
        super.applyPowersToBlock();

        AbstractPower previewVigor = AbstractDungeon.player.getPower(VigorPower.POWER_ID);
        if (previewVigor != null && previewVigor.amount > 0) {
            this.block += previewVigor.amount;
            isBlockModified = true;
        }
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
        upgradeMagicNumber(UPG_VIGOR_GAIN);
    }
}