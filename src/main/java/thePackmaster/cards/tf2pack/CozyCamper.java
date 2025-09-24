package thePackmaster.cards.tf2pack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.tf2pack.ResupplyRefundPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class CozyCamper extends AbstractTF2Card {
    public final static String ID = makeID("CozyCamper");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;

    public CozyCamper() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ResupplyRefundPower(p, 1), 1));
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADE_COST);
    }
}


