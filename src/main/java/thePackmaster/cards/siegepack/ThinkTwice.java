package thePackmaster.cards.siegepack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.siegepack.FlavorConstants.*;

public class ThinkTwice extends AbstractSiegeCard {
    public final static String ID = makeID("ThinkTwice");
    private static final int COST = 2;
    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 4;
    private static final int BLOCK_PER_ATTACKER = 5;

    public ThinkTwice() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = BLOCK_PER_ATTACKER;

        FlavorText.AbstractCardFlavorFields.flavorBoxType.set(this, FLAVOR_BOX_TYPE);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        //Gain block per enemy that intends to attack.
        for (AbstractMonster mo : Wiz.getEnemies()) {
            if (Wiz.isAttacking(mo)) {
                Wiz.doBlk(magicNumber);
            }
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : Wiz.getEnemies()) {
            if (Wiz.isAttacking(m)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}
