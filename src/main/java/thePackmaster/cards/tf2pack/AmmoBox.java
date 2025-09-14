package thePackmaster.cards.tf2pack;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AmmoBox extends AbstractTF2Card {
    public final static String ID = makeID("AmmoBox");
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;

    private static final int COST = 2;

    private static final int BLOCK = 9;
    private static final int UPGRADE_BLOCK = 3;
    private static final int MAGIC = 2;

    public AmmoBox() {
        super(ID, COST, TYPE, RARITY, TARGET);

        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = MAGIC;
        this.cardsToPreview = new Ammo();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.blck();
        this.addToBot(new MakeTempCardInHandAction(this.cardsToPreview, this.magicNumber));
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
        this.cardsToPreview.upgrade();
    }
}
