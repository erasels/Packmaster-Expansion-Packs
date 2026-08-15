package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonspack.JinxPower;

public class AssaultShield extends AbstractCellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("AssaultShield");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // Core stats
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int JINX = 2;
    private static final int UPGRADE_PLUS_JINX = 1;

    public AssaultShield() {
                super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = JINX;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Apply Block to yourself
        this.addToBot(new GainBlockAction(p, p, this.block));

        // Apply Poison to the targeted enemy
        this.addToBot(new ApplyPowerAction(m, p, new JinxPower(m, this.magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_PLUS_BLOCK);
            this.upgradeMagicNumber(UPGRADE_PLUS_JINX);
            this.initializeDescription();
        }
    }
}
