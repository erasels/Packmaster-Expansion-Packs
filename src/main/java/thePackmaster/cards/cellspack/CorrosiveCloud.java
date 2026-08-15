package thePackmaster.cards.cellspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.SpireAnniversary5Mod;

public class CorrosiveCloud extends AbstractCellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("CorrosiveCloud");

    private static final int COST = 1;
    private static final int POISON = 2;
    private static final int SPLASH = 2;
    private static final int UPGRADE_POISON = 0;
    private static final int UPGRADE_SPLASH = 2;

    public CorrosiveCloud() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);

        // Base poison applied to the single target
        this.magicNumber = this.baseMagicNumber = POISON;
        this.secondMagic = this.baseSecondMagic = SPLASH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Step 1: Apply poison to the single selected target
        this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));

        // Step 2: Loop through all alive enemies and apply the AoE poison
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                this.addToBot(new ApplyPowerAction(mo, p, new PoisonPower(mo, p, this.secondMagic), this.secondMagic, AbstractGameAction.AttackEffect.POISON));
            }
        }
    }

    @Override
    public void upp() {
        this.upgradeSecondMagic(UPGRADE_SPLASH);
    }
}