package thePackmaster.cards.tf2pack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.tf2pack.DispenserPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Dispenser extends AbstractTF2Card {
    public final static String ID = makeID("Dispenser");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.POWER;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static final int COST = 1;
    private static final int MAGIC = 1;

    public Dispenser() {
        super(ID, COST, TYPE, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
        this.cardsToPreview = new Ammo();
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DispenserPower(p, this.magicNumber), this.magicNumber));
    }
}