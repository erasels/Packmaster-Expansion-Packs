package thePackmaster.cards.tf2pack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.tf2pack.CozyCamperPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class CozyCamper extends AbstractTF2Card {
    public final static String ID = makeID("CozyCamper");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static final int COST = 0;

    public CozyCamper() {
        super(ID, COST, TYPE, RARITY, TARGET);
        PersistFields.setBaseValue(this, 3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(1));
        addToBot(new ApplyPowerAction(p, p, new CozyCamperPower(p, 1), 1));
    }

    @Override
    public void upp() {
        PersistFields.upgrade(this, 1);
    }
}


