package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.turmoilpack.AbandonAction;

public class ForsakenDreams extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ForsakenDreams");
    private static final int COST = 2;
    private static final int BUFFS = 1;

    public ForsakenDreams() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = BUFFS;
        this.isEthereal = true;
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbandonAction(c -> true, l -> {
            if (l.isEmpty()) { return; }
            int amount = (int)l.stream().map(c -> c.type).distinct().count();
            this.addToTop(new ApplyPowerAction(p, p, new ThornsPower(p, amount)));
            this.addToTop(new ApplyPowerAction(p, p, new MetallicizePower(p, amount)));
            this.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, amount)));
        }));
    }
}
