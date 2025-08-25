package thePackmaster.cards.tf2pack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.tf2pack.StealthPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InvisibilityWatch extends AbstractTF2Card {
    public final static String ID = makeID("InvisibilityWatch");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static final int COST = 0;

    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;

    public InvisibilityWatch() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    @Override
    public void upp() {
        this.upMagic(UPGRADE_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StealthPower(p, this.magicNumber)));
    }
}

