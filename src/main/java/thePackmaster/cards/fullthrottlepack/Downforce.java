package thePackmaster.cards.fullthrottlepack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.actions.fullthrottlepack.ApplyRandomNitroAction;
import thePackmaster.patches.fullthrottlepack.AddToHandPatches;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;


public class Downforce extends AbstractFullThrottleCard {
    public final static String ID = makeID("Downforce");

    public Downforce() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.baseSecondMagic = 1;
        this.secondMagic = this.baseSecondMagic;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AllEnemyApplyPowerAction(p, magicNumber, n -> new IgnitePower(n, magicNumber)));
        atb(new AllEnemyApplyPowerAction(p, secondMagic, n -> new VulnerablePower(n, secondMagic, false)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}


