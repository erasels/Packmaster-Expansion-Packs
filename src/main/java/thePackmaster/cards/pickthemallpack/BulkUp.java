package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class BulkUp extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("BulkUp");
    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UPGRADE_BLOCK = 2;
    private static final int TEMP_HP = 4;
    private static final int UPGRADE_TEMP_HP = 1;
    private static final int PICKUP_MAX_HP_GAIN = 1;

    public BulkUp() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = TEMP_HP;
        this.secondMagic = this.baseSecondMagic = PICKUP_MAX_HP_GAIN;
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
        this.upgradeMagicNumber(UPGRADE_TEMP_HP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new AddTemporaryHPAction(p, p, this.magicNumber));
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.player.increaseMaxHp(PICKUP_MAX_HP_GAIN, false);
    }
}
