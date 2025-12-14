package thePackmaster.cards.tf2pack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.tf2pack.BonkAtomicPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class BonkAtomicPunch extends AbstractTF2Card {
    public final static String ID = makeID("BonkAtomicPunch");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static final int COST = 3;

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public BonkAtomicPunch() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = MAGIC;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new BonkAtomicPower(p, this.magicNumber)));
    }
}


