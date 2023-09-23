package thePackmaster.cards.pickthemallpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.shamanpack.IgnitePower;

public class TouchedByFlame extends AbstractPickThemAllCard implements OnObtainCard {
    public static final String ID = SpireAnniversary5Mod.makeID("TouchedByFlame");
    private static final int COST = 1;
    private static final int IGNITE = 9;
    private static final int UPGRADE_IGNITE = 3;
    private static final int WEAK_VULNERABLE = 1;
    private static final int UPGRADE_WEAK_VULNERABLE = 1;
    private static final int PICKUP_MAX_HP_LOSS = 7;

    public TouchedByFlame() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = IGNITE;
        this.secondMagic = this.baseSecondMagic = WEAK_VULNERABLE;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_IGNITE);
        this.upgradeSecondMagic(UPGRADE_WEAK_VULNERABLE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new IgnitePower(m, this.magicNumber)));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.secondMagic, false)));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.secondMagic, false)));
    }

    @Override
    public void onObtainCard() {
        // Not going below 1 max HP is handled by decreaseMaxHealth
        AbstractDungeon.player.decreaseMaxHealth(PICKUP_MAX_HP_LOSS);
    }

    @Override
    public String getPickupDescription() {
        return super.getPickupDescription().replace("{0}", PICKUP_MAX_HP_LOSS + "");
    }
}
