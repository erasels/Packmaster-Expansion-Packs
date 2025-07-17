package thePackmaster.cards.turmoilpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class DarkPath extends AbstractTurmoilCard {
    public static final String ID = SpireAnniversary5Mod.makeID("DarkPath");
    private static final int COST = 0;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BLOCK = 3;
    private static final int STATUSES = 1;

    public DarkPath() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = STATUSES;
        this.cardsToPreview = new Burden();
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new MakeTempCardInHandAction(new Burden(), this.magicNumber));
    }
}
